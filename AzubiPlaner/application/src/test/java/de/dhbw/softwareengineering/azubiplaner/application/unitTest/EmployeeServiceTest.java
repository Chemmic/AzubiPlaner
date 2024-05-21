package de.dhbw.softwareengineering.azubiplaner.application.unitTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.dhbw.softwareengineering.azubiplaner.application.services.EmployeeService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Teamchef;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.NotAzubiException;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.AngestelltenRepository;
import de.dhbw.softwareengineering.azubiplaner.domain.values.Email;

@Testable
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

		@Mock
		private AngestelltenRepository employeeRepository;
		
		@InjectMocks
		private EmployeeService employeeService;
		
		@Test
		public void isValidTeamchef() {
			assertDoesNotThrow(() -> {
				Optional<Angestellter> mockEmployee = Optional.of(new Angestellter("Mirko", Role.AZUBI, "one",new Email( "mirko.dost@dost.mirko")));
				when(employeeRepository.getById(1l)).thenReturn(mockEmployee);
				Angestellter emp = employeeService.getById(1l);
				//Checken ob richtiges entity beim Service zurückgeliefert wird
				assertEquals(mockEmployee.get().getRole(), emp.getRole());
				assertEquals(mockEmployee.get().getEmail(), emp.getEmail());
				assertEquals(mockEmployee.get().getUsername(), emp.getUsername());
				
				//Überprüfen ob valid Teamchef
				assertDoesNotThrow(() -> {
					new Teamchef(mockEmployee.get(), LocalDateTime.now(), mockEmployee.get());
				});
			});
			
		}
		
		@Test
		public void isinValidTeamchef() {
			assertDoesNotThrow(() -> {
				Optional<Angestellter> mockEmployee = Optional.of(new Angestellter("Mirko", Role.ADMIN, "one", new Email("mirko.dost@dost.mirko")));
				when(employeeRepository.getById(1l)).thenReturn(mockEmployee);
				Angestellter emp = employeeService.getById(1l);
				//Checken ob richtiges entity beim Service zurückgeliefert wird
				assertEquals(mockEmployee.get().getRole(), emp.getRole());
				assertEquals(mockEmployee.get().getEmail(), emp.getEmail());
				assertEquals(mockEmployee.get().getUsername(), emp.getUsername());
				
				//Überprüfen ob INVALID Teamchef
				assertThrows(NotAzubiException.class, () -> {
					new Teamchef(mockEmployee.get(), LocalDateTime.now(), mockEmployee.get());
				});
			});
			
		}
}
