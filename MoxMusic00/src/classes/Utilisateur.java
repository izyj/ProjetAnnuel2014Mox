package classes;

import java.io.Serializable;
import java.util.ArrayList;



public class Utilisateur  implements Serializable {
	
		private String Nom;
		private String prenom;
		private ArrayList<Album> mesAlbum;
		
		
		
		public Utilisateur() {
			super();
		}


		public Utilisateur(String nom, String prenom, ArrayList<Album> mesAlbum) {
			super();
			Nom = nom;
			this.prenom = prenom;
			this.mesAlbum = mesAlbum;
		}


		public String getNom() {
			return Nom;
		}


		public void setNom(String nom) {
			Nom = nom;
		}


		public String getPrenom() {
			return prenom;
		}


		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


		public ArrayList<Album> getMesAlbum() {
			return mesAlbum;
		}


		public void setMesAlbum(ArrayList<Album> mesAlbum) {
			this.mesAlbum = mesAlbum;
		}
		
		
		
		

}

