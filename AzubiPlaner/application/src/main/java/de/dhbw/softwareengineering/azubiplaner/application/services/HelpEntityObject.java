package de.dhbw.softwareengineering.azubiplaner.application.services;

import de.dhbw.softwareengineering.azubiplaner.domain.entities.EmployeeEntity;

public class HelpEntityObject implements Comparable<HelpEntityObject>{

	private EmployeeEntity entity;
	
	private Integer amountOfWorkDays = 0;

	
	public HelpEntityObject() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HelpEntityObject(EmployeeEntity entity) {
		super();
		this.entity = entity;
	}


	public EmployeeEntity getEntity() {
		return entity;
	}

	public void setEntity(EmployeeEntity entity) {
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
