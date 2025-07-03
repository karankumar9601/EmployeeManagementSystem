package com.controller;

import com.model.Employee;
import com.view.EmployeeView;
import com.DAO.EmployeeDAO;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
	private final EmployeeDAO dao;
	private final EmployeeView view;
	private final Scanner scanner;

	public EmployeeController() {
		dao = new EmployeeDAO();
		view = new EmployeeView();
		scanner = new Scanner(System.in);
	}

	public void startApp() {
		while (true) {
			view.showMenu();
			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {
			case 1:
				addEmployee();
				break;
			case 2:
				viewEmployees();
				break;
			case 3:
				updateEmployee();
				break;
			case 4:
				deleteEmployee();
				break;
			case 5:
				System.exit(0);
				System.out.println("Thank you.......");

			default:
				view.showMessage("Invalid option. Try again.");
			}
		}
	}

	private void addEmployee() {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter position: ");
		String position = scanner.nextLine();
		System.out.print("Enter salary: ");
		double salary = scanner.nextDouble();

		Employee emp = new Employee(0, name, position, salary);
		boolean success = dao.addEmployee(emp);
		view.showMessage(success ? "Employee added!" : "Failed to add employee.");
	}

	private void viewEmployees() {
		List<Employee> employees = dao.getAllEmployees();
		view.showEmployees(employees);
	}

	private void updateEmployee() {
		System.out.print("Enter employee ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // consume newline
		System.out.print("New name: ");
		String name = scanner.nextLine();
		System.out.print("New position: ");
		String position = scanner.nextLine();
		System.out.print("New salary: ");
		double salary = scanner.nextDouble();

		Employee emp = new Employee(id, name, position, salary);
		boolean success = dao.updateEmployee(emp);
		view.showMessage(success ? "Employee updated!" : "Update failed.");
	}

	private void deleteEmployee() {
		System.out.print("Enter employee ID to delete: ");
		int id = scanner.nextInt();
		boolean success = dao.deleteEmployee(id);
		view.showMessage(success ? "Employee deleted!" : "Delete failed.");
	}
}
