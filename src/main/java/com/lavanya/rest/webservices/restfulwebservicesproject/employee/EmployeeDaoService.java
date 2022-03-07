package com.lavanya.rest.webservices.restfulwebservicesproject.employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoService {
	private static List<Employee> employees = new ArrayList<>();

	private static int employeesCount = 3;

	static {
		employees.add(new Employee(1, "Adam", 24));
		employees.add(new Employee(2, "Eve", 30));
		employees.add(new Employee(3, "Jack", 36));
	}

	public List<Employee> findAll() {
		return employees;
	}
	
	public Employee save(Employee employee) {
		if (employee.getId() == null) {
			employee.setId(++employeesCount);
		}
		employees.add(employee);
		return employee;
	}

	public Employee findOne(int id) {
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}

	public Employee deleteById(int id) {
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if (employee.getId() == id) {
				iterator.remove();
				return employee;
			}
		}
		return null;
	}
	
	public Employee updateName(int id, String name) {
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if (employee.getId() == id) {
				employee.setName(name);
				return employee;
			}
		}
		return null;
	}
}
