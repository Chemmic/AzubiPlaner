package de.dhbw.softwareengineering.azubiplaner.application.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Teamchef;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TeamchefRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamchefService {
	
	@Autowired
    private TeamchefRepository teamchefRepository;
	
    

	public Teamchef updateTeamchef(Teamchef newTeamchef) {
		return teamchefRepository.setTeamchef(newTeamchef);
	}
	
	public Teamchef getCurrentTeamchef() {
		Optional<Teamchef> teamchefOptional = teamchefRepository.getTeamchef();
		if(teamchefOptional.isEmpty()) throw new EntityNotFoundException("There is currently no Teamchef set");
		return teamchefOptional.get();
	}
}
