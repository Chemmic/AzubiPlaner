package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;

public interface EmployeeRepository {
	void deleteById(Long id);
	
	Optional<EmployeeEntity> getById(Long id);
}
