import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import p6.CalTest;
import util.ArrayListTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  CalTest.class,
  ArrayListTest.class,
})

public class allTest {

}