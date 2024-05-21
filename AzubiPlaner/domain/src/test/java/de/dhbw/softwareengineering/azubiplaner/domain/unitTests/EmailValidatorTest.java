package de.dhbw.softwareengineering.azubiplaner.domain.unitTests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;

@Testable
public class EmailValidatorTest {

	
	@Test
	public void testSetValidEmail() {
		EmployeeEntity employee = new EmployeeEntity();
		String validEmail = "test@example.com";
		
		assertDoesNotThrow(() -> {
			employee.setEmail(validEmail);
		});
	
		assertEquals(validEmail, employee.getEmail());
		
	}
	
	@Test
	public void testSetInvalidEmail_NoAtSymbol() {
		EmployeeEntity employee = new EmployeeEntity();
		String invalidEmail = "testexample.com";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_ConsecutiveDots() {
		EmployeeEntity employee = new EmployeeEntity();
		String invalidEmail = "test@example..com";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_ToLong() {
		EmployeeEntity employee = new EmployeeEntity();
		String invalidEmail = "thisIsJustAsimpleTestToShowMirkoThatTheLocalPartIsWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaayToLongAndAnErrorShouldbeenthrown@test.de";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
	
	@Test
	public void testSetInvalidEmail_EndsWithDot() {
		EmployeeEntity employee = new EmployeeEntity();
		String invalidEmail = "test.@test.de";
		
        assertThrows(InvalidEmailException.class, () -> {
            employee.setEmail(invalidEmail);
        });
	
		assertNull(employee.getEmail());
	}
}
