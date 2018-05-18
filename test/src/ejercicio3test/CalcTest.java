package src.ejercicio3test;

import Ejercicio3.Cal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)
public class CalcTest{

	public int dia1,dia2,mes1,mes2,anio,expected;

	public CalcTest(int m1, int d1, int m2, int d2, int a,int expected){

		this.mes1 = m1;
		this.dia1 = d1;
		this.mes2 = m2;
		this.dia2 = d2;
		this.anio = a;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {	// Base {3,12,8,26,2003}
		return Arrays.asList (new Object [][] { /* Test Base */ {3,12,8,26,2003,167},
												/* Test 1 */ {3,12,8,26,2020,167},
												/* Test 2 */ {3,12,8,29,2003,170},
												/* Test 3 */ {3,12,8,30,2003,171},
												/* Test 4 */ {3,12,8,31,2003,172},
												/* Test 5 */ {3,12,8,1,2003,142},
												/* Test 6 */ {3,12,8,26,2003,167},
												/* Test 7 */ {3,1,8,26,2003,142},
												/* Test 8 */ {3,29,8,26,2003,150},
												/* Test 9 */ {3,30,8,26,2003,149},
												/* Test 10 */ {4,31,8,26,2003,117},
												/* Test 11 */ {2,12,8,26,2003,195},
												/* Test 12 */ {3,12,4,26,2003,45},
												/* Test 13 */ {1,12,2,26,2003,45},
											}); 
	
	};

	@Test
	public void TestEjA() {
		assertTrue ("Wrong number of days", expected == Cal.cal(mes1, dia1, mes2, dia2, anio));
	};




}
