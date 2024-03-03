package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kuechendienst")
public class KuechendienstEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	@Column
	private List<KuechendienstDayEntity> dayEntities;
	
	@Column
	private LocalDateTime generatedAt;

	public KuechendienstEntity(List<KuechendienstDayEntity> dayEntities, LocalDateTime generatedAt) {
		super();
		this.dayEntities = dayEntities;
		this.generatedAt = generatedAt;
	}

	public KuechendienstEntity() {
		super();
	}

	public List<KuechendienstDayEntity> getDayEntities() {
		return dayEntities;
	}

	public void setDayEntities(List<KuechendienstDayEntity> dayEntities) {
		this.dayEntities = dayEntities;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}
	
	
}
