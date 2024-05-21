package de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

@Repository
public interface SpringEmployeeRepository extends JpaRepository<Angestellter, Long> {

}
