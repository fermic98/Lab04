package it.polito.tdp.lab04.model;

public class Corso implements Comparable<Corso> {
   private String codins;
   private int crediti;
   private String nome;
   private int pd;
public Corso(String codins, int crediti, String nome, int pd) {
	super();
	this.codins = codins;
	this.crediti = crediti;
	this.nome = nome;
	this.pd = pd;
}
public String getCodins() {
	return codins;
}
public void setCodins(String codins) {
	this.codins = codins;
}
public int getCrediti() {
	return crediti;
}
public void setCrediti(int crediti) {
	this.crediti = crediti;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getPd() {
	return pd;
}
public void setPd(int pd) {
	this.pd = pd;
}
@Override
public String toString() {
	return  nome;
	}
@Override
public int compareTo(Corso arg0) {
	return this.nome.compareTo(arg0.nome);
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Corso other = (Corso) obj;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	return true;
}
   


   
   
   
}
