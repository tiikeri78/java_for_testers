package my.test.sandbox;

public class MyFirstProgram {

public static void main(String[] args){

    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 6);

    System.out.println("Расстояние между точкамми с координатами " + "(" + p1.x + ", " + p1.y + ")" + " и " + "(" + p2.x + ", " +
            p2.y + ")" + " равно " + p1.distance(p2) + ".");
 }
}