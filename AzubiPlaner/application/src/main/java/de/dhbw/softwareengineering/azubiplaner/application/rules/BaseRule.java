package de.dhbw.softwareengineering.azubiplaner.application.rules;

import de.dhbw.softwareengineering.azubiplaner.application.services.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.KuechendienstEntity;

public interface BaseRule extends Comparable<BaseRule> {
	
	boolean applyRule(KuechendienstEntity lastWeek, Schedule current, int indexDay, HelpEntityObject potentionalCandidate);
	
	public int getPriority();
	
	@Override
	public default int compareTo(BaseRule o) {
		return Integer.compare(getPriority(), o.getPriority());
	}
}
