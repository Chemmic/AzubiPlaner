package de.dhbw.softwareengineering.azubiplaner.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SwapRequest")
public class TauschVorgang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "requester_id")
	private KuechendienstDayEntity requester;
	
    @ManyToOne
    @JoinColumn(name = "responder_id")
	private KuechendienstDayEntity responder;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	public TauschVorgang() {
		super();
	}


	public TauschVorgang(KuechendienstDayEntity requester, KuechendienstDayEntity responder, Status status) {
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
