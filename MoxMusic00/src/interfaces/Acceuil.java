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
public class Acceuil extends Page implements ActionListener, MouseListener {
	
	
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
    public Acceuil() {

        
        contener4= new JPanel();
        contener4.setOpaque(false);

        //--------------------------------------------------

        

        tablo=new DataFileTable(nomFichierChanson, false);
        //configuration de la fenêtre
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setForeground(Color.black);
        this.setBackground(Color.lightGray);
       // this.getContentPane().add(tablo,"Center");
        this.setSize(tablo.getPreferredSize());
        //affichage
        //Phaut.add(tablo);
        if (super.menu.getRefresh() == 1){
        	
        	tablo.repaint();
        	super.menu.setRefresh(0);
        }
        //écouteur pour fermeture
      //  fen.addWindowListener(new WindowCloser());
        
        
        //-----------------------------------------------
        
//        BoutonPause = new JButton(new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonPause.png"));
//        BoutonPause.setPressedIcon(new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonPause.png"));
//        BoutonPause.addActionListener(this);
//        BoutonPause.setOpaque(false);
//        BoutonPause.setContentAreaFilled(false);
//        BoutonPause.setBorderPainted(false);
//        BoutonPause.setPreferredSize(new Dimension(200,100));
//        contener4.add(BoutonPause);
// 
//        
//        
//        boutonLecture = new JButton(new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonLecture.png"));
//        boutonLecture.setPressedIcon(new ImageIcon("D:\\ESGI\\projets\\projet annuel\\2013-2014\\Java\\images\\boutonLecture.png"));
//        boutonLecture.addActionListener(this);
//        boutonLecture.setOpaque(false);
//        boutonLecture.setContentAreaFilled(false);
//        boutonLecture.setBorderPainted(false);
//        boutonLecture.setPreferredSize(new Dimension(100,100));
        
        
        
        contener4.add(audio);
        contener.add(contener4);
        AjoutMusique  cPath = new AjoutMusique();
        tablo.getTable().addMouseListener(this);
        cPath.creationChemin(nomFichierChanson);
        cPath.creationChemin(nomFichierPlaylist);
        addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermer grace a la croix
        this.getContentPane().add(tablo,BorderLayout.CENTER);
        this.getContentPane().add(contener,BorderLayout.SOUTH);
        
        PBas.setLayout(new BorderLayout());
        //this.getContentPane().add(PBas,BorderLayout.SOUTH);
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
        	//Chanson cellule = tablo.getTable().getValueAt(ligne,colonne);
        	
        }
	//        if(e.getSource()==supprimerMot){
        	//            SupprimerMot test = new SupprimerMot();       

	//        }
	//      if(e.getSource()==nouvellePartie){
        	//       Partie start = new Partie();

	//      }
        
	//      if(e.getSource()==score){
	//      VoirScore sc = new VoirScore();

	//     }
        
	//     if(e.getSource()==quitter){
	//         System.exit(0);

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