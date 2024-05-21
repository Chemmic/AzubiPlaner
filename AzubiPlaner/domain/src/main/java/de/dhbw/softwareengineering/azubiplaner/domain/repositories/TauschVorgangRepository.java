package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.List;
import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang;

public interface TauschVorgangRepository {

	
	TauschVorgang save(TauschVorgang e);
	
	void deleteById(Long id);
	
	Optional<TauschVorgang> getById(Long id);
	
	List<TauschVorgang> all();
}
