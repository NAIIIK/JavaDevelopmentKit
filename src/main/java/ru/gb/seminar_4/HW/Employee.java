package ru.gb.seminar_4.HW;

import lombok.Data;

@Data
public class Employee {

    private int id;
    private int phoneNumber;
    private String name;
    private int experience;

    public Employee(int phoneNumber, String name, int experience) {
        id = -1;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }
}
