package de.dhbw.softwareengineering.azubiplaner.application.rules;

import de.dhbw.softwareengineering.azubiplaner.application.services.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;

public class FridayRule implements BaseRule {




	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean applyRule(KuechendienstEntity lastWeek, Schedule current, int indexDay,
			HelpEntityObject potentionalCandidate) {
		// TODO Auto-generated method stub
		return true;
	}



}
