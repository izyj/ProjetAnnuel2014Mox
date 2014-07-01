package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Artiste  implements Serializable {

	public String  nomArtiste;
	public ArrayList<Chanson> cesChansons;
	public ArrayList<Album> cesAlbums;
	
	public Artiste() {
		
	}
	
	
	public Artiste(String nomArtiste, ArrayList<Chanson> cesChansons,
			ArrayList<Album> cesAlbums) {
		super();
		this.nomArtiste = nomArtiste;
		this.cesChansons = cesChansons;
		this.cesAlbums = cesAlbums;
	}


	public String getNomArtiste() {
		return nomArtiste;
	}

	public void setNomArtiste(String nomArtiste) {
		this.nomArtiste = nomArtiste;
	}

	public ArrayList<Chanson> getCesChansons() {
		return cesChansons;
	}

	public void setCesChansons(ArrayList<Chanson> cesChansons) {
		this.cesChansons = cesChansons;
	}

	public ArrayList<Album> getCesAlbums() {
		return cesAlbums;
	}

	public void setCesAlbums(ArrayList<Album> cesAlbums) {
		this.cesAlbums = cesAlbums;
	}
	
	
	
	
	
	
}
