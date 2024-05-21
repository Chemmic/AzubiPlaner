package de.dhbw.softwareengineering.azubiplaner.plugins.rest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.services.HolidayService;
import de.dhbw.softwareengineering.azubiplaner.application.services.KuechendienstService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.exceptions.InvalidEmailException;

@RestController
public class TestController {
	
	@Autowired
	KuechendienstService kuechendienstService;
	
	@Autowired
	HolidayService holidayService;
	
	@GetMapping("/test/{id}")
	public String test(@PathVariable String id) {
		return "test: " + id;
	}
	
	@GetMapping("/test")
	public KuechendienstEntity test2() {
		KuechendienstEntity e;
		try {
			return kuechendienstService.generateKuechendienst(Arrays.asList(new HelpEntityObject(new EmployeeEntity("Chemmic", Role.AZUBI, "2", "Matteo.staar@gmx.de")), new HelpEntityObject(new EmployeeEntity("Chemmic2", Role.AZUBI, "23", "Matteo.staa3r@gmx.de"))),holidayService.getValidDaysForWeek(LocalDateTime.now()));
		} catch (InvalidEmailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return null;
    }
	
	@GetMapping("/testfeiertag")
	public List<DayOfWeek> test3() {
		return holidayService.getValidDaysForWeek(LocalDateTime.of(2024, 10, 3, 1, 1));
    }
}
