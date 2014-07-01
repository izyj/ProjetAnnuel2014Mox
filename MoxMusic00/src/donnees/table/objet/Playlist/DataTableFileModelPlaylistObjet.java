package donnees.table.objet.Playlist;

import javax.swing.JTable;

import javax.swing.*;
import javax.swing.table.*;

import classes.Chanson;
import classes.Playlist;

import fichier.AjoutMusique;

import java.io.*;
import java.util.*;

public class DataTableFileModelPlaylistObjet extends AbstractTableModel {
	
	// private String nomFichier = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "chanson.txt";

	 protected Vector entete;            //données d'une ligne du tableau  
	 protected String datafile;        //nom du fichier de données
	 private AjoutMusique recupM = new AjoutMusique();
	 private ArrayList<Playlist>  playlists ;
	 
	 public DataTableFileModelPlaylistObjet(String nomFichier) {
	     playlists =(ArrayList<Playlist>)(Object)recupM.recuperationMusique(nomFichier);
	     initVectors();
	    }
	 
	 public DataTableFileModelPlaylistObjet() {
		super();
		initVectors();
	}

    public void initVectors() {
   
    	entete=new Vector();
    	
    	   entete.addElement(new String("Nom de la playlist"));
   
       }

    
    
    public int getColumnCount() {
    	return entete.size();
       }
    
    public int getRowCount() {
        return playlists.size();
       }
    

    public String getColumnName(int columnIndex) {
    	
    	//if(entete.elementAt(columnIndex).toString().equals("Path"))
 
    	return entete.elementAt(columnIndex).toString();
    	
       }
    
//    public Class getColumnClass(int columnIndex){
//        return String.class;
//       }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
       }
    //On donne ici le contenu de chaque cellule sous forme d'objet.
    public Object getValueAt(int rowIndex, int columnIndex) {
    	
    	switch(columnIndex){
        case 0:
            return playlists.get(rowIndex).getNomPlaylist();

        default:
            return null; //Ne devrait jamais arriver
    }
       }

 
 	/**
 	 * Retourne l'objet de la ligne
 	 * @param rowIndex
 	 * @param columnIndex
 	 * @return
 	 */
    public Object getObjet(int rowIndex, int columnIndex){
		Chanson chanson;
    	
		switch(columnIndex){
        case 0:
        	return playlists.get(rowIndex);
        case 1:
        	return playlists.get(rowIndex);
        case 2: 
        	return playlists.get(rowIndex);
        case 3:
        	return playlists.get(rowIndex);
        case 4:
        	return playlists.get(rowIndex);
        case 5:
        	return playlists.get(rowIndex);
        case 6: 
        	return playlists.get(rowIndex);
        default:
            return null; //Ne devrait jamais arriver
    	
    	
		}
    }
    
   
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	
    }
}
