package it.polito.tdp.lab04.DAO;

import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) throws SQLException {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		Corso corso = cdao.getCorso("Ingegneria della qualità");
		System.out.println("----------Corso---------");
		System.out.println(corso);
		List<Corso> corsi = cdao.getTuttiICorsi();
		System.out.println("----------Corsi---------");
        for(Corso c : corsi) {
        	System.out.println(c);
        }
		
		StudenteDAO sdao = new StudenteDAO();
		
		List<String> corsiStudente = sdao.getCorsiStudente(sdao.getStudente(146101));
		System.out.println("----------Corsi Studente---------");
		System.out.println(corsiStudente);
		try {
			Studente studente = sdao.getStudente(146101);
			System.out.println("----------Studente---------");
		    System.out.println(studente.getNome()+" "+studente.getCognome());
		} catch (RuntimeException e)  {
			e.printStackTrace();
		}
		
		try {
			
			List<Studente> studenti = sdao.getStudentiIscritti("01KSUPG");
			System.out.println("----------Studenti Iscritti---------");
			for(Studente s : studenti) {
				System.out.println(s);
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
        
	}

}
