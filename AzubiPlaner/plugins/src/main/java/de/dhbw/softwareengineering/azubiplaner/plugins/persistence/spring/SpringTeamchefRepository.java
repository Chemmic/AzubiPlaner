package de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;

public interface SpringTeamchefRepository extends JpaRepository<TeamchefEntity, Long> {

	Optional<TeamchefEntity> findFirstByOrderByChangedAtDesc();

}
