package java_prog.first.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("user");

		Square s = new Square(5); //с помощью конструктора атрибуты передаются в объект, не объявляя отдельно
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area()); //обращение к методу объекта

		Rectangle r = new Rectangle(); //создание объекта класс
		r.a = 4;  //заполняются атрибуты
		r.b = 6;
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
	}


	public static void hello(String somebody) {
		System.out.println("Hello," + somebody + "!");
	}


	public static double area(Rectangle r) { // в функцию передаем объект
		return r.a*r.b;
	}
} 