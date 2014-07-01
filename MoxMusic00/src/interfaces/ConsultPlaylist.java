package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import lecteur.AudioControledPlayerGUI;

import org.apache.tika.parser.jpeg.JpegParser;


import classes.Chanson;
import classes.Playlist;

import commun.Page;
import donnees.table.DataFileTable;
import donnees.table.objet.Playlist.DataFileTableObjet;
import donnees.table.objet.Playlist.DataTableFileModelPlaylistObjet;
import donnees.table.objet.chansons.DataFileTableModelObjetChansons;
import donnees.table.objet.chansons.DataFileTableObjetChansons;
import fichier.AjoutMusique;

/**
 * Permet de visualiser les playlists (seulement le nom)
 * @author Isaac2
 *
 */
public class ConsultPlaylist extends Page implements ActionListener, MouseListener, TreeSelectionListener {
	
	
	
	private String nomFichierPlaylist = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "Playlist.txt";
	private Playlist maPlaylist;
	private ArrayList<Chanson> lesChansons;
	private JButton ajouter;
	private AudioControledPlayerGUI audio = new AudioControledPlayerGUI();
	private JPanel pbas;
	private JPanel phaut;
	private JPanel paneltree;
	private JPanel panelfic = new JPanel();
	private String nomPlayl;
	private DataFileTableObjetChansons table;
	private DataFileTableModelObjetChansons modeltable;
	private Playlist selection;
	private JTree tree;
	private ArrayList<Playlist> colPlaylists;
	private AjoutMusique  ajout;
	
	public ConsultPlaylist() {
		// on va chercher les musique
		maPlaylist = new Playlist();
		maPlaylist.setLesChansons(lesChansons);
		ajout =  new AjoutMusique();
		colPlaylists = (ArrayList<Playlist>)(Object) ajout.recuperationMusique(nomFichierPlaylist);
		
		 //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Playlists");
        
        //create the child nodes
        for(Playlist  playlist : colPlaylists){
        	
        DefaultMutableTreeNode list = new DefaultMutableTreeNode(playlist.getNomPlaylist());
        //add the child nodes to the root node
        root.add(list);
        }
        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.addTreeSelectionListener(this);
		tree.addMouseListener(this);
		paneltree = new JPanel();
		paneltree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table = new DataFileTableObjetChansons();
		table.getTable().addMouseListener(this);
		phaut = new JPanel();
		phaut.setLayout(new BorderLayout());
		
		
		
		
		pbas = new JPanel();
		
		paneltree.add(audio,BorderLayout.SOUTH);
		paneltree.add(table,BorderLayout.NORTH);
		//paneltree.add(tree,BorderLayout.WEST);
		pbas.add(tree);
		phaut.add(pbas,BorderLayout.WEST);
		phaut.add(paneltree,BorderLayout.EAST);
		
		
		this.getContentPane().add(phaut,BorderLayout.CENTER);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ajouter){
			
			AjoutMusiquePlaylist k = new AjoutMusiquePlaylist(nomPlayl);
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
//		int ligne = table.getTable().getSelectedRow();
//		int colonne = table.getTable().getSelectedColumn();
//		 if( ligne > -1 && colonne > -1){
//	     	
//			 
//			 selection = (Playlist) table.getModelP().getObjet(ligne, colonne);
//			 
//		 }
		
		String path;
		int ligne = table.getTable().getSelectedRow();
		int colonne = table.getTable().getSelectedColumn();
		 if( ligne > -1 && colonne > -1){
	     	
	     	path = (String) table.getModelP().getPath(ligne, colonne);
	     	
	     	audio.setAudioFile(new File(path));
	     	System.out.println(audio.getAudioFile().getPath());
	     	//Chanson cellule = tablo.getTable().getValueAt(ligne,colonne);
	     	
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

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		int i =0 ;
		//Returns the last path element of the selection.
		//This method is useful only when the selection model allows a single selection.
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                       tree.getLastSelectedPathComponent();

		    if (node == null)
		    return;

		    Object nodeInfo = node.getUserObject();
		    if (node.isLeaf()) {
		        String nomPlaylist = (String)nodeInfo;
		        
		        while(i < colPlaylists.size() && !colPlaylists.get(i).getNomPlaylist().equalsIgnoreCase(nomPlaylist)){
		        	i+=1;
		        }
		        if (colPlaylists.get(i).getNomPlaylist().equalsIgnoreCase(nomPlaylist)){
		        	
		        	modeltable = new DataFileTableModelObjetChansons(colPlaylists.get(i).getLesChansons());
		        	
		        	 
		        	  
		        	
		        	table.refresh(modeltable);
		        	//table.getTable().reloadData();
		        	this.repaint();
		        	this.revalidate();
		        	//System.out.println(colPlaylists.get(i).getLesChansons().get(0).getPathFichier());
	        	}
		        System.out.println(nomPlaylist);
		        
		    } else {
		        //displayURL(helpURL); 
		    }
		
	}
	

}
