package de.dhbw.softwareengineering.azubiplaner.application.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.repositories.EmployeeRepository;

@Service
public class TeamchefService {
	
	@Autowired
    private EmployeeRepository repository;
    
    public void deleteById(Long id) {
        repository.deleteById(id);
       
    }
}
