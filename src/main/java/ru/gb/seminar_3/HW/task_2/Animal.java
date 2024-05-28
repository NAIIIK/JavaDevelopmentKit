package ru.gb.seminar_3.HW.task_2;

public class Animal {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Animal animal = (Animal) obj;
        return true;
    }
}
