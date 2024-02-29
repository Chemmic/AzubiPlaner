package de.dhbw.softwareengineering.azubiplaner.plugins.persistence;
import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EntityExample;

public interface springDataForBridge extends JpaRepository<EntityExample, Long> {

}
