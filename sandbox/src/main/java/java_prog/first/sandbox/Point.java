package java_prog.first.sandbox;

public class Point {
    double x;
    double y;


    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

   /* public double distance(double x, double y) {
        return Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
    }*/

    public double distance(Point p0) {
        return Math.sqrt(Math.pow((this.x - p0.x), 2) + Math.pow((this.y - p0.y), 2));
    }
}