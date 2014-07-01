package classes;

import java.io.Serializable;
import java.util.ArrayList;

import module.IModule;


public class Album implements Serializable  {
	
	
	private String titreAlbum;
	private String artiste;
	private ArrayList<Chanson> lesChanson;
	
	
	public Album() {
		super();
	}
	public Album(String titre) {
		
		this.titreAlbum = titre;
	}

	public Album(String titreAlbum,	ArrayList<Chanson> lesChanson) {
		
		this.titreAlbum = titreAlbum;
		this.lesChanson = lesChanson;
	}
	

	
	public String getTitreAlbum() {
		return titreAlbum;
	}

	public void setTitreAlbum(String titreAlbum) {
		this.titreAlbum = titreAlbum;
	}



	public ArrayList<Chanson> getLesChanson() {
		return lesChanson;
	}

	public void setLesChanson(ArrayList<Chanson> lesChanson) {
		this.lesChanson = lesChanson;
	}

	public ArrayList<Chanson> RechercheChanson(){
		//a implementer
		
		return null;
		
		
	}
	

}
