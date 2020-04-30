package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				corsi.add(new Corso(codins,numeroCrediti, nome, periodoDidattico));
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un nome insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
         final String sql = "SELECT * FROM corso WHERE nome = ? ";
	     Corso corso= null;
        try {
        	Connection conn = ConnectDB.getConnection();
        	PreparedStatement ps = conn.prepareStatement(sql);
        	
        	ps.setString(1, nomeCorso);
        	
        	ResultSet rs = ps.executeQuery();
            while(rs.next()) {
        	corso = new Corso(rs.getString("codins"),
                	rs.getInt("crediti"),
                	rs.getString("nome"),
                	rs.getInt("pd"));
        	
        	conn.close();
            }
        	
            
        } catch (SQLException e) {
        	e.printStackTrace();
        	throw new RuntimeException("Errore Db");
        }
        
	    return corso;
	}
	public Corso getCorsoDaCodins(String codins) {
        final String sql = "SELECT * FROM corso WHERE codins = ? ";
	     Corso corso= null;
       try {
       	Connection conn = ConnectDB.getConnection();
       	PreparedStatement ps = conn.prepareStatement(sql);
       	
       	ps.setString(1, codins);
       	
       	ResultSet rs = ps.executeQuery();
           while(rs.next()) {
       	corso = new Corso(rs.getString("codins"),
               	rs.getInt("crediti"),
               	rs.getString("nome"),
               	rs.getInt("pd"));
       	
       	conn.close();
           }
       	
           
       } catch (SQLException e) {
       	e.printStackTrace();
       	throw new RuntimeException("Errore Db");
       }
       
	    return corso;
	}
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscrivi(Studente studente, Corso corso) {
		String sql = "INSERT IGNORE INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES(?,?)";
		boolean returnValue =false;
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,studente.getMatricola());
			ps.setString(2, corso.getCodins());
			
			int rs = ps.executeUpdate();
			
			if(rs==1) {
				returnValue = true;
			}
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return returnValue;
	}

}
