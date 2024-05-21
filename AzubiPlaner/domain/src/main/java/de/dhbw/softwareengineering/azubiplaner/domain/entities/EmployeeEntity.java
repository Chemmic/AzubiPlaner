package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import java.util.regex.Pattern;

import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;
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
	
	@Column
	private String email;
	
	public EmployeeEntity() {
		
	}
	
	public EmployeeEntity(String username, Role role, String tuleapId, String email) throws InvalidEmailException {
		this.username = username;
		this.role = role;
		this.tuleapId = tuleapId;
		setEmail(email);
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {
		/*
		 * Domain Service: Checking for valid Email
		 * allows numeric values from 0 to 9.
		   Both uppercase and lowercase letters from a to z are allowed.
    	   Allowed are underscore “_”, hyphen “-“, and dot “.”
		   Dot isn’t allowed at the start and end of the local part.
		   Consecutive dots aren’t allowed.
		   For the local part, a maximum of 64 characters are allowed.
		 */
		if(Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE)
				.matcher(email)
				.matches()) {
			this.email = email;
		} else {
			throw new InvalidEmailException(email);
		}
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
