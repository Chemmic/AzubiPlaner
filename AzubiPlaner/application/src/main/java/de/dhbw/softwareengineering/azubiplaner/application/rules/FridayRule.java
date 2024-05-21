package de.dhbw.softwareengineering.azubiplaner.application.rules;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.Schedule;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;

public class FridayRule implements BaseRule {

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean applyRule(Kuechendienst lastWeek, Schedule current, int indexDay,
			HelpEntityObject potentionalCandidate) {
		return true;
	}



}
