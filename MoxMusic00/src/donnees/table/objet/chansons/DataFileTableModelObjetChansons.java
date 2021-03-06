package donnees.table.objet.chansons;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import classes.Chanson;
import fichier.AjoutMusique;

public class DataFileTableModelObjetChansons extends AbstractTableModel {
	 protected Vector entete;            //données d'une ligne du tableau  
	 //private final String[] entetes = {"Titre", "Album", "Genre"," Artiste", "Compositeur", "Format","Path"};    //noms de colonnes
	 protected String datafile;        //nom du fichier de données
	 private AjoutMusique recupM = new AjoutMusique();
	 private ArrayList<Chanson>   listeChanson ;
	 private Vector donnees;
	 
	 public DataFileTableModelObjetChansons(ArrayList<Chanson> col) {
	     listeChanson =col;
	     initVectors();
	    }
	 
	 public DataFileTableModelObjetChansons() {
		super();
		initVectors();
		
	}

    public void initVectors() {
   
    	entete=new Vector();
    	
    	   entete.addElement(new String("Titre"));
    	   entete.addElement(new String("Album"));
    	   entete.addElement(new String("Genre"));
    	   entete.addElement(new String("Artiste"));
    	   entete.addElement(new String("Compositeur"));
    	   entete.addElement(new String("Format"));
    	   entete.addElement(new String("Path"));
    	
    	  
    	   
    	  
    	   
       }

    
    
    public int getColumnCount() {
    	if(entete == null)
    		return 0;
    	return entete.size();
       }
    
    public int getRowCount() {
    	if(listeChanson == null)
    		return 0;
        return listeChanson.size();
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
//    	Chanson chanson;
//       chanson =  (Chanson)infoligne.elementAt(
//                 (rowIndex*getColumnCount())+columnIndex);
//       
//        return chanson.getTitre();
    	
    	switch(columnIndex){
        case 0:
            return listeChanson.get(rowIndex).getTitre();
        case 1:
            return listeChanson.get(rowIndex).getSonAlbum();
        case 2: 
        	return listeChanson.get(rowIndex).getUnGenre().getNomGenre();
        case 3:
            return listeChanson.get(rowIndex).getArtiste();
        case 4:
            return listeChanson.get(rowIndex).getCompositeur();
        case 5:
            return listeChanson.get(rowIndex).getFormat();
        case 6: 
        	return listeChanson.get(rowIndex).getPathFichier();
        default:
            return null; //Ne devrait jamais arriver
    }
       }
    
    /**
     * Retourne le chemin du fichier 
     * @param rowIndex
     * @param columnIndex
     * @return
     */
 public Object getPath(int rowIndex, int columnIndex){
    	
    	switch(columnIndex){
        case 0:
        	return listeChanson.get(rowIndex).getPathFichier();
        case 1:
        	return listeChanson.get(rowIndex).getPathFichier();
        case 2: 
        	return listeChanson.get(rowIndex).getPathFichier();
        case 3:
        	return listeChanson.get(rowIndex).getPathFichier();
        case 4:
        	return listeChanson.get(rowIndex).getPathFichier();
        case 5:
        	return listeChanson.get(rowIndex).getPathFichier();
        case 6: 
        	return listeChanson.get(rowIndex).getPathFichier();
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
        	return listeChanson.get(rowIndex);
        case 1:
        	return listeChanson.get(rowIndex);
        case 2: 
        	return listeChanson.get(rowIndex);
        case 3:
        	return listeChanson.get(rowIndex);
        case 4:
        	return listeChanson.get(rowIndex);
        case 5:
        	return listeChanson.get(rowIndex);
        case 6: 
        	return listeChanson.get(rowIndex);
        default:
            return null; //Ne devrait jamais arriver
    	
    	
		}
    }
    
   
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	
    }
	public void reloadData(ArrayList<Chanson> col)  {
        
    	listeChanson.clear();
    	listeChanson = col;
        
    }
    
}

