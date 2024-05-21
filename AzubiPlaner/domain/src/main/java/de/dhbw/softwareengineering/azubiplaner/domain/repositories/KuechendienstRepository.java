package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.List;
import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;

public interface KuechendienstRepository {
	Kuechendienst save(Kuechendienst e);
	
	void deleteById(Long id);
	
	Optional<Kuechendienst> getById(Long id);
	
	List<Kuechendienst> all();
}
