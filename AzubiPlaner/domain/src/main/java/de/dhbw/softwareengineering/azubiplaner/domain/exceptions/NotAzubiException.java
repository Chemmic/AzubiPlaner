package de.dhbw.softwareengineering.azubiplaner.domain.exceptions;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;

public class NotAzubiException extends Exception {
	
	public NotAzubiException(EmployeeEntity e) {
		super(e.getUsername() + " does not have the Azubi role!");
	}

}
