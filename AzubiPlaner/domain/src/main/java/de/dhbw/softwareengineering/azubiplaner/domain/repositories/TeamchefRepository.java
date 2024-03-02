package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;

public interface TeamchefRepository {
	
	//return das Teamchef entity nach speichern in der Datenbank
	TeamchefEntity setTeamchef(TeamchefEntity teamchef);
	
	Optional<TeamchefEntity> getTeamchef();
	
	void deleteTeamchefEntry(Long id);
}
