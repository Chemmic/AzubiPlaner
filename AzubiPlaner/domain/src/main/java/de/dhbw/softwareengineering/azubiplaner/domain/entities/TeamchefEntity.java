package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.time.LocalDateTime;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter.Role;
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
	private Angestellter teamchefEmployee;
	
	@Column
	private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "assignedBy_id")
	private Angestellter assignedBy;

	public TeamchefEntity(Angestellter teamchefEmployee, LocalDateTime changedAt,Angestellter assignedBy) throws NotAzubiException {
		super();
		setTeamchefEmployee(teamchefEmployee);
		this.assignedBy = assignedBy;
		this.changedAt = changedAt;
	}

	public TeamchefEntity() {
		super();
	}

	public Angestellter getTeamchefEmployee() {
		return teamchefEmployee;
	}

	
	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public Angestellter getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Angestellter assignedBy) {
		this.assignedBy = assignedBy;
	}

	/** Domain Service der überprüft, ob der gesetzte Teamchef auch wirklich ein Azubi ist
	 * 
	 * @param teamchefEmployee
	 * @throws NotAzubiException
	 */
	public void setTeamchefEmployee(Angestellter teamchefEmployee) throws NotAzubiException {
		if(teamchefEmployee.getRole() == null || teamchefEmployee.getRole() != Role.AZUBI) throw new NotAzubiException(teamchefEmployee);
		this.teamchefEmployee = teamchefEmployee;
	}
	
	
}
