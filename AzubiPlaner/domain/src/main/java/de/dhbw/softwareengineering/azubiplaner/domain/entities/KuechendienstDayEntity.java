package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


// Eventuell OV?
@Entity
public class KuechendienstDayEntity {
	
	@Column
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;
	
	@Column
	private EmployeeEntity responsibleEmployee;
	
	
	
	public KuechendienstDayEntity(DayOfWeek day, EmployeeEntity responsibleEmployee) {
		super();
		this.day = day;
		this.responsibleEmployee = responsibleEmployee;
	}



	public KuechendienstDayEntity() {

	}
}
