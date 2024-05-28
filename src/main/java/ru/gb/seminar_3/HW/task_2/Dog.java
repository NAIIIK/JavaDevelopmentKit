package ru.gb.seminar_3.HW.task_2;

public class Dog extends Animal{
    private String someValue;

    public Dog(String someValue) {
        this.someValue = someValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Dog dog = (Dog) obj;
        return someValue.equals(dog.someValue);
    }
}
