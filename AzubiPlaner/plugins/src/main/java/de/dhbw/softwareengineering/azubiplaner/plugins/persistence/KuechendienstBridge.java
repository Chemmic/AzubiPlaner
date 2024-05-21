package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.KuechendienstRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringKuechendienstRepository;

public class KuechendienstBridge implements KuechendienstRepository {

	@Autowired
	SpringKuechendienstRepository repo;
	
	@Override
	public KuechendienstEntity save(KuechendienstEntity e) {
		// TODO Auto-generated method stub
		return repo.save(e);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<KuechendienstEntity> getById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<KuechendienstEntity> all() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
