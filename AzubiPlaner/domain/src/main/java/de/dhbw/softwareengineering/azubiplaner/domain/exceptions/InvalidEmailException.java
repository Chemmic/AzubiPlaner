package de.dhbw.softwareengineering.azubiplaner.domain.exceptions;

public class InvalidEmailException extends Exception{

	public InvalidEmailException(String email) {
		super("E-Mail " + email + " is invalid!");
	}
}
