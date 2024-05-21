package de.dhbw.softwareengineering.azubiplaner.plugins.rest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.services.EmployeeService;
import de.dhbw.softwareengineering.azubiplaner.application.services.HolidayService;
import de.dhbw.softwareengineering.azubiplaner.application.services.KuechendienstService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter.Role;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;
@RestController
public class KuechendienstController {

	
	@Autowired
	KuechendienstService kuechendienstService;
	
	@Autowired
	HolidayService holidayService;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/kuechendienst")
	public Kuechendienst createKuechendienstForCurrentWeek() {
		List<Angestellter> azubis = employeeService.findAll().stream().filter(t -> t.getRole() == Role.AZUBI).collect(Collectors.toList());
		List<HelpEntityObject> helpObjects = new ArrayList<>();
		azubis.forEach(a -> helpObjects.add(new HelpEntityObject(a)));
		
		return kuechendienstService.createKuechendienst(helpObjects, holidayService.getValidDaysForWeek(LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))));
    }
	
	@GetMapping("/kuechendienst")
	public Kuechendienst getCurrentKuechendienst() {
		return kuechendienstService.getcurrentKuechendienst();
	}
}
