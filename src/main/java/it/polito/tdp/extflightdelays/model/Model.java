package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;  
	private List<Airport> aeroporti; 
	private Map<Integer, Airport> idAeroporti; 
	
	
	public void creaGrafo(int distanza_min) {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO(); 

		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.aeroporti = dao.loadAllAirports(); 
		this.idAeroporti = new HashMap<>(); 
		
		for(Airport a: aeroporti) {
			idAeroporti.put(a.getId(), a); 
		}
		
		Graphs.addAllVertices(this.grafo, this.aeroporti);
		
		List<CoppieAeroporti> archiDaSistemare = dao.getallCoppieDistanzaPredefinita(idAeroporti); 
		
		List<CoppieAeroporti> archi = archiPartenzaRitorno(archiDaSistemare); 
		
		for(CoppieAeroporti c : archi) {
			double distCalcolata = c.getDistanza(); 
			if(c.getDistanza() >= distanza_min) {
	//			DefaultWeightedEdge e = this.grafo.addEdge(c.getPartenza(), c.getArrivo());
				Graphs.addEdge(this.grafo, c.getPartenza(), c.getArrivo(), c.getDistanza()); 				
			}
		}
	
		System.out.println("Grafo creato con "+this.grafo.vertexSet().size() +
				" vertici e " + this.grafo.edgeSet().size() + " archi") ;
		System.out.println(this.grafo);
		
	}
	
	
	public List<CoppieAeroporti> archiPartenzaRitorno(List<CoppieAeroporti> lista){
		
		List<CoppieAeroporti> res = new ArrayList<>(); 
		
		for(CoppieAeroporti c: lista) {
			int idP = c.getPartenza().getId() ; 
			int idA = c.getArrivo().getId();
			for(CoppieAeroporti cc: lista) {
				int idPP = cc.getPartenza().getId() ; 
				int idAA = cc.getArrivo().getId();
				if(idP == idAA && idA == idPP) {
					double media = ((c.getDistanza()*c.getCnt() + cc.getDistanza()*cc.getCnt())/(c.getCnt()+cc.getCnt()));
					CoppieAeroporti n = new CoppieAeroporti(c.getPartenza(),c.getArrivo(),media,0);
					res.add(n); 
				}
			}
		}
		
		return res; 
	}


	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return this.grafo;
	}
	
	public Set<CoppieAeroporti> setArchi(Graph<Airport, DefaultWeightedEdge> grafo){
		Set<DefaultWeightedEdge> archi = grafo.edgeSet();
		Set<CoppieAeroporti> set = new HashSet<>();  
		for(DefaultWeightedEdge d: archi) {
			CoppieAeroporti c = new CoppieAeroporti(grafo.getEdgeSource(d), grafo.getEdgeTarget(d),grafo.getEdgeWeight(d), 0); 
			set.add(c); 
		}
		return set; 
	}


	public void setGrafo(Graph<Airport, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}
		
		

}
