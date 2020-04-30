package it.polito.tdp.lab04.model;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	 private CorsoDAO corsoDAO;
	 private StudenteDAO studenteDAO;
	 private Corso corso;
	 private Studente studente;
     private List<Corso> corsi;
     /**
      * costruttore del model con cui inizializzo i corsi
      * all'interno della combobox, interrogando il database.
      */
     public Model() {
    	 this.corsoDAO = new CorsoDAO();
    	 this.studenteDAO = new StudenteDAO();
    	 corsi = this.corsoDAO.getTuttiICorsi();
       
     }
     
     /**
      * meotodo per risalire ad uno studente, data la sua matricola,
      * tramite interrogazione del database con la classe StudenteDAO.
      * @param matricola
      * @return
      */

	public Studente getStudente(String matricola) {
		
		StudenteDAO dao = new StudenteDAO();
		try {
			
			 int mat = Integer.parseInt(matricola);
		     this.studente = dao.getStudente(mat);
		 if(studente==null) {
			 throw new IllegalStateException("La matricola non corrisponde a nessuno studente");
		 }
		  
		     
		} catch (NumberFormatException e) {
			throw new NumberFormatException("fratè devi inserire un numero");
		} catch (InvalidParameterException ie) {
			
		} catch (SQLException sql) {
		    throw new RuntimeException("ERRORE DI CONNESSIONE");
		}
		return studente;
	}
	
	/**
	 * metodo che svolge la query per ottenere le matricole degli iscritti,
	 * e poi iterando il metodo getStudente(), crea una lista Studenti che passa
	 * al FMXLController;
	 * @param studente
	 * @throws SQLException 
	 */
	
	public String getIscrittiCorso(String nomeCorso) throws InvalidAttributeIdentifierException {
		Corso corso = this.corsoDAO.getCorso(nomeCorso);
		String elenco ="";
		List<Studente> studentiIscritti = null;
		if(corso == null) {
			throw new InvalidAttributeIdentifierException("Nessun corso selezionato");
		}
		
		try {
			studentiIscritti = this.studenteDAO.getStudentiIscritti(corso.getCodins());
		} catch(SQLException e) {
			throw new RuntimeException("ERRORE DI CONNESSIONE AL DATABASE");
		}
		
		for(Studente s : studentiIscritti) {
			elenco += s+"\n";
		}
		return elenco;
	}
		
	
	public List<Corso> getCorsiStudente(String mat) {
		 
		List<Corso> corsiStudente = new LinkedList<>();
		
		Studente studente = this.getStudente(mat);
		List<String> corsi2 ;
		try {
			corsi2 = this.studenteDAO.getCorsiStudente(studente);
		} catch (SQLException sql) {
			throw new RuntimeException("ERRORE DB");
		}
		for(String s : corsi2) {
		   corsiStudente.add(this.corsoDAO.getCorsoDaCodins(s));
		}
		
		return corsiStudente;
	}
	

	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	
	public Corso getCorso(String nomeCorso) {
        Corso trovato= null;
		for(Corso c : this.corsi) {
			if(c.getNome().compareTo(nomeCorso)==0) {
				trovato=c;
			}
			
		}
		return trovato;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public List<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}
     
	public boolean isIscritto(Studente studente, Corso corso) {
		List<Corso> corsi = this.getCorsiStudente(Integer.toString(studente.getMatricola()));
		for(Corso c : corsi) {
		if(c.getNome().compareTo(corso.getNome())==0) {
			return true;
		  }
		}
		return false;
	}
     
	public boolean iscrivi(Studente studente, Corso corso) {
		
		return this.corsoDAO.iscrivi(studente,corso);
	}
}
