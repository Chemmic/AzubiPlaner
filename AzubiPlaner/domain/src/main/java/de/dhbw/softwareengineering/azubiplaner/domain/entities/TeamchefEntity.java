package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TeamchefEntity {

	@Column
	private EmployeeEntity teamchef;
	
	@Column
	private LocalDateTime lastChangedAt;


	public TeamchefEntity(EmployeeEntity teamchef, LocalDateTime lastChangedAt) {
		super();
		this.teamchef = teamchef;
		this.lastChangedAt = lastChangedAt;
	}

	public TeamchefEntity() {
		super();
	}

	public EmployeeEntity getTeamchef() {
		return teamchef;
	}

	
	public LocalDateTime getLastChangedAt() {
		return lastChangedAt;
	}

	public void setLastChangedAt(LocalDateTime lastChangedAt) {
		this.lastChangedAt = lastChangedAt;
	}

	public void setTeamchef(EmployeeEntity teamchef) {
		this.teamchef = teamchef;
	}
	
	
}
