package de.dhbw.softwareengineering.azubiplaner.application.rules;

import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.HelpEntityObject;
import de.dhbw.softwareengineering.azubiplaner.application.helpObjects.Schedule;
import de.dhbw.softwareengineering.azubiplaner.domain.entities.Kuechendienst;

public interface BaseRule extends Comparable<BaseRule> {
	
	boolean applyRule(Kuechendienst lastWeek, Schedule current, int indexDay, HelpEntityObject potentionalCandidate);
	
	public int getPriority();
	
	@Override
	public default int compareTo(BaseRule o) {
		return Integer.compare(getPriority(), o.getPriority());
	}
}
