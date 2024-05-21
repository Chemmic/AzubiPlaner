package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.util.regex.Pattern;

import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;
import de.dhbw.softwareengineering.azubiplaner.domain.values.Email;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Angestellter {
	
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
	
	@Column
	private Email email;
	
	public Angestellter() {
		
	}
	
	public Angestellter(String username, Role role, String tuleapId, Email email) throws InvalidEmailException {
		this.username = username;
		this.role = role;
		this.tuleapId = tuleapId;
		this.email = email;
	}



	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
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
