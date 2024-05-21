package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.NotAzubiException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeamchefEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "teamchefEmployee_id")
	private EmployeeEntity teamchefEmployee;
	
	@Column
	private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "assignedBy_id")
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

	/** Domain Service der überprüft, ob der gesetzte Teamchef auch wirklich ein Azubi ist
	 * 
	 * @param teamchefEmployee
	 * @throws NotAzubiException
	 */
	public void setTeamchefEmployee(EmployeeEntity teamchefEmployee) throws NotAzubiException {
		if(teamchefEmployee.getRole() == null || teamchefEmployee.getRole() != Role.AZUBI) throw new NotAzubiException(teamchefEmployee);
		this.teamchefEmployee = teamchefEmployee;
	}
	
	
}
