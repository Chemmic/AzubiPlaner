package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.Schedule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.BaseRule;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstDayEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.repositories.KuechendienstRepository;

@Service
public class KuechendienstService {

	@Autowired
	KuechendienstRepository repo;
	
    @Autowired
    public KuechendienstService(ArrayList<BaseRule> rulesToApply) {
        this.rulesToApply = rulesToApply;
    }

    private ArrayList<BaseRule> rulesToApply;
	
	Map<DayOfWeek, List<Angestellter>> candidatesForSpecificDay;
	Schedule schedule;

	
	
	/** Funktionsweise des Algorithmus:
	 * 
	 *  1. Es werden die Tage Montag-Freitag nacheinander durchgegangen, und versucht eine passende Person zu finden.
	 *     Passende Person bedeutet: Es wird die Person gesucht, welche die wenigsten Tage Küchendienst hat und auf die alle Regeln zu treffen.
	 *  
	 *  2. Ist eine Kombination entstanden, dass keine Person mehr gefunden wird, wird ein Tag zurück gegangen und eine andere passende Person gesucht.
	 *     Es kann also theoretisch von Donnerstag zurück bis Montag gegangen werden, um alle möglichen Kombinationen zu prüfen.
	 *  
	 *  3. Gibt es keine mögliche Kombination um alle Tage zu decken und alle Regeln einzuhalten, wird die Regel mit der niedrigsten 
	 *     {@link de.dhbw.softwareengineering.azubiplaner.application.rules.BaseRule.class#getPriority()} entfernt und ein neuer Versuch den Plan zu generieren wird gestartet.   
	 *  
	 *  Schedule: hier wird ein temporärer Küchendienst angelegt, dieser kann sich durch Schritt 2 variable ändern. Der finale Schedule wird zum Küchendienstplan konvertiert.
	 *  CandidatesForSpecificDay: Hier werden mögliche Kandidaten für bestimmte Tage gespeichert. z.B. Ist ein Kandidat für Donnerstag gesetzt und später versucht der Algorithmus einen
	 *  						  neuen Kandidaten für Donnerstag zu finden, da mit einer aktuellen Kombination nicht möglich ist jemanden für Freitag zu finden, ist dieser als Kandidat
	 *  						  für Donnerstag nicht mehr verfügbar.
	 *  rulesToApply: Hier können Regeln ausgewählt werden, welche eingehalten werden sollen. Diese müssen das BaseRule Interface implementieren
	 *  
	 *  Es wird versicht:
	 *   - Ausgewogene Verteilung der Kandidaten
	 *   - Wenn möglich! Einhaltung aller Regeln
	 *   - Nur Kandidaten welche keine Schule/Uni haben sind zulässig
	 *   TODO: Für Zukunft: Urlaub checken
	 * 
	 * @return
	 */
	public KuechendienstEntity generateKuechendienst(List<HelpEntityObject> candidates, List<DayOfWeek> validDays) {
		
		resetCandidates(candidates);
		loadCandidatesForSpecificDay(candidates, validDays);
		schedule = new Schedule();
		//Hier muss der von letzter Woche geladen werden

		Collections.sort(candidates);
		if(rulesToApply != null) {
			Collections.sort(rulesToApply); 
		}
		//Der Erste Tag für den Planer (falls Montag Feiertag ist, Dienstag z.B.)
		int indexDay = validDays.get(0).getValue(); 
		
		while(indexDay != 6) {
			if(!validDays.contains(DayOfWeek.of(indexDay))) {
				//überspringe Feiertag 
				indexDay++;
				continue;
			}
			HelpEntityObject heo = findValidCandidateForRules(indexDay, candidates);
			if(heo == null) {
				//wenn heo null, kein Azubi für diesen Tag gefunden, Tag eins zurück gehen, neuen Kandidaten finden und weitermachen!
				//Wenn indexDay 0 ist, entferne Rule mit niedrigster Priorität!
				if(indexDay == validDays.get(0).getValue()) {
					rulesToApply.remove(0);
					return generateKuechendienst(candidates, validDays);
				} else {
					indexDay--;
					if(!validDays.contains(DayOfWeek.of(indexDay))) {
						//überspringe Feiertag 
						//TODO: Nochmal anschauen wegen 2 Feiertage in Folge!
						indexDay--;
					}
					schedule.getCandidateOnDay(DayOfWeek.of(indexDay)).setAmountOfWorkDays(schedule.getCandidateOnDay(DayOfWeek.of(indexDay)).getAmountOfWorkDays()-1);
					schedule.setCandidateOnDay(null, DayOfWeek.of(indexDay));
				}
				
				
			} else {
				heo.setAmountOfWorkDays(heo.getAmountOfWorkDays() + 1);
				candidatesForSpecificDay.get(DayOfWeek.of(indexDay)).remove(heo.getEntity());
				schedule.setCandidateOnDay(heo, DayOfWeek.of(indexDay));
				Collections.sort(candidates);
				indexDay++;
			}
		}
		
		return scheduleToKuechendienstEntity(schedule);
		
	}
	
	
	public KuechendienstEntity createKuechendienst(List<HelpEntityObject> candidates, List<DayOfWeek> validDays) {
		KuechendienstEntity ke = generateKuechendienst(candidates, validDays);
		return repo.save(ke);
	}
	
