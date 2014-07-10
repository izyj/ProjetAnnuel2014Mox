package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.BorderUIResource;

import commun.Page;

import classes.Chanson;
import classes.Playlist;

import donnees.table.DataFileTable;
import fichier.AjoutMusique;


public class AjoutMusiquePlaylist extends Page implements MouseListener, ActionListener  {
	private DataFileTable table;
	private String nomFichierChanson = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "chanson.txt";
	private String nomFichierPlaylist = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "Playlist.txt";
	private Chanson selection= null;
	private Playlist playlist = new Playlist();
	private ArrayList<Chanson> colChanson = new ArrayList<Chanson>();
	private JButton ajoutPlaylist = new JButton("Ajouter a la playlist");
	private JButton valider = new JButton("Valider");
	private AjoutMusique ajout = new AjoutMusique();
	private JPanel pan = new JPanel();
	private JPanel pan2 = new JPanel();
	public AjoutMusiquePlaylist() {
		super();
		
	}
	/**
	 * Classe permettant de mettre les musiques dans une playlist
	 * @param ajoutPlaylist
	 */
	 public AjoutMusiquePlaylist( String nomPlaylist ) {  
	        
		 	this.playlist.setNomPlaylist(nomPlaylist);
	        pan.setLayout(new BorderLayout());
	        table = new DataFileTable(nomFichierChanson, false);
	        table.getTable().addMouseListener(this); 
	        ajoutPlaylist.addActionListener(this);
	        valider.addActionListener(this);
	        
	        
	        pan.add(table,BorderLayout.NORTH);
	        pan2.add(ajoutPlaylist);
	        pan2.add(valider);
	        this.getContentPane().add(pan,BorderLayout.NORTH);
	        this.getContentPane().add(pan2,BorderLayout.SOUTH);
	        this.setSize(table.getPreferredSize());
	        this.pack();  
	        
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(selection != null && e.getSource() == ajoutPlaylist){
			
			colChanson.add(selection);
			
			selection =null;
			System.out.println("ajouter");
		}
		
		if(colChanson.isEmpty()== false && e.getSource() == valider){
			
			playlist.setLesChansons(colChanson);
			ajout.insertFichierObject(playlist, nomFichierPlaylist);
			this.dispose();
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
		int ligne = table.getTable().getSelectedRow();
		int colonne = table.getTable().getSelectedColumn();
		 if( ligne > -1 && colonne > -1){
	     	
			 
			 selection = (Chanson) table.getModel().getObjet(ligne, colonne);	     
	     	
	     	
		 }
	     }
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}  

	

}
