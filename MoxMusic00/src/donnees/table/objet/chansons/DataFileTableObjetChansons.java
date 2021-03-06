package donnees.table.objet.chansons;
	
	import javax.swing.*;

import classes.Chanson;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

	public class DataFileTableObjetChansons extends JPanel {
		
		// le tableau
		private JTable table;                
	    // le modèle
	    private DataFileTableModelObjetChansons modelP;
	    
	    public DataFileTableObjetChansons(ArrayList<Chanson> col) {
	    
	    //font
	    Font f=new Font("SanSerif",Font.PLAIN,24);
	    setFont(f);
	    //gestionnaire de positionnement
	    setLayout(new BorderLayout());
	    table=new JTable();
	    
	    //construction du modèle de remplissage à partir du fichier en fonction du model voulue
	    // si ce n'est pas une playlist on prend un certain modele de tableau
	 
	    	modelP = new DataFileTableModelObjetChansons(col);
	    	table.setModel(modelP);
	    
	    //création du tableau
	   
	    table.createDefaultColumnsFromModel();
	    //scroller
	    JScrollPane scrollpane=new JScrollPane(table);
	    add(scrollpane);
	   }
	    public DataFileTableObjetChansons() {
		    
		    //font
		    Font f=new Font("SanSerif",Font.PLAIN,24);
		    setFont(f);
		    //gestionnaire de positionnement
		    setLayout(new BorderLayout());
		    table=new JTable();
		    
		    //construction du modèle de remplissage à partir du fichier en fonction du model voulue
		    // si ce n'est pas une playlist on prend un certain modele de tableau
		 
		    	modelP = new DataFileTableModelObjetChansons();
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

		public DataFileTableModelObjetChansons getModelP() {
			return modelP;
		}

		public void setModelP(DataFileTableModelObjetChansons modelP) {
			this.modelP = modelP;
		}
		public void refresh(DataFileTableModelObjetChansons modelP) {
	        table.setModel(modelP);
	        modelP.fireTableDataChanged();
	        setModelP(modelP);
	        this.repaint();
	        this.revalidate();
	    }
		
}
