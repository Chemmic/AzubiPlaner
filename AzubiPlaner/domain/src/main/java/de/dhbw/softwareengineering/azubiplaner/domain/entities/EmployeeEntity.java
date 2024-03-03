package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String username;
	
	@Enumerated(EnumType.STRING)
	@Column 
	private Role role;
	
	@Column
	private String tuleapId;
	
	public EmployeeEntity() {
		
	}
	
	public EmployeeEntity(String username, Role role, String tuleapId) {
		super();
		this.username = username;
		this.role = role;
		this.tuleapId = tuleapId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	public String getTuleapId() {
		return tuleapId;
	}



	public void setTuleapId(String tuleapId) {
		this.tuleapId = tuleapId;
	}



	public static enum Role {
		AZUBI, ADMIN;
	}
}
