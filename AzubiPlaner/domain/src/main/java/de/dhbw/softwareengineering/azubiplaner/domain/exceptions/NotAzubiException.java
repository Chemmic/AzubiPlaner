package de.dhbw.softwareengineering.azubiplaner.domain.exceptions;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

public class NotAzubiException extends Exception {
	
	public NotAzubiException(Angestellter e) {
		super(e.getUsername() + " does not have the Azubi role!");
	}

}
