package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "SwapRequest")
public class SwapRequestEntity {
	
	@Column
	private KuechendienstDayEntity requester;
	
	@Column
	private KuechendienstDayEntity responder;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	
	
	public SwapRequestEntity() {
		super();
	}




	public SwapRequestEntity(KuechendienstDayEntity requester, KuechendienstDayEntity responder, Status status) {
		super();
		this.requester = requester;
		this.responder = responder;
		this.status = status;
	}




	public KuechendienstDayEntity getRequester() {
		return requester;
	}




	public void setRequester(KuechendienstDayEntity requester) {
		this.requester = requester;
	}




	public KuechendienstDayEntity getResponder() {
		return responder;
	}




	public void setResponder(KuechendienstDayEntity responder) {
		this.responder = responder;
	}




	public Status getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status;
	}




	public static enum Status {
		CANCELLED, ACCEPTED, PENDING;
	}
}
