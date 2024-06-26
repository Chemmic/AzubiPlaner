package de.dhbw.softwareengineering.azubiplaner.plugins.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.azubiplaner.application.services.EmployeeService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/employee")
	public Angestellter createEmployee(@RequestBody Angestellter body) {
		return service.createEmployee(body);
	}
	
	@GetMapping("/employee/all")
	public List<Angestellter> getAllEmployees() {
		return service.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Angestellter findById(@PathVariable Long id) {
		return service.getById(id);
	}
}
