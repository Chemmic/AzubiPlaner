package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.List;
import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;

public interface EmployeeRepository {
	
	EmployeeEntity save(EmployeeEntity e);
	
	void deleteById(Long id);
	
	Optional<EmployeeEntity> getById(Long id);
	
	List<EmployeeEntity> all();
}
