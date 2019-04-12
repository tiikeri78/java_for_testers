package my.test.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MyFirstProgram {

public static void main(String[] args){

    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 6);

    System.out.println("Расстояние между точкамми с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно " + distance(p1, p2) + ".");
 }

    public static double distance(Point p1, Point p2){

        return sqrt(pow((p2.x-p1.x), 2)+pow((p2.y-p1.y), 2));
    }
}