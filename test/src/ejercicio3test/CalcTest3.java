package src.ejercicio3test;


import Ejercicio3.Cal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import java.util.*;

@RunWith (Parameterized.class)
public class CalcTest3 {

    public int  dia1, dia2, mes1, mes2, anio, expected;


    public CalcTest3(int m1, int d1, int m2, int d2, int a, int expected) {

        this.mes1 = m1;
        this.dia1 = d1;
        this.mes2 = m2;
        this.dia2 = d2;
        this.anio = a;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> parameters() {    // Base {3,12,8,26,2003}
        return Arrays.asList(new Object[][]{
                /* Test 1 */ {1, 20, 1, 24,2004,4},
                /* Test 2 */ {4, 20, 7, 24,3,95},
                /* Test 3 */ {4, 20, 7, 24,100,95},
                /* Test 4 */ {4, 20, 7, 24,400,95},
                /* Test 5 */ {4, 20, 7, 24,8,95},

        });
    }

    @Test
    public void TestEjC() {
        assertTrue ("Wrong number of days", expected == Cal.cal(mes1, dia1, mes2, dia2, anio));
    };


}