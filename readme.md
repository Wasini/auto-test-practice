# Sobre el codigo 
Las carpetas test y src contienen todo el codigo fuente.

Los test fueron realizados utilizando Junit+hamcrest-all.

# Actividades
Los ejercicios se encuentran en el archivo TP1.pdf

# Ejercicio 1
#### B)
Para lograr tests eficientes y con un alto puntaje de cobertura de ramas y mutación los parametros de los datos de entrada generados deben tener en cuenta los casos especiales, para el generador dado podemos jugar con la **cantidad de elementos de la lista**, el **rango de los enteros generados** y la **cantidad de listas generadas**.  
* Para la cantidad de elementos en cada lista considerar:
	* Lista vacia
	* Lista unitaria
	* Lista con mas de un elemento
* Para el rango de enteros considerar:
	* Mayores a 0
	* Menores a 0
	* 0
* Para la cantidad de listas generadas podemos tener en cuenta la cantidad de combinaciones entre el rango de enteros y el tamaño de las listas de forma que se generen listas que combinen las diferentes caracteristicas mencionadas arriba, es decir, para todas las listas generadas exista:
	* Alguna lista vacia
	* Alguna lista con un elemento y:
		* Valores mayores a 0
		* Valores menores a 0
		* Valores igual a 0
	* Alguna lista con mas de un elemento y:
		* Valores mayores a 0
		* Valores menores a 0
		* Valores igual a 0