	public KuechendienstEntity getcurrentKuechendienst() {
	    Optional<KuechendienstEntity> currentService = repo.all().stream()
	            .max(Comparator.comparing(KuechendienstEntity::getGeneratedAt));

	        return currentService.orElse(null); 
	}

	private List<Angestellter> mapCanidates(List<HelpEntityObject> candidates) {
		List<Angestellter> l = new ArrayList<>();
		for(HelpEntityObject heo : candidates) {
			l.add(heo.getEntity());
		}
		return l;
	}


	private void resetCandidates(List<HelpEntityObject> candidates) {
		for(HelpEntityObject heo : candidates) {
			heo.setAmountOfWorkDays(0);
		}
	}
	
	public void loadCandidatesForSpecificDay(List<HelpEntityObject> candidates, List<DayOfWeek> validDays) {
		candidatesForSpecificDay = new HashMap<>();
		for(DayOfWeek dow : validDays) {
			candidatesForSpecificDay.put(dow, mapCanidates(candidates));
		}
	}

	//HIER FACTORY EINBAUEN?
	public KuechendienstEntity scheduleToKuechendienstEntity(Schedule schedule) {
		List<KuechendienstDayEntity> dayEntries = new ArrayList<>();
		for (Map.Entry<DayOfWeek, HelpEntityObject> entry : schedule.getMap().entrySet()) {
			dayEntries.add(new KuechendienstDayEntity(entry.getKey(), entry.getValue().getEntity()));
		}
		
		return new KuechendienstEntity(dayEntries, LocalDateTime.now());
	}
	//Diese Methode geht, (VORHER SORTIEREN DAMIT KANDIDAT MIT DEN WENIGSTEN KÜCHENDIENST EINSETZTEN ALS ERSTER GECHECKT WIRD!), alle Kandidaten durch und versucht die Regeln anzuwenden, können alle Regelen bei einem Kandidaten 
	// angewendet werden, also ist dieser ein gültiger Kandidat, wird dieser zurück gegeben ( der counter kann dann erhöht werden)
	// wurde kein Kandidat gefunden, auf welchen alle Regeln zutreffen wird null zurück gegeben.

	public HelpEntityObject findValidCandidateForRules( int indexDay, List<HelpEntityObject> candidates) {
		HelpEntityObject foundHelper = null;
		for(HelpEntityObject ee : candidates) {
			if(!candidatesForSpecificDay.get(DayOfWeek.of(indexDay)).contains(ee.getEntity())) continue;
			foundHelper = ee;
			for(BaseRule br : rulesToApply) {
				if(!br.applyRule(null, schedule, indexDay, foundHelper)) {
					foundHelper = null;
					break;
				}
			}
			if(foundHelper != null) {
				
				return foundHelper;
			}
		}
		return null;
	}
}
