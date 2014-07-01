package classes;

import java.io.Serializable;
import java.util.ArrayList;



public class Chanson implements Serializable {

	private String titre;
	
	private Genre unGenre;
	private String pathFichier;
	private String compositeur;
	private String artiste;
	private String sonAlbum;
	private String format;
	
	public Chanson() {
		super();
	}
	/** Constructeur chanson prend en argument un titre un genre un nom de chanteur
	 * et un nom de compositeur.
	 * @param titre
	 * @param unGenre
	 * @param chanteur
	 * @param compos
	 */
	public Chanson(String titre, Genre unGenre,  String chanteur,String compos,String album,String path, String type) {
		
		this.titre = titre;
		this.compositeur = compos;
		this.unGenre = unGenre;
		this.artiste = chanteur;
		this.sonAlbum =album;
		this.pathFichier =path;
		this.format = type;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Genre getUnGenre() {
		return unGenre;
	}
	public void setUnGenre(Genre unGenre) {
		this.unGenre = unGenre;
	}
	public String getCompositeur() {
		return compositeur;
	}
	public void setCompositeur(String compositeur) {
		this.compositeur = compositeur;
	}
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}
	public String getSonAlbum() {
		return sonAlbum;
	}
	public void setSonAlbum(String sonAlbum) {
		this.sonAlbum = sonAlbum;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPathFichier() {
		return pathFichier;
	}
	public void setPathFichier(String pathFichier) {
		this.pathFichier = pathFichier;
	}
	
//	public int getTailleFichier() {
//		return tailleFichier;
//	}
//	public void setTailleFichier(int tailleFichier) {
//		this.tailleFichier = tailleFichier;
//	}
//	
}

