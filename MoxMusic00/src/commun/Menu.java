package commun;

import interfaces.AjoutMusiquePlaylist;
import interfaces.ConsultPlaylist;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.Database;
import plugins.PluginsLoader;
import fichier.AjoutFichierRepertoire;
import fichier.AjoutMusique;
import lecteur.AudioParser;

public class Menu implements ActionListener {

	private String nomFichierPlaylist = "C:" + File.separator + "MoxMusic"
			+ File.separator + "Content" + File.separator + "Playlist.txt";
	private String nomFichier = "C:" + File.separator + "MoxMusic"
			+ File.separator + "Content" + File.separator + "chanson.txt";
	private JMenuBar menu = new JMenuBar();
	private JMenu ongletFichier = new JMenu("Fichier");
	private JMenu ongletFonctionnalite = new JMenu("Fonctionnalite");
	private JMenu ongletParametre = new JMenu("Parametre");
	private JMenu ongletAide = new JMenu("Aide");
	private JMenu ongletPlugin = new JMenu("Jeu");
	private JMenuItem jouer = new JMenuItem("Jouer au jeu");
	private JMenuItem aide = new JMenuItem("Documentation");
	private JMenuItem inscription = new JMenuItem("S'inscrire");
	private JMenuItem creationPlaylist = new JMenuItem("CrÃ©e une playlist");
	private JMenuItem visuPlaylist = new JMenuItem("Voir playlists");
	private JMenuItem ajouterFichier = new JMenuItem("Ajouter un Fichier");
	private JMenuItem ajoutDossier = new JMenuItem("Ajouter un dossier");

	private int refresh = 0;

	public Menu(JFrame Fenetre) {

		// JFrame.setDefaultLookAndFeelDecorated(true);
		this.ongletParametre.add(inscription);

		inscription.setToolTipText("Permet de s'inscrire au site");
		this.ongletFichier.add(ajouterFichier);
		ajouterFichier.addActionListener(this);
		ajouterFichier.setToolTipText("Ajouter une musique"); // Active
																// l'infobulle
		this.ongletFichier.add(ajoutDossier);
		ajoutDossier.addActionListener(this);
		ajoutDossier.setToolTipText("Ajouter un repertoire de musique");

		this.ongletAide.add(aide);
		aide.setToolTipText("Affiche la documentation d'aide.");
		aide.addActionListener(this);

		this.ongletFonctionnalite.add(creationPlaylist);
		creationPlaylist.addActionListener(this);
		creationPlaylist.setToolTipText("CrÃ©e une playlist");

		this.ongletFonctionnalite.add(visuPlaylist);
		visuPlaylist.addActionListener(this);
		visuPlaylist.setToolTipText("visualiser playlist");
		this.inscription.addActionListener(this);

		this.ongletPlugin.add(jouer);
		jouer.addActionListener(this);

		this.menu.add(ongletPlugin);
		this.menu.add(ongletFichier);
		this.menu.add(ongletFonctionnalite);
		this.menu.add(ongletParametre);
		this.menu.add(ongletAide);
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

		if (e.getSource() == ajouterFichier) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3",
					"Mp3", "wav");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getName() + " "
						+ chooser.getSelectedFile().getAbsolutePath() + " "
						+ chooser.getSelectedFile().getPath());
				AjoutMusique ajout = new AjoutMusique();
				ajout.insertFichier(
						chooser.getSelectedFile().getAbsolutePath(), nomFichier);
				refresh += 1;
			}
		}

		if (e.getSource() == aide) {

		}

		if (e.getSource() == ajoutDossier) {

			JFileChooser chooser = new JFileChooser();
			// Selection de repertoire
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3",
					"Mp3", "wav");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getName() + " "
						+ chooser.getSelectedFile().getAbsolutePath() + " "
						+ chooser.getSelectedFile().getPath());
				AjoutFichierRepertoire ajout = new AjoutFichierRepertoire(
						chooser.getSelectedFile().getAbsolutePath(), true);
				refresh += 1;
			}

		}
		if (e.getSource() == creationPlaylist) {
			String NomPlaylist = "";
			NomPlaylist = JOptionPane.showInputDialog("nomplaylist");

			if (!NomPlaylist.equals("")) {
				AjoutMusiquePlaylist ajout = new AjoutMusiquePlaylist(
						NomPlaylist);
			}
		}
		if (e.getSource() == inscription) {
			JPanel userPanel = new JPanel();
			userPanel.setLayout(new GridLayout(4,2));

			//Labels for the textfield components        
			JLabel mailLbl = new JLabel("E-mail :");
			JLabel passwordLbl = new JLabel("Mot de passe:");
			JLabel nomLbl = new JLabel("Nom :");
			JLabel prenomLbl = new JLabel("Prénom :");

			JTextField mailFld = new JTextField();
			JPasswordField passwordFld = new JPasswordField();
			JTextField nomFld = new JTextField();
			
			//numéro interdits
			nomFld.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char caracter = e.getKeyChar();
	                if ((caracter < 65) || (caracter > 90) && (caracter < 97) || (caracter > 122)&& (caracter < 130) || (caracter > 130))
	                {
	                    e.consume();
	                }
	            }
	        });
			
			JTextField prenomFld = new JTextField();
			prenomFld.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                char caracter = e.getKeyChar();
	                if ((caracter < 65) || (caracter > 90) && (caracter < 97) || (caracter > 122)&& (caracter < 130) || (caracter > 130))
	                {
	                    e.consume();
	                }
	            }
	        });
			//Ajout des fields + lbl      
			userPanel.add(mailLbl);
			userPanel.add(mailFld);
			
			
			userPanel.add(passwordLbl);
			userPanel.add(passwordFld);
			
			userPanel.add(nomLbl);
			userPanel.add(nomFld);
			
			userPanel.add(prenomLbl);
			userPanel.add(prenomFld);

			int input = 0;
			while(input != 2)
			{
				input = JOptionPane.showConfirmDialog(null, userPanel, "Enter your password:"
	                      ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(input == -1)
				{
					input = 2;
				}
				//Si accepté :
				if(input == 0)
				{
					char[] password = passwordFld.getPassword();
					String pwd = "";
					String nom = "";
					String prenom = "";
					String mail = "";
					
					for(int ind = 0; ind < password.length;ind++)
					{
						pwd += password[ind];
					}
					
					nom = nomFld.getText();
					prenom = prenomFld.getText();
					mail = mailFld.getText();
					
					if(pwd == "" || nom == "" || prenom == "" || mail == "")
						JOptionPane.showMessageDialog(null, "Un des champs n'a pas été renseigné.");
					else{
						Database db = new Database();
						if(db.inscription(pwd, nom, prenom, mail) == 1)
						{
							JOptionPane.showMessageDialog(null, "Inscription réussite");
						}
						else{
							JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
						input = 2;
					}
				}
			}
			
			
			
			
			
		}
		if (e.getSource() == visuPlaylist) {

			ConsultPlaylist ajout = new ConsultPlaylist();
		}
		if (e.getSource() == jouer) {

			PluginsLoader pl = new PluginsLoader(
					new String[] { "src\\test.jar" });

		}

	}

}
