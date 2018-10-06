package test.jenkins.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import test.jenkins.model.Employee;

@RestController
public class EmployeeController {

	@GetMapping(path="/{name}")
	public ResponseEntity<Employee> greeting(@PathVariable String name) {
		return new ResponseEntity<Employee>(new Employee(name), HttpStatus.OK);
	}
	
	@GetMapping(path="/employee/{name}",produces= {MediaType.APPLICATION_JSON_VALUE})
	public Resource<Employee> one(@PathVariable String name) {
		Resource<Employee> resource = new Resource<Employee>(new Employee("Selami"),
				linkTo(methodOn(EmployeeController.class).one(name)).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
		return resource;
	}
	
	@GetMapping(path="/employees",produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<Resource<Employee>> all() {
		List<Employee> employeeList = Arrays.asList(new Employee("Selami"),new Employee("Coban"));
		List<Resource<Employee>> employees = employeeList.stream()
				.map(employee-> new Resource<>(employee,
					linkTo(methodOn(EmployeeController.class).one(employee.getName())).withSelfRel(),
					linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
					.collect(Collectors.toList());
		
		return employees;
	}
}
