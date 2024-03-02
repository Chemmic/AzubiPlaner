package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public void deleteById(Long id) {
		Objects.requireNonNull(id);
		employeeRepository.deleteById(id);
	}
	
	public EmployeeEntity getById(Long id) {
		Objects.requireNonNull(id);
		Optional<EmployeeEntity> optionalEntity = employeeRepository.getById(id);
		if(optionalEntity.isEmpty()) {
			throw new EntityNotFoundException("An Employee with id " + id + " does not exist!");
		}
		return optionalEntity.get();
		
	}
}