package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.List;
import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

public interface AngestelltenRepository {
	
	Angestellter save(Angestellter e);
	
	void deleteById(Long id);
	
	Optional<Angestellter> getById(Long id);
	
	List<Angestellter> all();
}
