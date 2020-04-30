package it.polito.tdp.lab04.DAO;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
     
     public Studente getStudente(int matricola) throws SQLException {
    	
     
     final String sql = "SELECT * FROM studente "
     		          + "WHERE studente.matricola = ?";

		Studente studente = null;

			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
            
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				
				studente = new Studente(matricola, cognome, nome, cds);
			}

			conn.close();
			
			return studente;
     }
     
     
     public List<Studente> getStudentiIscritti(String codins) throws SQLException{
    	 List<Studente> studenti = new LinkedList<>();
    	 
    	 final String sql = "SELECT matricola FROM iscrizione "
    			           +"WHERE codins = ?";
    	 
    	 Connection conn = ConnectDB.getConnection();
    	 PreparedStatement ps = conn.prepareStatement(sql);
    	 
    	 ps.setString(1, codins);
    	 
    	 ResultSet rs = ps.executeQuery();
    	 
    	 while(rs.next()) {
    		 studenti.add(this.getStudente(Integer.parseInt(rs.getString("matricola"))));
    	 }
    	 conn.close();
    	 return studenti;
     }

   
 

 public List<String> getCorsiStudente(Studente studente) throws SQLException {
	 
	 List<String> corsi = new LinkedList<String>();	 
	
	 
	 final String sql = "SELECT codins FROM iscrizione WHERE matricola = ?";
	 
	 try {
		 Connection conn = ConnectDB.getConnection();
		 PreparedStatement ps= conn.prepareStatement(sql);
		 
		 ps.setInt(1, studente.getMatricola());
		 
		 ResultSet rs = ps.executeQuery();
		 
		 while(rs.next()) {
			 corsi.add(rs.getString("codins"));
		 }
		 
	 } catch (SQLException sql2) {
		 throw new RuntimeException();
	 }
	 return corsi; 
	 
	 
 }
}