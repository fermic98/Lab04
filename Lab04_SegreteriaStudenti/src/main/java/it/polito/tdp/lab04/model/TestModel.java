package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
        System.out.print(model.getCorsi());
        
        List<Corso> corsiStudente = model.getCorsiStudente("146101");
        System.out.println(corsiStudente);
	}

}
