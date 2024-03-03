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


// Eventuell OV?
@Entity
public class KuechendienstDayEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;
	
    @ManyToOne
    @JoinColumn(name = "responsibleEmployee_id")
	private EmployeeEntity responsibleEmployee;
	
	
	
	public KuechendienstDayEntity(DayOfWeek day, EmployeeEntity responsibleEmployee) {
		super();
		this.day = day;
		this.responsibleEmployee = responsibleEmployee;
	}



	public KuechendienstDayEntity() {

	}



	public DayOfWeek getDay() {
		return day;
	}



	public void setDay(DayOfWeek day) {
		this.day = day;
	}



	public EmployeeEntity getResponsibleEmployee() {
		return responsibleEmployee;
	}



	public void setResponsibleEmployee(EmployeeEntity responsibleEmployee) {
		this.responsibleEmployee = responsibleEmployee;
	}
	
	
}
