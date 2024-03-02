package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TeamchefRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringTeamchefRepository;

@Repository
public class TeamchefRepositoryImpl implements TeamchefRepository {

	@Autowired
	SpringTeamchefRepository springTeamchefRepository;
	
	@Override
	public TeamchefEntity setTeamchef(TeamchefEntity teamchef) {
		return springTeamchefRepository.save(teamchef);
	}

	@Override
	public TeamchefEntity getTeamchef() {
		return springTeamchefRepository.findAll().get(0);
	}

	@Override
	public void deleteTeamchef() {
		springTeamchefRepository.deleteAll();
		
	}

}
