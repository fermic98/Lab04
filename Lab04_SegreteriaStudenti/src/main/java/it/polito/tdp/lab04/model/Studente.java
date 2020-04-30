package it.polito.tdp.lab04.model;

public class Studente {

	private int matricola;
	private String cognome;
	private String nome;
	private String corsoDiStudi;
	
	public Studente(int matricola, String cognome, String nome, String corsoDiStudi) {
		this.corsoDiStudi = corsoDiStudi;
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}
	public void setCorsoDiStudi(String corsoDiStudi) {
		this.corsoDiStudi = corsoDiStudi;
	}
	@Override
	public String toString() {
		return  matricola + " " + cognome + " " + nome + " "
				+ corsoDiStudi;
	}

	
	
}
