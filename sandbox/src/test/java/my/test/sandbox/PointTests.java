package my.test.sandbox;

import org.junit.Test;
import org.testng.AssertJUnit;

public class PointTests {

    @Test
    //Позитивный тест
    public void testDistance(){

        Point p1 = new Point(5, 5);
        Point p2 = new Point(2, 1);
        AssertJUnit.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    //Тест на "0"
    public void testDistanceNull(){

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        AssertJUnit.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    //Негативный тест
    public void testDistanceNegative(){

        Point p1 = new Point(0, 3);
        Point p2 = new Point(-1.9, 11);
        assert(p1.distance(p2)!= 2.0);
    }
}
