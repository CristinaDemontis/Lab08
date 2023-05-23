package it.polito.tdp.extflightdelays.model;

public class CoppieAeroporti {
	
	private Airport partenza; 
	private Airport arrivo; 
	private double distanza;
	private int cnt; 
	
	public CoppieAeroporti(Airport partenza, Airport arrivo, double distanza, int cnt) {
		super();
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.distanza = distanza;
		this.cnt = cnt; 
	}

	public Airport getPartenza() {
		return partenza;
	}

	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}

	public Airport getArrivo() {
		return arrivo;
	}

	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Aeroporto di partenza: " + partenza.getAirportName() + ", Aeroporto di arrivo: " + arrivo.getAirportName() + ", la distanza del grafo è: " + distanza +"\n";
	} 
	
	
	
	

}
