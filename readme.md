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
```
{
	minTamañoLista : 0,
	maxTamañoLista : 5,
	minInt : -5,
	maxInt : 5,
	cantidadListas: 70 //aprox. 6 posibles tamaños de listas * 11 valores de enteros
}
```
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
<summary> 2. Condicion de limite en remove y fastRemove</summary>  

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


* Quitar los test equivalentes aumenta el puntaje de cobertura de mutación, por el hecho que se decrementa la cantidad total de mutantes generados de los cuales, los equivalentes removidos, siempre sobrevivian al producir la misma salida que la esperada.

# Ejercicio 2
## Grafo

#### A)
![alt text](https://image.ibb.co/etiitJ/Captura_de_pantalla_de_2018_06_03_16_56_39.png "Grafo de flujo de control")
#### B)
## Requerimientos de test
#### Node Coverage
* El conjunto de TP deben visitar todos los nodos
	* RT={0,1,2,3,4,5,6,7,8,9,10}
#### Edge Coverage
* El conjunto de TP deben pasar por todas las aritas 
	* RT={(0,1), (1,2), (1,9), (2,3), (2,8), (3,4), (4,5), 
		 (4,8), (5,6), (5,7), (6,8), (7,4), (8,1), (9,10)}
### Prime Path Coverage
* El conjunto de TP deben pasar, con o sin desvios/sidepaths, por los siguientes subcaminos:  
1. [0, 1, 2, 8]
2. [0, 1, 9, 10]
3. [1, 2, 8, 1]
4. [2, 8, 1, 2]
5. [4, 5, 7, 4]
6. [5, 7, 4, 5]
7. [7, 4, 5, 7]
8. [8, 1, 2, 8]
9. [2, 8, 1, 9, 10]
10. [0, 1, 2, 3, 4, 8]
11. [1, 2, 3, 4, 8, 1]
12. [2, 3, 4, 8, 1, 2]
13. [3, 4, 8, 1, 2, 3]
14. [4, 8, 1, 2, 3, 4]
15. [8, 1, 2, 3, 4, 8]
16. [0, 1, 2, 3, 4, 5, 7]
17. [2, 3, 4, 8, 1, 9, 10]
18. [5, 7, 4, 8, 1, 2, 3]
19. [5, 7, 4, 8, 1, 9, 10]
20. [0, 1, 2, 3, 4, 5, 6, 8]
21. [1, 2, 3, 4, 5, 6, 8, 1]
22. [2, 3, 4, 5, 6, 8, 1, 2]
23. [3, 4, 5, 6, 8, 1, 2, 3]
24. [4, 5, 6, 8, 1, 2, 3, 4]
25. [5, 6, 8, 1, 2, 3, 4, 5]
26. [6, 8, 1, 2, 3, 4, 5, 6]
27. [6, 8, 1, 2, 3, 4, 5, 7]
28. [7, 4, 5, 6, 8, 1, 2, 3]
29. [7, 4, 5, 6, 8, 1, 9, 10]
30. [8, 1, 2, 3, 4, 5, 6, 8]
31. [2, 3, 4, 5, 6, 8, 1, 9, 10]

* Para obtener los caminos primos se generan todos los caminos simples empezando por los de longitud 0 (nodos individuales), se agregan nuevos caminos extendiendo su longitud, aquellos caminos que alcanzen un nodo final son marcados(!) al igual que aquellos caminos que comienzan y terminan con el mismo nodo(ciclos *), los caminos marcados no se siguen extendiendo, el procedimiento termina cuando ya no se puedan generar caminos simples.
Una vez generados todos los caminos simples, se eligen como primos aquellos que **no sean un subcamino** de otro, empezando primero por los de mayor longitud.


| 0       | 1         | 2           | 3             | 4               | 5                 | 6                   | 7                     | 8                       |
|---------|-----------|-------------|---------------|-----------------|-------------------|---------------------|-----------------------|-------------------------|
| 0       | 0-1       | 0-1-2       | 0-1-2-3       | 0-1-2-3-4       | 0-1-2-3-4-5       | 0-1-2-3-4-5-6       | **0-1-2-3-4-5-6-8!**  | **2-3-4-5-6-8-1-9-10!** |
| 1       | 1-2       | 0-1-9       | **0-1-2-8!**  | 1-2-3-4-5       | 1-2-3-4-5-6       | **0-1-2-3-4-5-7!**  | **1-2-3-4-5-6-8-1***  |                         |
| 2       | 1-9       | 1-2-3       | **0-1-9-10!** | 2-3-4-5-6       | ~~1-2-3-4-5-7!~~  | 1-2-3-4-5-6-8       | **2-3-4-5-6-8-1-2***  |                         |
| 3       | 2-3       | 1-2-8       | 1-2-3-4       | ~~2-3-4-5-7!~~  | 2-3-4-5-6-8       | 2-3-4-5-6-8-1       | 2-3-4-5-6-8-1-9       |                         |
| 4       | 2-8       | ~~1-9-10!~~ | **1-2-8-1***  | **2-8-1-9-10!** | 3-4-5-6-8-1       | 3-4-5-6-8-1-2       | **3-4-5-6-8-1-2-3***  |                         |
| 5       | 3-4       | 2-3-4       | 2-3-4-5       | 3-4-5-6-8       | 4-5-6-8-1-2       | 3-4-5-6-8-1-9       | **3-4-5-6-8-1-9-10!** |                         |
| 6       | 4-5       | 2-8-1       | **2-8-1-2***  | 4-5-6-8-1       | 4-5-6-8-1-9       | 4-5-6-8-1-2-3       | **4-5-6-8-1-2-3-4***  |                         |
| 7       | 5-6       | 3-4-5       | 2-8-1-9       | 5-6-8-1-2       | 5-6-8-1-2-3       | ~~4-5-6-8-1-9-10!~~ | **5-6-8-1-2-3-4-5***  |                         |
| 8       | 5-7       | 4-5-6       | 3-4-5-6       | 5-6-8-1-9       | ~~5-6-8-1-9-10!~~ | 5-6-8-1-2-3-4       | **6-8-1-2-3-4-5-6***  |                         |
| 9       | 6-8       | 4-5-7       | ~~3-4-5-7!~~  | 6-8-1-2-3       | 6-8-1-2-3-4       | 6-8-1-2-3-4-5       | **6-8-1-2-3-4-5-7!**  |                         |
| ~~10!~~ | 7-4       | 5-6-8       | 4-5-6-8       | 6-8-1-9-10!     | 7-4-5-6-8-1       | 7-4-5-6-8-1-2       | **7-4-5-6-8-1-2-3!**  |                         |
|         | 8-1       | 5-7-4       | **4-5-7-4***  | 7-4-5-6-8       | 8-1-2-3-4-5       | 7-4-5-6-8-1-9       | **7-4-5-6-8-1-9-10!** |                         |
|         | ~~9-10!~~ | 6-8-1       | 5-6-8-1       | 8-1-2-3-4       |                   | ~~8-1-2-3-4-5-7!~~  | **8-1-2-3-4-5-6-8***  |                         |
|         |           | 7-4-5       | **5-7-4-5***  |                 |                   | 8-1-2-3-4-5-6       |                       |                         |
|         |           | 8-1-2       | 6-8-1-2       |                 |                   |                     |                       |                         |
|         |           | 8-1-9       | 6-8-1-9       |                 |                   |                     |                       |                         |
|         |           |             | 7-4-5-6       |                 |                   |                     |                       |                         |
|         |           |             | **7-4-5-7***  |                 |                   |                     |                       |                         |
|         |           |             | **8-1-2-8***  |                 |                   |                     |                       |                         |
|         |           |             | 8-1-2-3       |                 |                   |                     |                       |                         |
|         |           |             | ~~8-1-9-10!~~ |                 |                   |                     |                       |                         |



#### C)
Caminos de test que consiguen cobertura de nodos pero no de arcos:
* TP1: [0,1,2,3,4,5,6,8,1,9],[0,1,2,3,4,5,7,4,8,1,9,10]
	* No pasa por el arco (2,8)
* TP2: [0,1,2,3,4,5,7,4,5,6,8,1,9,10]
	* No pasa por los arcos (2,8) y (4,8)
#### D) 
Camino de test que consigue cobertura de arcos pero no de caminos primos:
 * TP1: [0,1,2,3,4,5,7,4,8,1,9,10],[0,1,2,8,1,9,10],[01,2,3,4,5,6,8,1,9,10]
	 * No cubre el camino primo [7,4,5,7] entre otros pero cubre todos los arcos
 * TP2: [0,1,2,3,4,5,7,4,5,6,8,1,9,10] , [0,1,2,3,4,8,1,2,8,1,9,10]
	 * No cubre el camino primo [5, 6, 8, 1, 2, 3, 4, 5]  entre otros pero cubre todos los arcos
#### E)
 #### Parametros de entrada
 * subject: char[] 
	 > Una cadena a observar
 * pattern: char[]
	 > Cadena a verificar si es subcadena de subject
#### Caracteristicas
* C1: pattern es subcadena de subject
	* T: True
	* F: False
* C2: Tamaño de la cadena pattern respecto a la cadena subject
	* Minor: pattern < subject
	* Equal: pattern = subject
	* Greater: pattern > subject
* C3: pattern es cadena vacia
	* T: true
	* F: false
* C4: subject es cadena vacia	
	* T: true
	* F: false 
#### Algunas restricciones
* C2.Greater **=>** C1.F && C4.F
* C1.T && C3.F **=>** C4.F
* C3.T **=>** C1.T
* C3.T && C4.T **=>** C2.Equal && C1.T
* C3.F && C4.T **=>** C1.F && C2.Greater
## Criterio de PEE Base Choice Coverage

* Elegimos como base el caso ideal en que pattern es una subcadacadena no vacia mas chica que subject:
	* C1.T, C2.Minor, C3.F, C4.F

Cantidad de tests con el criterio: 6 

| Base              | C4                      | C3                      | C2                        | C1                      |
|---------------------|-------------------------|-------------------------|---------------------------|-------------------------|
| C1.T C2.Minor C3.F C4.F | C1.T C2.Minor C3.F C4.T | C1.T C2.Minor C3.T C4.F | C1.T C2.Equal C3.F C4.F   | C1.F C2.Minor C3.F C4.F |
|                         |                         |                         | C1.T C2.Greater C3.F C4.F |                         |

Teniendo en cuenta las restricciones los casos **C1.T C2.Greater C3.F C4.F** y **C1.T C2.Minor C3.F C4.T** no son factibles, cambiamos algun valor base a otro no base hasta encontrar una combinacion factible.

**Los test elegidos para cubrir BCC quedan:**
1. C1.T C2.Minor C3.F C4.F
2. C1.T C2.Equal C3.T C4.T
3. C1.T C2.Minor C3.T C4.F
4. C1.T C2.Equal C3.F C4.F
5. C1.F C2.Greater C3.F C4.F
6. C1.F C2.Minor C3.F C4.F

# Ejercicio 3:

#### A) Modelo del espacio de entradas funcion cal

#### Parametros de entrada
 * month1 : int
	 > Representa un mes del año  
 * day1: int
	 > Representa un dia del mes
 * month2 : int
	 > Representa un mes del año
 * day2: int
	 > Representa un dia del mes
* year : int
	> Representa un año

#### Precondiciones

* day1 y day2 deben ser del mismo año
* 1 <= month1, month2 <= 12
* 1 <= day1, day2 <= 31
* month1 <= month2
* 1 <= year <= 10000

#### Caracteristicas

* M1:  Cantidad de dias del mes1
	* 28 o 29 (FEB)
	* 30	(ENE, MAR, MAY, JUL, AGO, OCT, DIC)
	* 31 (ABR, JUN, SEPT, NOV)
* M2:  Cantidad de dias del mes2
	* 28 o 29 (FEB)
	* 30 (ENE, MAR, MAY, JUL, AGO, OCT, DIC)
	* 31 (ABR, JUN, SEPT, NOV)
* D1:
	* A: <= 28
	* B: 29
	* C: 30 o 31
* D2: 
	* A: <= 28
	* B: 29
	* C: 30 o 31
* B: Año bisiesto
	* T
	* F

#### Algunas restricciones
* B = False && M1 == 28-29 **=>** D1 != B
* B = False && M2 == 28-29 **=>** D2 != B 
* M1 = 28-29 **<=>** D1 != C
* M2 = 28-29 **<=>** D2 != C



## Cobertura Pair-Wise
**Cantidad de tests a generar**
#Bloques(Caracteristica con mas bloques A) + #Bloques(Caracteristica con mas bloques B) = 3 * 3 (A != B) = 9 Tests
|  Test  |   M1  |   M2  | D1 | D2 | Bisiesto |
|:------:|:-----:|:-----:|:--:|:--:|:--------:|
|  **.1** | 28-29 | 28-29 |  A |  A |     T    |
|  **.2** | 28-29 |   30  |  B |  B |     T    |
|  ~~**.3**~~ | 28-29 |   31  |  C |  C |     F    |
|  ~~**.4**~~ |   30  | 28-29 |  B |  C |     F    |
|  **.5** |   30  |   30  |  C |  A |     T    |
|  **.6** |   30  |   31  |  A |  B |     F    |
|  ~~**.7**~~ |   31  | 28-29 |  C |  B |     F    |
|  **.8** |   31  |   30  |  A |  C |     F    |
|  **.9** |   31  |   31  |  B |  A |     T    |


* Teniendo en cuenta las restricciones descartamos los test
	* 7 : B= False && M2 = 28-29 pero D2 == B
	* 3 : M1 = 28-29 pero D1 == C
	* 4 : M2 = 28-29 pero D2 == C

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
![Cobertura Correlacionada de Clausulas Activas](https://image.ibb.co/cxsb7o/imagen.png =800x300)

#### D)
![Cobertura Restringida de Clausulas Activas](https://image.ibb.co/ckf7DT/imagen.png =800x300)
<!--stackedit_data:
eyJoaXN0b3J5IjpbODY5Njg2MzQ3LC0yMzIwNzE3MSwxNzA4MT
kyNjQ4LC0zMTk4Mjg3NjAsLTM2ODU5ODQ5NiwtMTgzMTY2NjU2
NywtMTcxODEwMTgzNiwtNzM5MTUyNjAwLC0xMTY2NTk4ODkwLC
03NTc5Mjc0MTQsMjE4Mjk3MTAwLDIwNjczMzAxNzQsMzkxMDU0
NjM1LC0xNjMzMTg5ODM0LDExNjI0MDA2MTksLTYwMTAzNTExOS
wtMTA2MDEzMTM0MywtMzkwNDQzNTA5LC0xMTgwNjU5MzU4LDE2
MDc0OTQ3MThdfQ==
-->