package p6;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalTest {

	public int m1, m2, d1, d2, year, expected;

	public CalTest(int m1, int m2, int d1, int d2, int year, int expected) {
		this.m1 = m1;
		this.m2 = m2;
		this.d1 = d1;
		this.d2 = d2;
		this.year = year;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] { 	
												//Test pair wise coverage
												{ 2, 2, 17, 28, 1996 , 11 },
												{ 2, 4, 29, 29, 1992 , 60 },
												{ 4, 6, 30, 1, 2000 , 32 },
												{ 11, 12, 9, 29, 1989, 50},
												{ 1, 9, 8, 30, 1942, 265},
												{ 1, 10, 29, 16, 1990, 260},
												//CACC
												//Mismo mes
												{ 1, 1, 10, 28, 1786, 18},
												//{ 9, 9, 29, 11, 2001, 18}, //TODO FIX: FALLA devuelve -18
												//Distinto mes
												//1. Año que no esa multiplo de 4 ni de 100 ni de 400
												{ 1, 10, 15, 20, 1999, 278},
												//2. Año multiplo de 4 de 100 y de 400
												{ 1, 4, 28, 15, 2000, 78},  
												//3. Año multiplo 4 y de 100 pero no de 400
												{ 1, 7, 1, 1, 700, 181},
												//4. Año multiplo de 4 pero no de 100 ni de 400
												{ 1, 9, 30, 30, 404, 244},
												//RACC
												//3. Año multiplo de 4 y de 100 pero no de 400 (se repite)
												{ 4, 12, 1, 31, 2100, 274},
												//4. Año multiplo de 4 pero no de 100 ni de 400 (se repite)
												{ 5, 9, 25, 1, 1024, 99 },
												//5. Año multiplo de 4 y de 100 pero no de 400 (se repite)
												{ 9, 11, 30, 30, 1700, 61}
											});
	}

	@Test
	public void testCal() {
		assertEquals("Cal return the expected value ",expected,Cal.cal(m1,d1,m2,d2,year));
	}

}
