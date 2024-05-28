package ru.gb.seminar_3.HW;

import ru.gb.seminar_3.HW.task_2.Animal;
import ru.gb.seminar_3.HW.task_2.ArrayComparator;
import ru.gb.seminar_3.HW.task_2.Cat;
import ru.gb.seminar_3.HW.task_2.Dog;

public class Main {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
    }

    public static void task1() {
        System.out.println(Calculator.sum(3, 5));
        System.out.println(Calculator.substract(3, 5));
        System.out.println(Calculator.multiply(3, 5));
        System.out.println(Calculator.divide(3, 1));
        try {
            System.out.println(Calculator.divide(3, 0));
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is restricted");
        }
    }

    public static void task2() {
        Animal[] animals1 = {new Cat("Murzik"), new Cat("Vasya"), new Dog("Bobik")};
        Animal[] animals2 = {new Cat("Murzik"), new Cat("Vasya"), new Dog("Bobik")};
        Animal[] animals3 = {new Cat("Murzik"), new Cat("Vasya"), new Cat("Bobik")};
        Animal[] animals4 = {new Cat("Murzik"), new Dog("Bob")};
        Animal[] animals5 = {new Cat("Murzik"), new Dog("Bob"), new Cat("Vasya")};
        ArrayComparator comparator = new ArrayComparator();
        System.out.println(comparator.compareArrays(animals1, animals2));
    }

    public static void task3() {
        Pair<Integer, String> pair = new Pair<>(35, "Thirty five");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }
}
