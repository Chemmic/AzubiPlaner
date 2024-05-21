package de.dhbw.softwareengineering.azubiplaner.application.helpObjects;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.Angestellter;

public class HelpEntityObject implements Comparable<HelpEntityObject>{

	private Angestellter entity;
	
	private Integer amountOfWorkDays = 0;

	
	public HelpEntityObject() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HelpEntityObject(Angestellter entity) {
		super();
		this.entity = entity;
	}


	public Angestellter getEntity() {
		return entity;
	}

	public void setEntity(Angestellter entity) {
		this.entity = entity;
	}

	public Integer getAmountOfWorkDays() {
		return amountOfWorkDays;
	}

	public void setAmountOfWorkDays(Integer amountOfWorkDays) {
		this.amountOfWorkDays = amountOfWorkDays;
	}


	@Override
	public int compareTo(HelpEntityObject o) {
		// TODO Auto-generated method stub
		return amountOfWorkDays.compareTo(o.amountOfWorkDays);
	}
	
	
}
