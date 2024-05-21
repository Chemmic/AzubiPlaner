package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.AngestelltenRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	AngestelltenRepository employeeRepository;
	
	
	public void deleteById(Long id) {
		Objects.requireNonNull(id);
		employeeRepository.deleteById(id);
	}
	
	public Angestellter getById(Long id) {
		Objects.requireNonNull(id);
		Optional<Angestellter> optionalEntity = employeeRepository.getById(id);
		if(optionalEntity.isEmpty()) {
			throw new EntityNotFoundException("An Employee with id " + id + " does not exist!");
		}
		return optionalEntity.get();
		
	}
	
	public Angestellter createEmployee(Angestellter ee) {
		if(employeeRepository.all().stream().anyMatch(t -> t.getUsername().equalsIgnoreCase(ee.getUsername()))) {
			throw new EntityExistsException();
		}
		return employeeRepository.save(ee);
	}
	
	public List<Angestellter> findAll() {
		return employeeRepository.all();
	}
}
