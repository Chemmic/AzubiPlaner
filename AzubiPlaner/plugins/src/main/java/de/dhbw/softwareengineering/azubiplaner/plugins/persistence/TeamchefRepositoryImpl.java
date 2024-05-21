package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Teamchef;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TeamchefRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringTeamchefRepository;

@Repository
public class TeamchefRepositoryImpl implements TeamchefRepository {

	@Autowired
	SpringTeamchefRepository springTeamchefRepository;
	
	@Override
	public Teamchef setTeamchef(Teamchef teamchef) {
		return springTeamchefRepository.save(teamchef);
	}

	@Override
	public Optional<Teamchef> getTeamchef() {
		return springTeamchefRepository.findFirstByOrderByChangedAtDesc();
	}

	@Override
	public void deleteTeamchefEntry(Long id) {
		springTeamchefRepository.deleteById(id);
		
	}

}
