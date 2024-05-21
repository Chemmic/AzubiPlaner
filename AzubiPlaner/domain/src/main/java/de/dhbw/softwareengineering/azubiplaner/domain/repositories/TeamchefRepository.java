package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Teamchef;

public interface TeamchefRepository {
	
	//return das Teamchef entity nach speichern in der Datenbank
	Teamchef setTeamchef(Teamchef teamchef);
	
	Optional<Teamchef> getTeamchef();
	
	void deleteTeamchefEntry(Long id);
}
