package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.EmployeeRepository;


@Repository
public class EmployeeRepositoryImpl  implements EmployeeRepository  {

	@Autowired
	SpringEmployeeRepository springEmployeeRepository;
	
	@Override
	public void deleteById(Long id) {
		springEmployeeRepository.deleteById(id);
		
	}

	@Override
	public Optional<EmployeeEntity> getById(Long id) {
		// TODO Auto-generated method stub
		return springEmployeeRepository.findById(id);
	}




}
