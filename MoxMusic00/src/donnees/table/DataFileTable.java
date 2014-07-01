package donnees.table;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DataFileTable extends JPanel {

	private JTable table;                // le tableau
    private DataFileTableModel model;    // le modèle
    private DataFileTableModelPlaylist modelP;
    
    public DataFileTable(String nomFichier, boolean Playlist) {
    
    //font
    Font f=new Font("SanSerif",Font.PLAIN,24);
    setFont(f);
    //gestionnaire de positionnement
    setLayout(new BorderLayout());
    table=new JTable();
    
    //construction du modèle de remplissage à partir du fichier en fonction du model voulue
    // si ce n'est pas une playlist on prend un certain modele de tableau
    if(Playlist == false){
    	model = new DataFileTableModel(nomFichier);
    	table.setModel(model);
    }
    else{
    	modelP = new DataFileTableModelPlaylist(nomFichier);
    	table.setModel(modelP);
    }
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
	public DataFileTableModel getModel() {
		return model;
	}
	public void setModel(DataFileTableModel model) {
		this.model = model;
	}
	
	
}