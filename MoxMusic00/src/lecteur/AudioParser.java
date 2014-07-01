package lecteur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import classes.Chanson;
import  classes.Genre;
import fichier.AjoutMusique;
public class AudioParser {
	
	private Chanson uneChanson;
	
    /**
     * @param args
     */
	public AudioParser() {
		
	}
	
	/*
	 * Contructeur AudioParser sert a récupérer les information sur une Chanson
	 * On lui passe le chemin de la chanson en parametre
	 */
	public AudioParser(String cheminFichier) {
		super();
		
		try {
			File  fichier = new File(cheminFichier);
	        InputStream input = new FileInputStream(fichier);
	        ContentHandler handler = new DefaultHandler();
	        Metadata metadata = new Metadata();
	        Parser parser = new Mp3Parser();
	        ParseContext parseCtx = new ParseContext();
	        parser.parse(input, handler, metadata, parseCtx);
	        input.close();
	
	        // List all metadata
	        String[] metadataNames = metadata.names();
	
//	        for(String name : metadataNames){
//	        System.out.println(name + ": " + metadata.get(name));
//	        }
	        System.out.println("----------------------------------------------");
	        String title = metadata.get("title");
	        if(title == null)
	        	title = fichier.getName();
	        	System.out.println(title);
	        String artists = metadata.get("xmpDM:artist");
	        if(artists == null)
	        	artists = "Inconnu";
	        
	        String composer = metadata.get("xmpDM:composer");
	        if(composer == null)
	        	composer= "Inconnu";
	        
	        String genre  = metadata.get("xmpDM:genre");
	        if(genre == null)
	        	genre = "Inconnu";
	        
	        String album = metadata.get("xmpDM:album");
	        if(album == null)
	        	album = "Inconnu";
	        
	        String type = metadata.get("xmpDM:audioCompressor");
	        if(type == null)
	        	type = "Inconnu";
	        // Retrieve the necessary info from metadata
	        
	        Genre unGenre = new Genre(genre);
	        this.uneChanson = new Chanson(title,unGenre,artists, composer,album,cheminFichier,type);	      
	        
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.out.print(e.getMessage());
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (TikaException e) {
        e.printStackTrace();
        }
		
		
     }

	public Chanson getUneChanson() {
		return uneChanson;
	}
	public void setUneChanson(Chanson uneChanson) {
		this.uneChanson = uneChanson;
	}

	
}
	

