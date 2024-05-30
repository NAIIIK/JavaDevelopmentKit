package ru.gb.seminar_4.HW;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRegister {
    List<Employee> employees;

    public EmployeeRegister() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setId(employees.size());
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployeeByExperience(int experience) {
        return employees.stream().filter(employee -> employee.getExperience() == experience).toList();
    }

    public List<Integer> getPhoneNumberByName(String name) {
        List<Integer> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }

    public Employee getById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) return employee;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("There are ")
                .append(employees.size())
                .append(" employees in the register:")
                .append("\n");
        for (Employee employee:employees) {
            stringBuilder.append(employee)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
