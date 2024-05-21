package de.dhbw.softwareengineering.azubiplaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.dhbw.softwareengineering.azubiplaner.application.services.EmployeeService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;
import de.dhbw.softwareengineering.azubiplaner.domain.values.Email;
import jakarta.annotation.PostConstruct;

@Component
public class DatenbankInitializer {
	
	@Autowired
	EmployeeService employeeService;
	
	
    @PostConstruct
    public void init() {
        try {
			employeeService.createEmployee(new Angestellter("Matti", Role.AZUBI, "tuleap", new Email("Matteo.staar@bla.de")));
			employeeService.createEmployee(new Angestellter("Mirko", Role.AZUBI, "tulea2p", new Email("mirko@bla.de")));
			employeeService.createEmployee(new Angestellter("DHBW", Role.ADMIN, "tulea3p", new Email("dhbw@dhbw.com")));
		} catch (InvalidEmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}