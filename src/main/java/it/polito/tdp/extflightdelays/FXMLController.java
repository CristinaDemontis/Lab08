/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.extflightdelays.model.CoppieAeroporti;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	if(distanzaMinima != null) {
    		int distanza =0; 
    		try {
    			distanza = Integer.parseInt(distanzaMinima.getText());
    		}catch(NumberFormatException e) {
    			txtResult.setText("Inserire un numero valido!");
    		}
    		
    		model.creaGrafo(distanza);
    		int numVertici = model.getGrafo().vertexSet().size(); 
    		int numArchi = model.getGrafo().edgeSet().size(); 
    		txtResult.appendText("Il grafo ha " + numVertici+ " veritici e "+numArchi+ " archi. \n" );
    		Set<CoppieAeroporti> set = this.model.setArchi(model.getGrafo());
    		for(CoppieAeroporti c: set) {
    			txtResult.appendText(c.toString());
    		}	
    	}   	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
        setModel(model);
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
