package fichier;

import java.io.File;
import java.util.ArrayList;

import lecteur.AudioParser;

import classes.Chanson;

public class AjoutFichierRepertoire {
	private String nomFichierChanson = "C:" + File.separator + "MoxMusic" +File.separator+ "Content" + File.separator + "chanson.txt";
    private String initialpath = "";
    private Boolean recursivePath = false;
    public int filecount = 0;
    public int dircount = 0;

/**
 * Constructeur
 * @param path chemin du répertoire
 * @param subFolder analyse des sous dossiers
 */
    public AjoutFichierRepertoire(String path, Boolean subFolder) {
        super();
        this.initialpath = path;
        this.recursivePath = subFolder;
        ajoutFichierRepertoire(initialpath);
    }

    public void list() {
        this.ajoutFichierRepertoire(this.initialpath);
    }

    private ArrayList<Object> ajoutFichierRepertoire(String dir) {
    	ArrayList<Object> colChanson = new ArrayList<Object>();
    	String nomFichier;
    	AudioParser recupChanson;
    	AjoutMusique unAjout = new AjoutMusique();
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == true) {
                
                    System.out.println("Dossier" + files[i].getAbsolutePath());
                    this.dircount++;
                    
                } else {
                   nomFichier = files[i].getName();
                   if(nomFichier.endsWith("Mp3")){
                	 recupChanson = new AudioParser(files[i].getAbsolutePath());
                	 colChanson.add(recupChanson.getUneChanson()); 
                	 System.out.println("Dossier" + files[i].getAbsolutePath());
                	 System.out.println("ajout");
                   }
                }
                if (files[i].isDirectory() == true && this.recursivePath == true) {
                    this.ajoutFichierRepertoire(files[i].getAbsolutePath());
                }
                
                unAjout.insertCollectionFichier(colChanson,nomFichierChanson);
                
            }
        }
		return colChanson;
    }

}
