package ru.gb.seminar_4.HW;

public class Main {
    public static void main(String[] args) {
        Employee employee1= new Employee(123456789, "Michael", 4);
        Employee employee2= new Employee(213456789, "Johnathan", 6);
        Employee employee3= new Employee(321456789, "Victor", 2);
        Employee employee4= new Employee(432156789, "Anne", 4);
        Employee employee5= new Employee(543216789, "Michael", 6);
        Employee employee6= new Employee(654321789, "Andrew", 5);
        Employee employee7= new Employee(765432189, "Anastasia", 1);
        Employee employee8= new Employee(876543219, "George", 9);
        Employee employee9= new Employee(987654321, "Sarah", 12);

        EmployeeRegister register = new EmployeeRegister();
        register.addEmployee(employee1);
        register.addEmployee(employee2);
        register.addEmployee(employee3);
        register.addEmployee(employee4);
        register.addEmployee(employee5);
        register.addEmployee(employee6);
        register.addEmployee(employee7);
        register.addEmployee(employee8);
        register.addEmployee(employee9);

        System.out.println(register.getEmployeeByExperience(4));
        System.out.println("*******************");
        System.out.println(register.getById(2));
        System.out.println("*******************");
        System.out.println(register.getPhoneNumberByName("Michael"));

    }
}
