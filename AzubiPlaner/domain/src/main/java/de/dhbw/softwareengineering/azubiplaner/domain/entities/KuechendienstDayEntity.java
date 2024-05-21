package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


// Eventuell VO?
@Entity
public class KuechendienstDayEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	
    @ManyToOne
    @JoinColumn(name = "responsibleEmployee_id")
	private Angestellter responsibleEmployee;
	
	
	
	public KuechendienstDayEntity(DayOfWeek dayOfWeek, Angestellter responsibleEmployee) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.responsibleEmployee = responsibleEmployee;
	}



	public KuechendienstDayEntity() {

	}



	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}



	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}



	public Angestellter getResponsibleEmployee() {
		return responsibleEmployee;
	}



	public void setResponsibleEmployee(Angestellter responsibleEmployee) {
		this.responsibleEmployee = responsibleEmployee;
	}
	
	
}
