package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.NotAzubiException;

@Entity
public class TeamchefEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private EmployeeEntity teamchefEmployee;
	
	@Column
	private LocalDateTime changedAt;

	@Column
	private EmployeeEntity assignedBy;

	public TeamchefEntity(EmployeeEntity teamchefEmployee, LocalDateTime changedAt,EmployeeEntity assignedBy) throws NotAzubiException {
		super();
		setTeamchefEmployee(teamchefEmployee);
		this.assignedBy = assignedBy;
		this.changedAt = changedAt;
	}

	public TeamchefEntity() {
		super();
	}

	public EmployeeEntity getTeamchefEmployee() {
		return teamchefEmployee;
	}

	
	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public EmployeeEntity getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(EmployeeEntity assignedBy) {
		this.assignedBy = assignedBy;
	}

	public void setTeamchefEmployee(EmployeeEntity teamchefEmployee) throws NotAzubiException {
		if(teamchefEmployee.getRole() == null || teamchefEmployee.getRole() != Role.AZUBI) throw new NotAzubiException(teamchefEmployee);
		this.teamchefEmployee = teamchefEmployee;
	}
	
	
}
