package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.KuechendienstRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringKuechendienstRepository;

@Repository
public class KuechendienstBridge implements KuechendienstRepository {

	@Autowired
	SpringKuechendienstRepository repo;
	
	@Override
	public Kuechendienst save(Kuechendienst e) {
		// TODO Auto-generated method stub
		return repo.save(e);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<Kuechendienst> getById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<Kuechendienst> all() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
