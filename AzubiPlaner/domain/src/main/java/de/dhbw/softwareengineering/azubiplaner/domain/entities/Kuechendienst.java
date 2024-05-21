package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Kuechendienst")
public class Kuechendienst {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column
	private List<KuechendienstDay> dayEntities;
	
	@Column
	private LocalDateTime generatedAt;

	public Kuechendienst(List<KuechendienstDay> dayEntities, LocalDateTime generatedAt) {
		super();
		this.dayEntities = dayEntities;
		this.generatedAt = generatedAt;
	}

	public Kuechendienst() {
		super();
	}

	public List<KuechendienstDay> getDayEntities() {
		return dayEntities;
	}

	public void setDayEntities(List<KuechendienstDay> dayEntities) {
		this.dayEntities = dayEntities;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}
	
	public Long getId() {
		return id;
	}
}
