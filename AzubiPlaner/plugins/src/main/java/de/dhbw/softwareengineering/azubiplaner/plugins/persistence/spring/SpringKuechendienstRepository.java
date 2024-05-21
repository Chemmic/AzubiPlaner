package de.dhbw.softwareengineering.azubiplaner.plugins.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;

public interface SpringKuechendienstRepository extends JpaRepository<KuechendienstEntity, Long> {

}
