package commun;

import interfaces.AjoutMusiquePlaylist;
import interfaces.ConsultPlaylist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import plugins.PluginsLoader;

import fichier.AjoutFichierRepertoire;
import fichier.AjoutMusique;

import lecteur.AudioParser;






public class Menu implements ActionListener {
	
	private String nomFichierPlaylist = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "Playlist.txt";
	private String nomFichier = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "chanson.txt";
	private JMenuBar menu = new JMenuBar();
	private JMenu ongletFichier = new JMenu("Fichier");
	private JMenu ongletFonctionnalite = new JMenu("Fonctionnalite");
	private JMenu ongletParametre = new JMenu("Parametre");
	private JMenu ongletPlugin = new JMenu("Jeu");
	private JMenuItem jouer = new JMenuItem("Jouer au jeu");
	private JMenuItem aide = new JMenuItem("Aide");
	private JMenuItem inscription = new JMenuItem("S'inscrire");
	private JMenuItem creationPlaylist = new JMenuItem("Crée une playlist");
	private JMenuItem visuPlaylist = new JMenuItem("Voir playlists");
	private JMenuItem ajouterFichier = new JMenuItem("Ajouter un Fichier");
	private JMenuItem ajoutDossier = new JMenuItem("Ajouter un dossier");
   
   private int refresh = 0;
  
   public Menu(JFrame Fenetre){
    

	//JFrame.setDefaultLookAndFeelDecorated(true);
	this.ongletParametre.add(inscription);
	this.ongletParametre.add(aide);
	inscription.setToolTipText("Permet de s'inscrire au site");
    this.ongletFichier.add(ajouterFichier);
    ajouterFichier.addActionListener(this);
    ajouterFichier.setToolTipText("Ajouter une musique"); // Active l'infobulle
    this.ongletFichier.add(ajoutDossier);
    ajoutDossier.addActionListener(this);
    ajoutDossier.setToolTipText("Ajouter un repertoire de musique");
    
    this.ongletFonctionnalite.add(creationPlaylist);
    creationPlaylist.addActionListener(this);
    creationPlaylist.setToolTipText("Crée une playlist");
    
    this.ongletFonctionnalite.add(visuPlaylist);
    visuPlaylist.addActionListener(this);
    visuPlaylist.setToolTipText("visualiser playlist");
    
    this.ongletPlugin.add(jouer);
    jouer.addActionListener(this);
   
    this.menu.add(ongletPlugin);
    this.menu.add(ongletFichier);
    this.menu.add(ongletFonctionnalite);
    this.menu.add(ongletParametre);
  
            }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar Menu) {
        this.menu = Menu;
    }
   
   

    public JMenu getOngletClient() {
        return ongletFichier;
    }

    public void setOngletClient(JMenu OngletClient) {
        this.ongletFichier = OngletClient;
    }

    
    
    public JMenu getOngletFichier() {
		return ongletFichier;
	}

	public void setOngletFichier(JMenu ongletFichier) {
		this.ongletFichier = ongletFichier;
	}

	public JMenu getOngletFonctionnalite() {
		return ongletFonctionnalite;
	}

	public void setOngletFonctionnalite(JMenu ongletFonctionnalite) {
		this.ongletFonctionnalite = ongletFonctionnalite;
	}

	public JMenuItem getCreationPlaylist() {
		return creationPlaylist;
	}

	public void setCreationPlaylist(JMenuItem creationPlaylist) {
		this.creationPlaylist = creationPlaylist;
	}

	public JMenuItem getVisuPlaylist() {
		return visuPlaylist;
	}

	public void setVisuPlaylist(JMenuItem visuPlaylist) {
		this.visuPlaylist = visuPlaylist;
	}

	public JMenuItem getAjouterFichier() {
		return ajouterFichier;
	}

	public void setAjouterFichier(JMenuItem ajouterFichier) {
		this.ajouterFichier = ajouterFichier;
	}

	public JMenuItem getAjoutDossier() {
		return ajoutDossier;
	}

	public void setAjoutDossier(JMenuItem ajoutDossier) {
		this.ajoutDossier = ajoutDossier;
	}

	public int getRefresh() {
		return refresh;
	}
    
    public void setRefresh(int refresh) {
		this.refresh = refresh;
	}


    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==ajouterFichier){
    		JFileChooser chooser = new JFileChooser();
    		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    		FileNameExtensionFilter filter = new FileNameExtensionFilter(
    		    "Mp3", "Mp3", "wav");
    		chooser.setFileFilter(filter);
    		int returnVal = chooser.showOpenDialog(null);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
    		   System.out.println("You chose to open this file: " +
    		       chooser.getSelectedFile().getName()+" "+ chooser.getSelectedFile().getAbsolutePath()+" "+chooser.getSelectedFile().getPath());
    		   	AjoutMusique ajout = new AjoutMusique();
       			ajout.insertFichier(chooser.getSelectedFile().getAbsolutePath(),nomFichier);
       			refresh +=1;
       			
    		}
    		
        }


        if(e.getSource()==ajoutDossier){
           
        	JFileChooser chooser = new JFileChooser();
        	//Selection de repertoire
        	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		FileNameExtensionFilter filter = new FileNameExtensionFilter(
    		    "Mp3", "Mp3", "wav");
    		chooser.setFileFilter(filter);
    		int returnVal = chooser.showOpenDialog(null);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
    		   System.out.println("You chose to open this file: " +
    		       chooser.getSelectedFile().getName()+" "+ chooser.getSelectedFile().getAbsolutePath()+" "+chooser.getSelectedFile().getPath());
    		   AjoutFichierRepertoire ajout = new AjoutFichierRepertoire(chooser.getSelectedFile().getAbsolutePath(), true);
    		   refresh +=1;
    		}
    		
    	
    		
        	
        }
    	if(e.getSource()==creationPlaylist){
    		String NomPlaylist ="";
    		NomPlaylist = JOptionPane.showInputDialog("nomplaylist");
    		
    		if(!NomPlaylist.equals("") ){
    			AjoutMusiquePlaylist ajout =new AjoutMusiquePlaylist(NomPlaylist);	
    		}
		}
    	if(e.getSource() == visuPlaylist){
    		
    		
    		
    		ConsultPlaylist ajout =new ConsultPlaylist();	
    	}
    	if(e.getSource() == jouer){
    		
    		PluginsLoader pl = new PluginsLoader(new String[]{"src\\test.jar"});
    		

    	}
                

    }






}
