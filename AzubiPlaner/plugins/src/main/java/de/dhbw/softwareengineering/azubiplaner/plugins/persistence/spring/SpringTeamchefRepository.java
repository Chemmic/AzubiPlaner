package de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Teamchef;

@Repository
public interface SpringTeamchefRepository extends JpaRepository<Teamchef, Long> {

	Optional<Teamchef> findFirstByOrderByChangedAtDesc();

}