Para el ejemplo dado esta el caso extremo en que la cantidad de elementos en una lista sea cercana a la cantidad maxima de elementos que puede almacenar un arreglo, el cual es mejor encararlo por separado para no sacarle eficiencia al generador.
Para hacer los test eficientes elegimos rangos pequeños tomen en cuenta las caracteristicas mencionadas, por ejemplo 

    {	minTamañoLista:0, 
    	maxTamañoLista:5,
    	minInt: -5,
    	maxInt: 5,
    	cantidadListas: 60 //aprox. 6 posibles tamaños de listas * 11 valores de enteros 

#### C)
Para el generador de ArrayList se utilizaron metodos propios de la clase ArrayList, por lo que este sera correcto si los metodos usados fueron Testeados y se sabe que funcionan como deberían.
#### D)

## Mutantes equivalentes
En la carpeta **out** se encuentra la salida de la cobertura de mutantes.
<details>
<summary> 1. Límite de condicional en ensureCapacity(int minCapacity)</summary>  

```java
	public void ensureCapacity(int minCapacity) {
		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
				// any size if not default element table
				? 0
				// larger than default for default empty table. It's already
				// supposed to be at default size.
				: DEFAULT_CAPACITY;

		if (minCapacity > minExpand) {
		//if (minCapacity >= minExpand) No afecta al cambio de capacidad del arreglo
			ensureExplicitCapacity(minCapacity);
		}
	}
```
</details>
<details>
<summary> 2. Remover llamado a rangeCheckForAdd</summary>  

Siempre salta la execpion IndexOutOfBound en el momento de copiar el arreglo por indices invalidos

```java
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		// //rangeCheckForAdd(index);
		// Cuando copia los elementos de todas formas tira la excepcion
		
		ensureCapacityInternal(size + 1); // Increments modCount!!
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}
```
</details>

<details>
<summary> 3. Condicion de limite en remove y fastRemove</summary>  

```java
	public E remove(int index) {
		rangeCheck(index);

		E oldValue = elementData(index);

		int numMoved = size - index - 1;
		if (numMoved > 0)
		// (numMoved >= 0) No cambia en nada al copiar 0 elementos
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null; // clear to let GC do its work

		return oldValue;
	}
```
```java
private void fastRemove(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0)
		// (numMoved >= 0) No cambia en nada al copiar 0 elementos
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null; // clear to let GC do its work
	}
```
</details>

## Mutantes ignorados

<details>
<summary> 1. Limites de condicion y condiciones negadas en hugeCapacity</summary>  

Los ignoro por el hecho que puede tirrar error de memoria dependiendo si la VM permite reservar arreglos de tamaño Integer.MAX_VALUE.

```java
		private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow 
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}
```
</details>

<details>
<summary> 2. Cambio de retorno del objeto en outOfBoundMsg</summary>  

Ignorado porque me parecio redundante para el caso

```java
private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}
```
</details>

# Ejercicio 2
## Grafo
![alt text](https://image.ibb.co/bVW9GJ/flowchart.png "Grafo de flujo de control")
#### B)
Si se inicializa el arreglo primes con una capacidad de 4 enteros, el t1(n=3) pasa mientras que para t2(n=5) produce una exepción por indice fuera de limites cuando intenta guardar el 5to numero primo en el arreglo.
```java
int curPrime; // Value currently considered for primeness  
int numPrimes; // Number of primes found so far.  
boolean isPrime; // Is curPrime prime?  
int [] primes = new int [4]; // The list of prime numbers.
```
#### C)
Sea t0: (n = 1) el camino de test correspondiente es [0,1,9,10,11,9,12], donde no se pasa por el cuerpo del while, la condicion del while no se cumple para n = 1 (1 < 1).

#### D)
## Requerimientos de test
<sup> **NOTA**: *No se tienen en cuenta los nodos 10,11,12* </sup>

#### Node Coverage
* El conjunto de TP deben visitar los nodos RT={0,1,2,3,4,5,6,7,8,9}
#### Edge Coverage
* El conjunto de TP deben pasar por las aritas RT={(0,1), (1,2), (1,9), (2,3), (3,4), (3,7), (4,5), (4,6), (5,7), (6,3), (7,1), (7,8), (8,1)}
### Prime Path Coverage
* El conjunto de TP deben pasar, con o sin desvios/sidepaths, por los siguientes subcaminos:
1. [0, 1, 9] 
2. [3, 4, 6, 3] 
3. [4, 6, 3, 4] 
4. [6, 3, 4, 6] 
5. [1, 2, 3, 7, 1] 
6. [2, 3, 7, 1, 2] 
7. [2, 3, 7, 1, 9] 
8. [3, 7, 1, 2, 3] 
9. [7, 1, 2, 3, 7] 
10. [0, 1, 2, 3, 4, 6] 
11. [0, 1, 2, 3, 7, 8] 
12. [1, 2, 3, 7, 8, 1] 
13. [2, 3, 7, 8, 1, 2] 
14. [2, 3, 7, 8, 1, 9] 
15. [3, 7, 8, 1, 2, 3] 
16. [4, 6, 3, 7, 1, 2] 
17. [4, 6, 3, 7, 1, 9] 
18. [7, 8, 1, 2, 3, 7] 
19. [8, 1, 2, 3, 7, 8]
20. [1, 2, 3, 4, 5, 7, 1] 
21. [2, 3, 4, 5, 7, 1, 2] 
22. [2, 3, 4, 5, 7, 1, 9] 
23. [3, 4, 5, 7, 1, 2, 3] 
24. [4, 5, 7, 1, 2, 3, 4]
25. [4, 6, 3, 7, 8, 1, 2] 
26. [4, 6, 3, 7, 8, 1, 9]
27. [5, 7, 1, 2, 3, 4, 5]
28. [5, 7, 1, 2, 3, 4, 6]
29. [6, 3, 4, 5, 7, 1, 2]
30. [6, 3, 4, 5, 7, 1, 9]
31. [7, 1, 2, 3, 4, 5, 7]
32. [0, 1, 2, 3, 4, 5, 7, 8]
33. [1, 2, 3, 4, 5, 7, 8, 1]
34. [2, 3, 4, 5, 7, 8, 1, 2]
35. [2, 3, 4, 5, 7, 8, 1, 9]
36. [3, 4, 5, 7, 8, 1, 2, 3]
37. [4, 5, 7, 8, 1, 2, 3, 4]
38. [5, 7, 8, 1, 2, 3, 4, 5]
39. [5, 7, 8, 1, 2, 3, 4, 6]
40. [6, 3, 4, 5, 7, 8, 1, 2]
41. [6, 3, 4, 5, 7, 8, 1, 9]
42. [7, 8, 1, 2, 3, 4, 5, 7]
43. [8, 1, 2, 3, 4, 5, 7, 8]

* Para obtener los caminos primos se generan todos los caminos simples empezando por los de longitud 0 (nodos individuales), se agregan nuevos caminos extendiendo su longitud, aquellos caminos que alcanzen un nodo final son marcados(!) al igual que aquellos caminos que comienzan y terminan con el mismo nodo(ciclos *), los caminos marcados no se siguen extendiendo, el procedimiento termina cuando ya no se puedan generar caminos simples.
Una vez generados todos los caminos simples, se eligen como primos aquellos que **no sean un subcamino** de otro, empezando primero por los de mayor longitud.
 
![alt text](https://image.ibb.co/j9WOAd/simplepaths.png "Ejemplo de como generar caminos simples")

#### E)
Camino de test que consigue cobertura de nodos pero no de arcos:
>* [0,1,2,3,4,6,3,4,5,7,8,1,9]
Los arcos (7,1) y (3,7) no estan cubiertos
#### F)
Camino de test que consigue cobertura de arcos pero no de caminos primos:
> * [0,1,2,3,4,6,3,4,5,7,8,1,9]
Cubre todos los arcos pero hay caminos primos como [7, 8, 1, 2, 3, 7] (18) o  [8, 1, 2, 3, 7, 8] (19) que no estan cubiertos por el TP



# Ejercicio 3:

#### A)
**Dominio y particion del mismo:**  
- 1<=d1<=31 
- 1<=d2<=31 
- 1<=m1<=m2<=12 
- 1<=a<=10000

**Casos especiales a considerar:**

Consideramos esta 4 caracteristicas ya que son las que no pueden ayudar a resaltar errores de la funcion  
	- Meses con 30 dias
	- Meses con 31 dias
	- Mes 2 con 28 dias
	- Anio bisiesto
	
**Particiones**

* Meses
	1. M130: {4,6,9,11}  // meses con 30 dias
	2. M131: {1,3,5,7,8,9,10,12} // meses con 31 dias
	3. M128: {2}  //febrero
	4. M130: {4,6,9,11}  // meses con 30 dias
	5. M131: {1,3,5,7,8,9,10,12} // meses con 31 dias
	6. M128: {2}  //febrero
* Dias
	1. D1A: {minInt,...,-1,0} 
	2. D1B: {1..28}
	3. D1C: {29}
	4. D1D: {30}
	5. D1E: {31}

	6. D2A: {1..28}
	7. D2B: {29}
	8. D2C: {30}
	9. D2D: {31}
	10. D2E: {32,...,maxInt}
	
* Bisiesto
	1. AB: {1,..,10000} && (a mod 4==0 || ((a mod 100==0 && a mod 400 != 0)) // anio bisiesto
	2. ANB: {1,..,10000} && !(a mod 4!=0 || ((a mod 100==0 && a mod 400 != 0)) // anio no bisiesto

**Base:**

- M131 M231 D1B D2A ANB {3,8,12,26,2003}
	
**Tests**

1. M131 M231 D1B D2A AB {3,8,12,26,2020}
2. M131 M231 D1B ANB D2B {3,8,12,2003,29}
3. M131 M231 D1B ANB D2C {3,8,12,2003,30}
4. M131 M231 D1B ANB D2D {3,8,12,2003,31}
5. M131 M231 D1B ANB D2E {3,8,12,2003,57}
6. M131 M231 D2A ANB D1A {3,8,26,2003,-299}
7. M131 M231 D2A ANB D1C {3,8,26,2003,29}
8. M131 M231 D2A ANB D1D {3,8,26,2003,30}
9. M131 M231 D2A ANB D1E {3,8,26,2003,31}
10. M231 D1B D2A ANB M130 {8,12,26,2003,4}
11. M231 D1B D2A ANB M128 {8,12,26,2003,2}
12. M131 D1B D2A ANB M230 {3,12,26,2003,4}
13. M131 D1B D2A ANB M228 {1,12,26,2003,2}

#### B)
Si queremos definir tests para lograr cobertura de clausulas en la funcion cal lo primero que debemos identificar son las clasulas de la misma es decir las que se encuentran en los if , ciclos y demas condicionales, para asi generar casos de test en los cuales las clausulas tomaran todos los valores posibles(True and false) y nos quedaremos con un test de de cada uno , es decir uno que haga true al if y otro false.

* Si miramos la funcion lo primero que encontramos es un if and else , en este sus clausula es:  
	* (month1==month2) -> True || False  
		<sup>*la cual puede tomar estos 2 valores ^*</sup>  
	**Un ejemplo que de True seria month1 == 1 && month2 == 1**

* En el caso de que la primer clausula tome el valor False nos encontramos con otro if and else el cual tiene 3 clausulas:  
	* ((m4 != 0 || (m100 == 0 && m400 != 0)))  
		<sup>*Donde m4 m100 y m400 pueden tomar los valores True && false*</sup>  
	
	1. (m4!=0) -> True donde m4 == year%4  
	2. (m4!=0) -> False donde m4 == year%4  
	3. (m100==0) -> True donde m100 == year%100 
	4. (m100==0) -> False donde m100 == year%100
	5. (m400!=0) -> True donde m400 == year%400
	6. (m400!=0) -> False donde m400 == year%400
	
	**Los test que podriamos realizar serian:**

	1. True -> (True)||(??&&??)  
		> como la condicion es un || nos da igual el resultado de && por lo tanto hacemos un 						 solo test con **year == 1999** 
	2. False-> (False) || (True&&false)  
		> este ocurre cuando **year == 400**

* Por ultimo tenemos un ciclo el cual tiene una clasula:

	Esta sera True si **month1<=month2**   
	Y sera False si **month1>month2**


#### C)
Para  Aplicar el criterio CACC debemos armar tablas verdad con todos los valores posibles de las clausulas de cada condicional , el primer if no tiene sentido analizarlo ya que al tener solo 1 clausula , tiene 2 valores posibles (true y false), el analisis del segundo if (dentro del else) se puede ver en la siguiente tabla:

| (m4!=0) | (m100==0) | (m400!=0) | ((m100==0)&&(m400!=0)) | ((m4!=0)or((m100==0)&&(m400!=0))) | m4 | m100 | m400 |
|:-------:|:---------:|:---------:|:----------------------:|:---------------------------------:|:--:|:----:|:----:|
| T       | T         | T         | T                      | T                                 | 0  | 0    | 0    |
| T       | T         | F         | F                      | T                                 | 1  | 0    | 0    |
| T       | F         | T         | F                      | T                                 | 1  | 0    | 0    |
| T       | F         | F         | F                      | T                                 | 1  | 0    | 0    |
| F       | T         | T         | T                      | T                                 | 0  | 1    | 1    |
| F       | T         | F         | F                      | F                                 | 1  | 0    | 1    |
| F       | F         | T         | F                      | F                                 | 1  | 1    | 0    |
| F       | F         | F         | F                      | F                                 | 1  | 0    | 0    |

Por lo tanto los test que se pueden generar  en base al criterio serian  2,3,4,5(2 veces),6(2 veces),7(2 veces),8 pero podemos podar algunos de ellos.En primero lugar como el 5,6 y el 7  cumplen con 2 clasulas  podemos hacer 1 de cada uno.Luego debemos ver cuales de los los casos restantes son posibles, veamos:

* Caso 2 : **( T || (T && F ))**
> este caso no es posible ya que pide que el anio no sea divisible por 4 , sea divisible por 100 y sea divisible por 400 , pero como 400 es multiplo 4 , no hay numero que cumpla con esto.

* Caso 3 : **(T || (F && T))**
> este caso pide que el anio no sea divisible por 4 , no sea divisible por 100 , no sea divisible por 400 , un ejemplo para esto seria year == 3.  

* Caso 4 : **(T || (F && F))**
> este caso  pide que el anio no sea divisible por 4, no sea divisible por 100 y sea divisible por 400 , este no puede ocurrir ya como antes dijimos 400 es multiplo de 4.

* Caso 5 : **(F || (T && T)**
> este caso pide que el anio sea divisible por 4 , sea divisible por 100 y  no sea divisible por 400 , un ejemplo de esto seria year == 100

* Caso 6: **(F || (T && F))**
> este caso pide que anio sea divisible por 4 ,  divisible por 100 y divisible por 400 , un ejemplo de esto seria year==400

* Caso 7: **(F || (F && T))**
> este caso pide que el anio sea divisible por 4 , no sea divisible por 100 y no sea divisible por 400, un ejemplo de esto seria year == 8.

* Caso 8: **(F || (F && F))**
> este caso pide que el anio sea divisible por 4 , no sea divisible por 100 y sea divisible por 400 , este caso es parecido al 4 ya que como 100 es multiplo de 400 , no hay un numero que cumpla con lo pedido.

* En del caso del For, como esta dentro else, esto nos dice que los meses ya son distintos, por lo tanto, va a entrar si o si.
	
Para generar los test los dias que elijamos son indistintos , siempre respetando la pre condicion obvio.
	Casos de test :

		Casos meses iguales:

			Test 1: {1,20,1,24,2004,4}
	 	
	 	Casos meses distintos:
	 		
	 		Test 2: {4,20,7,24,3,95}
	 		Test 3: {4,20,7,24,100,95}
	 		Test 4: {4,20,7,24,400,95}
	 		Test 5: {4,20,7,24,8,95}


#### D)Coverage de los Test :
		CalcTest1 : 33.1%
		CalcTest2 : 13.4%
		CalcTest3 : 17.0%
		CalcTest4 : 25.9%
		Total : 73%
		
		-Los mutantes nos quedaron pendientes , no pudimos hacerlo andar en eclipse.
		
#### D) Test para mejorar cobertura:

	  	/*Test1 */{3,6,12,25,555,2323}
        	/*Test2 */{3,3,12,25,235,0}
        	/*Test3 */{4,27,12,25,2665,57}
       		/*Test4 */{3,16,12,25,823,284}
       		/*Test5 */{6,23,12,20,1,186}
        	/*Test6 */{8,23,12,20,666,125}
        	/*Test7 */{3,1,12,25,9999,299}
        	/*Test8 */ {10,3,12,25,10000,83}
        	/*Test9 */{11,12,12,25,11,43}
        	/*Test10 */{2,5,10,29,1,266}
	
#### F) 
	Al no poder hacer andar los  mututantes no pudimos ver cuales son equivalentes.

<!--stackedit_data:
eyJoaXN0b3J5IjpbNDA0MTQwMDcwLC0xNTM0NjQ2OTcyLDE4ND
E0MzQ5MTIsOTY1MDA4OTc1LC0xNzc0NTI3MTg4LC0xNDIzODk2
ODAsMzc5MzE5NzI4LC04NjUwNjY5NzQsNjI3ODY3NzYsMTIzMj
E1Mzg2MCw1MjE4MDUxOTEsLTQyMjc4ODQ4NiwtMTQ1MzQyMzI2
NiwxMzc1MTcyNjMyLDE5ODIxMzQ0MDAsMTc2Nzg3MTc3Niw3ND
E5NTgxNTJdfQ==
-->