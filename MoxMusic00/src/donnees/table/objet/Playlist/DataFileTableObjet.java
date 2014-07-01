package donnees.table.objet.Playlist;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DataFileTableObjet extends JPanel {
	
	// le tableau
	private JTable table;                
    // le modèle
    private DataTableFileModelPlaylistObjet modelP;
    
    public DataFileTableObjet(String nomFichier, boolean Playlist) {
    
    //font
    Font f=new Font("SanSerif",Font.PLAIN,24);
    setFont(f);
    //gestionnaire de positionnement
    setLayout(new BorderLayout());
    table=new JTable();
    
    //construction du modèle de remplissage à partir du fichier en fonction du model voulue
    // si ce n'est pas une playlist on prend un certain modele de tableau
 
    	modelP = new DataTableFileModelPlaylistObjet(nomFichier);
    	table.setModel(modelP);
    
    //création du tableau
   
    table.createDefaultColumnsFromModel();
    //scroller
    JScrollPane scrollpane=new JScrollPane(table);
    add(scrollpane);
   }

	public Dimension getPreferredSize() {
    return new Dimension(600, 500);
   }
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public DataTableFileModelPlaylistObjet getModelP() {
		return modelP;
	}
	public void setModelP(DataTableFileModelPlaylistObjet modelP) {
		this.modelP = modelP;
	}

	
	
}
