package ru.gb.seminar_3.HW.task_2;

public class Cat extends Animal {
    private String someValue;

    public Cat(String someValue) {
        this.someValue = someValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Cat cat = (Cat) obj;
        return someValue.equals(cat.someValue);
    }
}
