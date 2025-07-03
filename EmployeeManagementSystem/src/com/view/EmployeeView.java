package com.view;

import com.model.Employee;

import java.util.List;

public class EmployeeView {
    public void showMenu() {
        System.out.println("\n=== Employee Management System ===");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public void showEmployees(List<Employee> employees) {
        System.out.println("\n--- Employee List ---");
        for (Employee emp : employees) {
            System.out.printf("ID: %d | Name: %s | Position: %s | Salary: %.2f\n",
                    emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary());
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
