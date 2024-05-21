package de.dhbw.softwareengineering.azubiplaner.plugins.rest;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.azubiplaner.application.services.TauschService;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang;
import de.dhbw.softwareengineering.azubiplaner.plguins.helpObjects.TauschHelpObject;

@RestController
public class TauschController {

	@Autowired
	TauschService service;
	
	@GetMapping("/tauschVorgang/all")
	public List<TauschVorgang> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/tauschVorgang/pending")
	public List<TauschVorgang> getPending() {
		return service.getAllPendingRequests();
	}
	
	@DeleteMapping("/tauschVorgang/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@GetMapping("/tauschVorgang/{id}")
	public Optional<TauschVorgang> getById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/tauschVorgang")
	public ResponseEntity<TauschVorgang> createSwap(@RequestBody TauschHelpObject tho) {
		try {
			return ResponseEntity.ok(service.createSwap(tho.getKuechendienstIDRequester(), tho.getKuechendienstIDToSwapWith()));
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/tauschVorgang/accept/{id}")
	public ResponseEntity<TauschVorgang> acceptSwap(@PathVariable Long id) {
		if(service.acceptSwap(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/tauschVorgang/decline/{id}")
	public ResponseEntity<TauschVorgang> declineSwap(@PathVariable Long id) {
		if(service.declineSwap(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}
