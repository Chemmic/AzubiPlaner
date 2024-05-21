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
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.TeamchefEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.NotAzubiException;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.EmployeeRepository;

@Testable
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

		@Mock
		private EmployeeRepository employeeRepository;
		
		@InjectMocks
		private EmployeeService employeeService;
		
		@Test
		public void isValidTeamchef() {
			assertDoesNotThrow(() -> {
				Optional<EmployeeEntity> mockEmployee = Optional.of(new EmployeeEntity("Mirko", Role.AZUBI, "one", "mirko.dost@dost.mirko"));
				when(employeeRepository.getById(1l)).thenReturn(mockEmployee);
				EmployeeEntity emp = employeeService.getById(1l);
				//Checken ob richtiges entity beim Service zurückgeliefert wird
				assertEquals(mockEmployee.get().getRole(), emp.getRole());
				assertEquals(mockEmployee.get().getEmail(), emp.getEmail());
				assertEquals(mockEmployee.get().getUsername(), emp.getUsername());
				
				//Überprüfen ob valid Teamchef
				assertDoesNotThrow(() -> {
					new TeamchefEntity(mockEmployee.get(), LocalDateTime.now(), mockEmployee.get());
				});
			});
			
		}
		
		@Test
		public void isinValidTeamchef() {
			assertDoesNotThrow(() -> {
				Optional<EmployeeEntity> mockEmployee = Optional.of(new EmployeeEntity("Mirko", Role.ADMIN, "one", "mirko.dost@dost.mirko"));
				when(employeeRepository.getById(1l)).thenReturn(mockEmployee);
				EmployeeEntity emp = employeeService.getById(1l);
				//Checken ob richtiges entity beim Service zurückgeliefert wird
				assertEquals(mockEmployee.get().getRole(), emp.getRole());
				assertEquals(mockEmployee.get().getEmail(), emp.getEmail());
				assertEquals(mockEmployee.get().getUsername(), emp.getUsername());
				
				//Überprüfen ob INVALID Teamchef
				assertThrows(NotAzubiException.class, () -> {
					new TeamchefEntity(mockEmployee.get(), LocalDateTime.now(), mockEmployee.get());
				});
			});
			
		}
}
