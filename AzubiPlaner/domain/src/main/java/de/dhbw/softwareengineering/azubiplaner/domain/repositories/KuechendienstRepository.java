package de.dhbw.softwareengineering.azubiplaner.domain.repositories;

import java.util.List;
import java.util.Optional;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang;

public interface KuechendienstRepository {
	KuechendienstEntity save(KuechendienstEntity e);
	
	void deleteById(Long id);
	
	Optional<KuechendienstEntity> getById(Long id);
	
	List<KuechendienstEntity> all();
}
