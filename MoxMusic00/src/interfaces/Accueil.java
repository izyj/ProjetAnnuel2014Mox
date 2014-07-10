package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import lecteur.AudioControledPlayer;
import lecteur.AudioControledPlayerGUI;

import commun.Page;
import donnees.table.DataFileTable;
import fichier.AjoutMusique;


/**
 *
 * @author iisiramen
 */
public class Accueil extends Page implements ActionListener, MouseListener {
	
	
	private String nomFichierChanson = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "chanson.txt";
	private String nomFichierPlaylist = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "Playlist.txt";
    protected JButton ajoutMot;
    protected JButton supprimerMot;
    protected JButton BoutonPause;
    protected JButton score;
    protected JButton boutonLecture = new JButton();
    protected JPanel contener4 = new JPanel();
    protected JPanel Phaut = new JPanel();
    protected JPanel PBas = new JPanel();
    protected JPanel contener = new JPanel();
    private DataFileTable tablo;
    private AudioControledPlayerGUI audio = new AudioControledPlayerGUI();
    public Accueil() {

        
        contener4= new JPanel();
        contener4.setOpaque(false);

        //--------------------------------------------------

        
        AjoutMusique  cPath = new AjoutMusique();
        cPath.creationChemin(nomFichierChanson);
        cPath.creationChemin(nomFichierPlaylist);
        tablo=new DataFileTable(nomFichierChanson, false);
        tablo.getTable().addMouseListener(this);
        //configuration de la fenêtre
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.black);
        this.setBackground(Color.lightGray);
        this.setSize(tablo.getPreferredSize());
        if (super.menu.getRefresh() == 1){
        	
        	tablo.repaint();
        	super.menu.setRefresh(0);
        }      
        
        contener4.add(audio);
        contener.add(contener4);
        
        addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermer grace a la croix
        this.getContentPane().add(tablo,BorderLayout.CENTER);
        this.getContentPane().add(contener,BorderLayout.SOUTH);
        
        PBas.setLayout(new BorderLayout());
        this.setVisible(true);
    }
public void actionPerformed(ActionEvent e) {
		String path;
		int ligne = tablo.getTable().getSelectedRow();//Si tu veut la ligne selectionnée
		int colonne = tablo.getTable().getSelectedColumn();//Si tu veut la colonne selectionnée
        if(e.getSource()==boutonLecture && ligne > -1 && colonne > -1){
        	
        	path = (String) tablo.getTable().getValueAt(ligne, colonne);
        
        	
        	contener.add(audio);
        	contener.repaint();
        	System.out.println("ll");
        	
        }


        }
@Override
public void mouseClicked(MouseEvent e) {
	String path;
	int ligne = tablo.getTable().getSelectedRow();
	int colonne = tablo.getTable().getSelectedColumn();
	 if( ligne > -1 && colonne > -1){
     	
     	path = (String) tablo.getModel().getPath(ligne, colonne);
     	
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
}