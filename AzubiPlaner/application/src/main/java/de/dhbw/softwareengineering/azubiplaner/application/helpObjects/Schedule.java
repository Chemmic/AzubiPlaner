package de.dhbw.softwareengineering.azubiplaner.application.helpObjects;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

public class Schedule {

	//Ein Schedule hält die Daten nur Temporär, also es ist kein Finaler küchenplan für die Woche! Wird für Algorithmus verwendet!

	private HashMap<DayOfWeek, HelpEntityObject> s = new HashMap<>();
	
	public Schedule() {
	//	 s.putAll(Map.of(DayOfWeek.MONDAY, new HelpEntityObject(), DayOfWeek.TUESDAY, null, DayOfWeek.WEDNESDAY, null, DayOfWeek.THURSDAY, null, DayOfWeek.FRIDAY, null));
	}

	public Map<DayOfWeek, HelpEntityObject> getMap() {
		return s;
	}
	
	public HelpEntityObject getCandidateOnDay(DayOfWeek dayOfWeek) {
		return s.get(dayOfWeek);
	}

	public void setCandidateOnDay(HelpEntityObject employee, DayOfWeek dayOfWeek) {
		this.s.put(dayOfWeek, employee);
	}

	
}
