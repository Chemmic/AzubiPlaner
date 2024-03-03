package de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;

@Repository
public interface SpringTeamchefRepository extends JpaRepository<TeamchefEntity, Long> {

	Optional<TeamchefEntity> findFirstByOrderByChangedAtDesc();

}
