package de.dhbw.softwareengineering.azubiplaner.domain.unitTests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;

@Testable
public class EmailValidatorTest {

	
	@Test
	public void testSetValidEmail() {
		Angestellter employee = new Angestellter();
		String validEmail = "test@example.com";
		
		assertDoesNotThrow(() -> {
			employee.setEmail(validEmail);
		});
	
		assertEquals(validEmail, employee.getEmail());
		
	}
	
	@Test
	public void testSetInvalidEmail_NoAtSymbol() {
		Angestellter employee = new Angestellter();
		String invalidEmail = "testexample.com";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_ConsecutiveDots() {
		Angestellter employee = new Angestellter();
		String invalidEmail = "test@example..com";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_ToLong() {
		Angestellter employee = new Angestellter();
		String invalidEmail = "thisIsJustAsimpleTestToShowMirkoThatTheLocalPartIsWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaayToLongAndAnErrorShouldbeenthrown@test.de";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_EndsWithDot() {
		Angestellter employee = new Angestellter();
		String invalidEmail = "test.@test.de";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
}
