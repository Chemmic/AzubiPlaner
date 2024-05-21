package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.AngestelltenRepository;
import de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring.SpringEmployeeRepository;


@Repository
public class EmployeeRepositoryImpl  implements AngestelltenRepository  {

	@Autowired
	SpringEmployeeRepository springEmployeeRepository;
	
	@Override
	public void deleteById(Long id) {
		springEmployeeRepository.deleteById(id);
	}

	@Override
	public Optional<Angestellter> getById(Long id) {
		// TODO Auto-generated method stub
		return springEmployeeRepository.findById(id);
	}

	@Override
	public Angestellter save(Angestellter e) {
		return springEmployeeRepository.save(e);
	}

	@Override
	public List<Angestellter> all() {
		// TODO Auto-generated method stub
		return springEmployeeRepository.findAll();
	}




}
