package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstDay;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.TauschVorgang.Status;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.KuechendienstRepository;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.TauschVorgangRepository;

@Service
public class TauschService {

	@Autowired
	TauschVorgangRepository repo;
	
	@Autowired
	KuechendienstService kuechendienstService;
	
	public List<TauschVorgang> getAll() {
		return repo.all();
	}
	
	public Optional<TauschVorgang> findById(Long id) {
		return repo.getById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public List<TauschVorgang> getAllPendingRequests() {
		return repo.all().stream().filter(t -> t.getStatus() == Status.PENDING).collect(Collectors.toList());
	}
	
	
	public TauschVorgang createSwap(Long requester, Long toSwapWith) throws AccountNotFoundException {
		
		 KuechendienstDay angestellter1 = getKuechendienstDayForAngestellter(requester);
		 KuechendienstDay angestellter2 = getKuechendienstDayForAngestellter(toSwapWith);

		TauschVorgang tausch = new TauschVorgang(angestellter1, angestellter2, Status.PENDING);
		return repo.save(tausch);
	}
	
    private KuechendienstDay getKuechendienstDayForAngestellter(Long angestellterId) {
        return kuechendienstService.getcurrentKuechendienst().getDayEntities().stream()
            .filter(employee -> employee.getResponsibleEmployee().getId().equals(angestellterId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Angestellter wurde nicht im Kuechendienst gefunden!"));
    }
    
	public boolean acceptSwap(Long id) {
		Optional<TauschVorgang> tv = repo.getById(id);
		if(tv.isEmpty()) {
			return false;
		}
		TauschVorgang vorgang = tv.get();

		
		if(vorgang.getStatus() != Status.PENDING) {
			return false;
		}
		
		vorgang.setStatus(Status.ACCEPTED);
		repo.save(vorgang);
		return true;
	}
	
	public boolean declineSwap(Long id) {
		Optional<TauschVorgang> tv = repo.getById(id);
		if(tv.isEmpty()) {
			return false;
		}
		TauschVorgang vorgang = tv.get();

		
		if(vorgang.getStatus() != Status.PENDING) {
			return false;
		}
		
		vorgang.setStatus(Status.CANCELLED);
		repo.save(vorgang);
		return true;
	}
}
