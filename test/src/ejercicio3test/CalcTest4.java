package src.ejercicio3test;


import Ejercicio3.Cal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith (Parameterized.class)
public class CalcTest4 {

    public int dia1, dia2, mes1, mes2, anio, expected;


    public CalcTest4(int m1, int d1, int m2, int d2, int a, int expected) {

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
                /*Test0 */{3,6,12,25,555,2323},
                /*Test1 */{3,3,12,25,235,0},
                /*Test2 */{4,27,12,25,2665,57},
                /*Test3 */{3,16,12,25,823,284},
                /*Test4 */{6,23,12,20,1,186},
                /*Test5 */{8,23,12,20,666,125},
                /*Test6 */{3,1,12,25,9999,299},
                /*Test7 */ {10,3,12,25,10000,83},
                /*Test8 */{11,12,12,25,11,43},
                /*Test9 */{2,5,10,29,1,266}

        });
    }

    @Test
    public void TestEjE() {
        assertTrue("Wrong number of days", expected == Cal.cal(mes1, dia1, mes2, dia2, anio));
    }

    ;
}