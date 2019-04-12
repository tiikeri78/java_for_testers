package my.test.sandbox;

public class MyFirstProgram {

public static void main(String[] args){

    Point p = new Point(2, 3, 4, 6);

    System.out.println("Расстояние между точкамми с координатами " + "(" + p.x1 + ", " + p.y1+ ")" + " и " + "(" + p.x2 + ", " + p.y2 + ")" + " равно " + p.distance() + ".");
 }
}