package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TauschVorgangRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringTauschVorgangRepository;
@Repository
public class TauschVorgangBridge implements TauschVorgangRepository{
	@Autowired
	public SpringTauschVorgangRepository repo;

	@Override
	public TauschVorgang save(TauschVorgang e) {
		// TODO Auto-generated method stub
		return repo.save(e);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public Optional<TauschVorgang> getById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<TauschVorgang> all() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
