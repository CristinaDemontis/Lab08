package it.polito.tdp.extflightdelays.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.extflightdelays.model.Airport;

public class TestDAO {

	public static void main(String[] args) {

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List<Airport> aeroporti = dao.loadAllAirports(); 
		Map<Integer, Airport> idAeroporti = new HashMap<>(); 
		
		for(Airport a: aeroporti) {
			idAeroporti.put(a.getId(), a); 
		}
		
		System.out.println(dao.getallCoppieDistanzaPredefinita(idAeroporti));
		System.out.println(dao.getallCoppieDistanzaPredefinita(idAeroporti).size());

		
	}

}
