package it.polito.tdp.extflightdelays.model;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.creaGrafo(3500);
		Graph<Airport, DefaultWeightedEdge> grafo = model.getGrafo(); 
		
		
		
		
		
		

	}

}
