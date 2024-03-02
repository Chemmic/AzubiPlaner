package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.NotAzubiException;

@Entity
public class TeamchefEntity {

	@Column
	private EmployeeEntity teamchefEmployee;
	
	@Column
	private LocalDateTime lastChangedAt;

	@Column
	private EmployeeEntity assignedBy;

	public TeamchefEntity(EmployeeEntity teamchefEmployee, LocalDateTime lastChangedAt,EmployeeEntity assignedBy) throws NotAzubiException {
		super();
		setTeamchefEmployee(teamchefEmployee);
		this.assignedBy = assignedBy;
		this.lastChangedAt = lastChangedAt;
	}

	public TeamchefEntity() {
		super();
	}

	public EmployeeEntity getTeamchefEmployee() {
		return teamchefEmployee;
	}

	
	public LocalDateTime getLastChangedAt() {
		return lastChangedAt;
	}

	public void setLastChangedAt(LocalDateTime lastChangedAt) {
		this.lastChangedAt = lastChangedAt;
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
