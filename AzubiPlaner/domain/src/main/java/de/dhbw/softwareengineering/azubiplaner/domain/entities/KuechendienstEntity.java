package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Kuechendienst")
public class KuechendienstEntity {

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
