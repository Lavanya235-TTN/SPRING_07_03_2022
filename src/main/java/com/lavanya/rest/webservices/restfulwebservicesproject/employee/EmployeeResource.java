package com.lavanya.rest.webservices.restfulwebservicesproject.employee;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lavanya.rest.webservices.restfulwebservicesproject.employee.exception.UserNotFoundException;

@RestController
public class EmployeeResource {
	
	@Autowired
	private EmployeeDaoService employeeDaoService;
	
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees() {
		return employeeDaoService.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee retrieveEmployee(@PathVariable int id) {
		Employee employee = employeeDaoService.findOne(id);
		if(employee==null)
			throw new UserNotFoundException("id-"+id);
		return employee;
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable int id) {
		Employee employee = employeeDaoService.deleteById(id);
		
		if(employee==null)
			throw new UserNotFoundException("id-"+ id);		
	}
	
	@PutMapping("/employees/{id}/{name}")
	public void updateEmployee(@PathVariable int id, @PathVariable String name) {
		Employee employee = employeeDaoService.updateName(id, name);
		if(employee==null)
			throw new UserNotFoundException("id-"+ id);		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeDaoService.save(employee);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedEmployee.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
