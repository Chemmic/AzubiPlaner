package de.dhbw.softwareengineering.azubiplaner.plugins.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.azubiplaner.application.services.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.services.KuechendienstService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity.Role;

@RestController
public class TestController {
	
	@Autowired
	KuechendienstService kuechendienstService;
	
	@GetMapping("/test/{id}")
	public String test(@PathVariable String id) {
		return "test: " + id;
	}
	
	@GetMapping("/test")
	public KuechendienstEntity test2() {
		kuechendienstService.setCandidates(Arrays.asList(new HelpEntityObject(new EmployeeEntity("Chemmic", Role.AZUBI, "2"))));
		KuechendienstEntity e = kuechendienstService.generateKuechendienst();
        return e;
    }
}
