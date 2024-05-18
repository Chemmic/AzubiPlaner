package de.dhbw.softwareengineering.azubiplaner.domain.exceptions;

public class InvalidEmailException extends Exception{

	public InvalidEmailException() {
		super("Invalid E-Mail entered!");
	}
}
