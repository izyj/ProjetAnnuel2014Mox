package classes;

import java.io.Serializable;



public class Genre  implements Serializable {
	
	
	private String nomGenre;
	
	
	
	public Genre() {
		super();
	}


	public Genre( String nomGenre) {
		super();
		
		this.nomGenre = nomGenre;
	}


	

	public String getNomGenre() {
		return nomGenre;
	}


	public void setNomGenre(String nomGenre) {
		this.nomGenre = nomGenre;
	}
	
	
	

}
