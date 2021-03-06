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
123freeze![alt text](https://image.ibb.co/etiitJ/Captura_de_pantalla_de_2018_06_03_16_56_39.png "Grafo de flujo de control")
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


|  0  |     1     |      2      |       3       |        4        |         5         |          6          |           7           |            8            |
|:---:|:---------:|:-----------:|:-------------:|:---------------:|:-----------------:|:-------------------:|:---------------------:|:-----------------------:|
|  0  |    0-1    |    0-1-2    |    0-1-2-3    |    0-1-2-3-4    |    0-1-2-3-4-5    |    0-1-2-3-4-5-6    |  **0-1-2-3-4-5-6-8!** | **2-3-4-5-6-8-1-9-10!** |
|  1  |    1-2    |    0-1-9    |  **0-1-2-8!** |    1-2-3-4-5    |  **0-1-2-3-4-8!** |  ~~0-1-2-3-4-5-7!~~ |  **1-2-3-4-5-6-8-1*** |                         |
|  2  |    1-9    |    1-2-3    | **0-1-9-10!** |    1-2-3-4-8    |    1-2-3-4-5-6    |    1-2-3-4-5-6-8    |  **2-3-4-5-6-8-1-2*** |                         |
|  3  |    2-3    |    1-2-8    |    1-2-3-4    |    2-3-4-5-6    |  ~~1-2-3-4-5-7!~~ |    2-3-4-5-6-8-1    |    2-3-4-5-6-8-1-9    |                         |
|  4  |    2-8    | ~~1-9-10!~~ |  **1-2-8-1*** |  ~~2-3-4-5-7!~~ |  **1-2-3-4-8-1*** | ~~2-3-4-8-1-9-10!~~ |  **3-4-5-6-8-1-2-3*** |                         |
|  5  |    3-4    |    2-3-4    |    2-3-4-5    |    2-3-4-8-1    |    2-3-4-5-6-8    |    3-4-5-6-8-1-2    | ~~3-4-5-6-8-1-9-10!~~ |                         |
|  6  |    4-5    |    2-8-1    |    2-3-4-8    |   2-8-1-9-10!   |  **2-3-4-8-1-2*** |    3-4-5-6-8-1-9    |  **4-5-6-8-1-2-3-4*** |                         |
|  7  |    4-8    |    3-4-5    |  **2-8-1-2*** |    3-4-5-6-8    |    2-3-4-8-1-9    |    4-5-6-8-1-2-3    |  **5-6-8-1-2-3-4-5*** |                         |
|  8  |    5-6    |    3-4-8    |    2-8-1-9    |    3-4-8-1-2    |    3-4-5-6-8-1    | ~~4-5-6-8-1-9-10!~~ |  **6-8-1-2-3-4-5-6*** |                         |
|  9  |    5-7    |    4-5-6    |    3-4-5-6    |    3-4-8-1-9    |  **3-4-8-1-2-3*** |    5-6-8-1-2-3-4    |  **6-8-1-2-3-4-5-7!** |                         |
| 10! |    6-8    |    4-5-7    |  ~~3-4-5-7!~~ |    4-5-6-8-1    | ~~3-4-8-1-9-10!~~ |  **5-7-4-8-1-2-3!** |  **7-4-5-6-8-1-2-3!** |                         |
|     |    7-4    |    4-8-1    |    3-4-8-1    |    4-8-1-2-3    |    4-5-6-8-1-2    | **5-7-4-8-1-9-10!** | **7-4-5-6-8-1-9-10!** |                         |
|     |    8-1    |    5-6-8    |    4-5-6-8    | ~~4-8-1-9-10!~~ |    4-5-6-8-1-9    |    6-8-1-2-3-4-5    |  **8-1-2-3-4-5-6-8*** |                         |
|     | ~~9-10!~~ |    5-7-4    |  **4-5-7-4*** |    5-6-8-1-2    |  **4-8-1-2-3-4*** |    7-4-5-6-8-1-2    |                       |                         |
|     |           |    6-8-1    |    4-8-1-2    |    5-6-8-1-9    |    5-6-8-1-2-3    |    7-4-5-6-8-1-9    |                       |                         |
|     |           |    7-4-5    |    4-8-1-9    |    5-7-4-8-1    | ~~5-6-8-1-9-10!~~ |    8-1-2-3-4-5-6    |                       |                         |
|     |           |    7-4-8    |    5-6-8-1    |    6-8-1-2-3    |    5-7-4-8-1-2    |  ~~8-1-2-3-4-5-7!~~ |                       |                         |
|     |           |    8-1-2    |  **5-7-4-5*** | ~~6-8-1-9-10!~~ |    5-7-4-8-1-9    |                     |                       |                         |
|     |           |    8-1-9    |    5-7-4-8    |    7-4-5-6-8    |    6-8-1-2-3-4    |                     |                       |                         |
|     |           |             |    6-8-1-2    |    7-4-8-1-2    |    7-4-5-6-8-1    |                     |                       |                         |
|     |           |             |    6-8-1-9    |    7-4-8-1-9    |  ~~7-4-8-1-2-3!~~ |                     |                       |                         |
|     |           |             |    7-4-5-6    |    8-1-2-3-4    | ~~7-4-8-1-9-10!~~ |                     |                       |                         |
|     |           |             |  **7-4-5-7*** |                 |    8-1-2-3-4-5    |                     |                       |                         |
|     |           |             |    7-4-8-1    |                 |  **8-1-2-3-4-8*** |                     |                       |                         |
|     |           |             |    8-1-2-3    |                 |                   |                     |                       |                         |
|     |           |             |  **8-1-2-8*** |                 |                   |                     |                       |                         |
|     |           |             | ~~8-1-9-10!~~ |                 |                   |                     |                       |                         |



#### C)
Caminos de test que consiguen cobertura de nodos pero no de arcos:
* TP1: [0,1,2,3,4,5,6,8,1,9,10],[0,1,2,3,4,5,7,4,8,1,9,10]
	* No pasa por el arco (2,8)
* TP2: [0,1,2,3,4,5,7,4,5,6,8,1,9,10]
	* No pasa por los arcos (2,8) y (4,8)
#### D) 
Camino de test que consigue cobertura de arcos pero no de caminos primos:
 * TP1: [0,1,2,3,4,5,7,4,8,1,9,10],[0,1,2,8,1,9,10],[01,2,3,4,5,6,8,1,9,10]
	 * No cubre el camino primo [7,4,5,7] entre otros pero cubre todos los arcos
 * TP2: [0,1,2,3,4,5,7,4,5,6,8,1,9,10] , [0,1,2,3,4,8,1,2,8,1,9,10]
	 * No cubre el camino primo [5, 6, 8, 1, 2, 3, 4, 5] ni [6, 8, 1, 2, 3, 4, 5, 6]  entre otros pero cubre todos los arcos
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
	* 28 o 29 (2.FEB)
	* 30	(4.ABR, 6.JUN, 9.SEPT, 11.NOV)
	* 31 (1.ENE, 3.MAR, 5.MAY, 7.JUL, 8.AGO, 10,OCT, 12.DIC)
* M2:  Cantidad de dias del mes2
	* 28 o 29 (2.FEB)
	* 30	(4.ABR, 6.JUN, 9.SEPT, 11.NOV)
	* 31 (1.ENE, 3.MAR, 5.MAY, 7.JUL, 8.AGO, 10,OCT, 12.DIC)
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

## Predicado y variables
```java
//Params month2 y month1
month2 == month1
```
* 2 tests para predicado atomico
	1. month1= 9, month2 = 9
	2. month1= 2, month2 = 9
```java
//Parametro year
int m4 = year % 4;
int m100 = year % 100;
int m400 = year % 400;
(m4 != 0) || ((m100 == 0) && (m400 != 0)
```
* Consideraciones
	* M100 = 0 -> M4 = 0
	* M400 = 0 -> M4 = 0 && M100 = 0
	
![Cobertura Correlacionada de Clausulas Activas](https://image.ibb.co/gH9sV8/imagen.png )

* Quitando duplicados nos quedan 4 test para satisfacer CACC sobre
	1. Año que no esa multiplo de 4 ni de 100 ni de 400: **1999**
	2. Año multiplo de 4 de 100 y de 400: **2000**
	3. Año multiplo 4 y de 100 pero no de 400: **700**
	4. Año multiplo de 4 pero no de 100 ni de 400: **404**
	

#### C)

![Cobertura Restringida de Clausulas Activas](https://image.ibb.co/ckf7DT/imagen.png)
* Quitando duplicados quedan 5 test para satisfacer RACC, algunos de ellos no son posibles lograr:
	1. No es posible lograr (Año multiplo de 400 implica que es multiplo de 100)
	2. Idem a 1 (Año multiplo de 400 implica que es multiplo de 100 y de 4)
	3. Año multiplo de 4 y de 100 pero no de 400: **2100** 
	4. Año multiplo de 4 pero no de 100 ni de 400: **1024**
	5. Año multiplo de 4 y de 100 pero no de 400: **1700**

#### D)
Cuando medimos cobertura de ramas y puntaje de mutación con Pitest sobre la clase Cal con los test propuestos los metodos **main** y **getN** no estan cubiertos, le decimos a Pitest que ignore estos metodos para Mutación, la salida de pitest esta en la carpeta Out.

* Cobertura de lineas en cal:
	* 22% considerando los metodos main y getN
	* 100% solo considerando el metodo cal
* Puntaje de mutación(Con mutadores por defecto):
	* 30% considerando los metodos main y getN
	* 100% solo considerando el metodo cal
```j
================================================================================
- Statistics
================================================================================
>> Generated 17 mutations Killed 17 (100%)
>> Ran 51 tests (3 tests per mutation)
```

#### E)
* Se supuso que las entradas satisfacian la precondición pero se podria cambiar el modelo de espacio de entradas  para contemplar entradas invalidas (Dia invalido del mes, meses fuera de rango, valores negativos, etc)
* Otra caracteristica que no se tiene en cuenta es si dia1 es mas chico que dia2 y si los meses son iguales, hay un error para cuando los meses son iguales y dia1 > dia2 (retorna valor negativo), con CACC se cubre el caso que los meses sean iguales, pero satisfacer esta cobertura no asegura probar el caso en que dia1 > dia2
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEzMzY3NzgzNDQsLTQwNjMxMDU3OCwtMT
k2MjA2NjI0LDQ0NjIzODcxMCwxMTQzNjQ1NzQwLC0zODEzNzY4
MjQsMTczOTA3NDU2LC0xNjYwMTg2NzAxLC04MDI4NDk1MTcsLT
E0ODM5MzY4MzAsMTYzMDUzMzc2NSwxMDMzNzk4MzEyLC03NDM3
NjEyMzIsLTc0Mzc2MTIzMiw4MDMwNTIwMCwxNTU0NjYyMzYyLD
E0NzgwMTg2MzUsLTE0OTMwNjA3MDIsMTY4MzExMTYzLDM3MTMw
NzI0NF19
-->
