package de.dhbw.softwareengineering.azubiplaner.application.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.azubiplaner.application.rules.BaseRule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.FridayRule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.NonConsecutiveDaysRule;
import de.dhbw.softwareengineering.azubiplaner.application.rules.Schedule;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstDayEntity;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;

@Service
public class KuechendienstService {

	List<HelpEntityObject> candidates;
	List<BaseRule> rulesToApply = new ArrayList<>( Arrays.asList(new NonConsecutiveDaysRule(), new FridayRule()));
	Map<DayOfWeek, List<EmployeeEntity>> mapForDaysCandidates = new HashMap<>();
	Schedule schedule;
	
	/** Vorgehen beim generieren:
	 *  
	 *  1. Alle Azubis ziehen und im Tracker schauen wer diese Woche da ist.
	 *  
	 *  2. Küchendienst von vergangener Woche holen, um vergleiche bzw Regeln durchsetzen zu können, dass zum Beispiel nicht jemand
	 *     zwei mal hintereinander an einem Freitag dran kommt oder auch nicht Freitag und Montag.
	 *     
	 *  3. Für Feiertage checken   
	 * 
	 *  4. Weitere Regeln checken, wie zum Beispiel, wer ist nächste Wochze nicht mehr da und sollte diese Woche nocheinmal dran kommen
	 * 
	 * @return
	 */
	public KuechendienstEntity generateKuechendienst() {
		resetCandidates();
		
		schedule = new Schedule();
		//Hier muss der von letzter Woche geladen werden
		mapForDaysCandidates.put(DayOfWeek.MONDAY, mapCanidates());
		mapForDaysCandidates.put(DayOfWeek.TUESDAY, mapCanidates());
		mapForDaysCandidates.put(DayOfWeek.WEDNESDAY, mapCanidates());
		mapForDaysCandidates.put(DayOfWeek.THURSDAY, mapCanidates());
		mapForDaysCandidates.put(DayOfWeek.FRIDAY, mapCanidates());
		Collections.sort(candidates);
		Collections.sort(rulesToApply); 
		
		int indexDay = DayOfWeek.MONDAY.getValue(); 
		
		while(indexDay != 6) {
			System.out.println(indexDay);
			HelpEntityObject heo = findValidCandidateForRules(indexDay);
			if(heo == null) {
				//wenn heo null, kein Azubi für diesen Tag gefunden, Tag eins zurück gehen, neuen Kandidaten finden und weitermachen!
				//Wenn indexDay 0 ist, entferne Rule mit niedrigster Priorität!
				if(indexDay == DayOfWeek.MONDAY.getValue()) {
					rulesToApply.remove(0);
					return generateKuechendienst();
				} else {
					indexDay--;
					schedule.getCandidateOnDay(DayOfWeek.of(indexDay)).setAmountOfWorkDays(schedule.getCandidateOnDay(DayOfWeek.of(indexDay)).getAmountOfWorkDays()-1);
					schedule.setCandidateOnDay(null, DayOfWeek.of(indexDay));
				}
				
				
			} else {
				heo.setAmountOfWorkDays(heo.getAmountOfWorkDays() + 1);
				mapForDaysCandidates.get(DayOfWeek.of(indexDay)).remove(heo.getEntity());
				schedule.setCandidateOnDay(heo, DayOfWeek.of(indexDay));
				Collections.sort(candidates);
				indexDay++;
			}
		}
		
		return scheduleToKuechendienstEntity(schedule);
		
	}
	
	
	
	public List<HelpEntityObject> getCandidates() {
		return candidates;
	}

	private List<EmployeeEntity> mapCanidates() {
		List<EmployeeEntity> l = new ArrayList<>();
		for(HelpEntityObject heo : candidates) {
			l.add(heo.getEntity());
		}
		return l;
	}

	public void setCandidates(List<HelpEntityObject> candidates) {
		System.out.println("Set Candidates! " + candidates.size());
		this.candidates = candidates;
	}

	private void resetCandidates() {
		for(HelpEntityObject heo : candidates) {
			heo.setAmountOfWorkDays(0);
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

	public HelpEntityObject findValidCandidateForRules( int indexDay) {
		HelpEntityObject foundHelper = null;
		for(HelpEntityObject ee : candidates) {
			if(!mapForDaysCandidates.get(DayOfWeek.of(indexDay)).contains(ee.getEntity())) continue;
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
