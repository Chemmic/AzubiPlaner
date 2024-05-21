package de.dhbw.softwareengineering.azubiplaner.plguins.helpObjects;

public class TauschHelpObject {

	
	private Long kuechendienstIDRequester;
	
	private Long kuechendienstIDToSwapWith;
	
	private Long KuechendienstID;
	
	

	public Long getKuechendienstID() {
		return KuechendienstID;
	}

	public void setKuechendienstID(Long kuechendienstID) {
		KuechendienstID = kuechendienstID;
	}

	public Long getKuechendienstIDRequester() {
		return kuechendienstIDRequester;
	}

	public void setKuechendienstIDRequester(Long kuechendienstIDRequester) {
		this.kuechendienstIDRequester = kuechendienstIDRequester;
	}

	public Long getKuechendienstIDToSwapWith() {
		return kuechendienstIDToSwapWith;
	}

	public void setKuechendienstIDToSwapWith(Long kuechendienstIDToSwapWith) {
		this.kuechendienstIDToSwapWith = kuechendienstIDToSwapWith;
	}

	public TauschHelpObject(Long kuechendienstIDRequester, Long kuechendienstIDToSwapWith, Long kuechendienstID) {
		super();
		this.kuechendienstIDRequester = kuechendienstIDRequester;
		this.kuechendienstIDToSwapWith = kuechendienstIDToSwapWith;
		KuechendienstID = kuechendienstID;
	}


	
	
}
