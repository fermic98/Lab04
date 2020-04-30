 package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.directory.InvalidAttributeIdentifierException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBoxCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private TextArea txtArea;

	private Model model;

	private List<Corso> corsi;

    @FXML
    void CorsoSelezionato(ActionEvent event) {

    }

    @FXML
    void btnCercaCorsi(ActionEvent event) {
        List<Corso> corsi;
        StringBuilder sb = new StringBuilder();
    	try {
    	 corsi=this.model.getCorsiStudente(this.txtMatricola.getText());
         } catch (RuntimeException rt) {
        	 txtArea.setText(rt.getMessage());
        	 return;
         }
         
    	for (Corso corso : corsi) {
			sb.append(String.format("%-8s ", corso.getCodins()));
			sb.append(String.format("%-4s ", corso.getCrediti()));
			sb.append(String.format("%-5s ", corso.getNome()));
			sb.append(String.format("%-4s ", corso.getPd()));
			sb.append("\n");
		}
    	txtArea.appendText(sb.toString());
    
    }

    @FXML
    void btnCercaIscrittiCorso(ActionEvent event) {
    
    	String nomeCorso;
    	
    	if(!(this.comboBoxCorsi.getValue() instanceof Corso)) {
    		this.txtArea.setText("Nessun corso selezionato");
    		return ;
    	} else { 
    	   nomeCorso = this.comboBoxCorsi.getValue().getNome();
    	}
                  
         try{
        	 String studenti = this.model.getIscrittiCorso(nomeCorso);
             this.txtArea.setText(studenti);  
         } catch (InvalidAttributeIdentifierException e) {
        	 this.txtArea.setText(e.getMessage());
        	 return;
         } catch (RuntimeException sql) {
        	 this.txtArea.setText(sql.getMessage());
        	 return;
         }
         
    }

    @FXML
    void btnIscrivi(ActionEvent event) {
       	Studente studente;
    	try {
   		 studente = this.model.getStudente(this.txtMatricola.getText());
   	 } catch (IllegalStateException e) {
   		 this.txtArea.setText(e.getMessage());
   		 return;
   	 } catch (NumberFormatException ne) {
   		 this.txtArea.setText(ne.getMessage());
   		 return;
   	 } catch (RuntimeException rte) {
   		 this.txtArea.setText(rte.getMessage());
   		 return;
   	 }
    	if(this.model.isIscritto(studente, this.comboBoxCorsi.getValue())) {
    		txtArea.setText("Lo studente è iscritto al corso.");
    	} else {
    		try {
    		if(this.model.iscrivi(studente, this.comboBoxCorsi.getValue())) {
    			this.txtArea.setText("studente iscritto con successo!");
    		} else {
    			this.txtArea.setText("errore durante l'scrizione dell'utente");
    		}
    		} catch (RuntimeException e) {
              
    		}
    	}
    }

    @FXML
    void btnNomeECognome(ActionEvent event) {
    	 this.cognome.clear();
    	 this.nome.clear();
    	 
    	 Studente studente = null;
    	 
    	 try {
    		 studente = this.model.getStudente(this.txtMatricola.getText());
    	 } catch (IllegalStateException e) {
    		 this.txtArea.setText(e.getMessage());
    	 } catch (NumberFormatException ne) {
    		 this.txtArea.setText(ne.getMessage());
    	 } catch (RuntimeException rte) {
    		 this.txtArea.setText(rte.getMessage());
    	 }
    	 
    	 
         this.nome.setText(studente.getNome());
         this.cognome.setText(studente.getCognome());
    }

    @FXML
    void btnReset(ActionEvent event) {
    	this.comboBoxCorsi.getSelectionModel().clearSelection();
        this.txtMatricola.clear();
        this.txtArea.clear();
    }

    @FXML
    void txtMatricolaSelezionata(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cognome != null : "fx:id=\"cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.corsi = this.model.getCorsi();
    	corsi.add(new Corso(null, 0 , "-Nessuno-", 0));
    	Collections.sort(corsi);
         
    	this.comboBoxCorsi.getItems().addAll(corsi);
    }
}
