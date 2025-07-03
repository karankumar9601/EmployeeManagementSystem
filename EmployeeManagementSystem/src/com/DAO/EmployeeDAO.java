package com.DAO;

import java.sql.*;
import java.util.*;

import com.model.Employee;

public class EmployeeDAO {

	private final String url = "jdbc:mysql://localhost:3306/employee_project";
	private final String USER = "root"; // Change if different
	private final String PASS = "root"; // Change if needed
	String driver_class = "com.mysql.cj.jdbc.Driver";

	private Connection getConnection() throws SQLException {
		try {

			Class.forName(driver_class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return DriverManager.getConnection(url, USER, PASS);
	}

	public boolean addEmployee(Employee emp) {
		String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, emp.getName());
			stmt.setString(2, emp.getPosition());
			stmt.setDouble(3, emp.getSalary());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM employees";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("position"),
						rs.getDouble("salary"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateEmployee(Employee emp) {
		String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, emp.getName());
			stmt.setString(2, emp.getPosition());
			stmt.setDouble(3, emp.getSalary());
			stmt.setInt(4, emp.getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteEmployee(int id) {
		String sql = "DELETE FROM employees WHERE id=?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
