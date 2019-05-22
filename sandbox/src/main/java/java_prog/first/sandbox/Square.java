package java_prog.first.sandbox;

public class Square {  //класс

    public double l;  //атрибут

    public Square(double l) {
        // l = len;
        this.l = l; // указание атрибута l которому присваивается значение переменной l
    }  //конструктор

    public double area() {  //метод
        return this.l*this.l;
    }
}
