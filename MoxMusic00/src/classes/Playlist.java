package classes;

import classes.Album;

import java.io.Serializable;
import java.util.ArrayList;
import classes.Chanson;


public class Playlist  implements Serializable {
	
	
		private String nomPlaylist;
		private ArrayList<Chanson> lesChansons;
		private ArrayList<Album> lesAlbums;
		
		
		
		public Playlist() {
			super();
		}


		public Playlist( String nomPlaylist,
				ArrayList<Chanson> lesChansons, ArrayList<Album> lesAlbums) {
			super();
			
			this.nomPlaylist = nomPlaylist;
			this.lesChansons = lesChansons;
			this.lesAlbums = lesAlbums;
		}


		


		public String getNomPlaylist() {
			return nomPlaylist;
		}


		public void setNomPlaylist(String nomPlaylist) {
			this.nomPlaylist = nomPlaylist;
		}


		public ArrayList<Chanson> getLesChansons() {
			return lesChansons;
		}


		public void setLesChansons(ArrayList<Chanson> lesChansons) {
			this.lesChansons = lesChansons;
		}


		public ArrayList<Album> getLesAlbums() {
			return lesAlbums;
		}


		public void setLesAlbums(ArrayList<Album> lesAlbums) {
			this.lesAlbums = lesAlbums;
		}
		
		
	

}
