package de.dhbw.softwareengineering.azubiplaner.application.rules;

import java.time.DayOfWeek;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.Schedule;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;

//2 tage in Folge
public class NonConsecutiveDaysRule implements BaseRule{


	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean applyRule(KuechendienstEntity lastWeek, Schedule current, int indexDay,
			HelpEntityObject potentionalCandidate) {
		//TODO: Freitag von letzter woche noch prüfen (also nicht Freitag + Montag, wegen zwei tagen)
		if(indexDay == DayOfWeek.MONDAY.getValue()) {
			return true;
		}
		if(current.getCandidateOnDay(DayOfWeek.of(indexDay-1)) != null) {
			if(current.getCandidateOnDay(DayOfWeek.of(indexDay-1)).getEntity().equals(potentionalCandidate.getEntity())) {
				return false;
			}
			return true;
		}
		return false;
	}



}
