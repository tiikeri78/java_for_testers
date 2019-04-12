package my.test.sandbox;

import org.junit.jupiter.api.Test;
import org.testng.AssertJUnit;

public class PointTests {

    @Test
    //Позитивный тест
    public void testDistance(){

        Point p = new Point(5, 5, 2, 1);
        AssertJUnit.assertEquals(p.distance(), 5.0);
    }

    @Test
    //Тест на "0"
    public void testDistanceNull(){

        Point p = new Point(0, 0, 0, 0);
        AssertJUnit.assertEquals(p.distance(), 0.0);
    }

    @Test
    //Негативный тест
    public void testDistanceNegative(){

        Point p = new Point(1, 1, 0, 0);
        assert(p.distance()!= 2.0);
    }
}
