package de.dhbw.softwareengineering.azubiplaner.application.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TeamchefRepository;

@Service
public class TeamchefService {
	
	@Autowired
    private TeamchefRepository teamchefRepository;
    

	public TeamchefEntity updateTeamchef(TeamchefEntity newTeamchef) {
		//TODO: Alten Teamchef in History speichern
		teamchefRepository.deleteTeamchef();
		return teamchefRepository.setTeamchef(newTeamchef);
	}
}
