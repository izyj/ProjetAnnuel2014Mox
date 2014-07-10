package fichier;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import lecteur.AudioParser;



import classes.Chanson;
import classes.Playlist;
	
	/**
	 *Clase Gerant les fichiers 
	 * @author Isaac
	 */
	public class AjoutMusique {
		
		
		
		private ArrayList<Object> colChanson;
		private String CheminFichierAjout ;
		/**Constructeur creation Fichier   **/
		public AjoutMusique() {
	
		}
	
		/** Cette methode verifie si les dossier qui vont recevoir les fichiers existe s'il n'éxiste pas les crées
		 *
		 * 
		 */
		public void creationChemin( String nomFichier){
			
			File targetFile = new File(nomFichier);
			File parent = targetFile.getParentFile();
			if(!parent.exists() && !parent.mkdirs())
			   parent.mkdirs();
			
			   if (!targetFile.exists())
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("problem create file "+ e.getMessage());
				}    
			
			
			
		}
		
		/**
		 * Ajoute une collection de Chanson dans le fichier.
		 * @param chansons
		 */
		public void insertCollectionFichier(ArrayList<Object> chansons,String nomFichier){
			File fichiertest = new File(nomFichier);
			//ArrayList<Chanson> colChansonRecup;
			// si un collection existe alors ajoute la recupere
			if(collectionChansonExiste(nomFichier)){
				 colChanson = recuperationMusique(nomFichier);
			
				 for(Object uneChanson : chansons){
				
					 colChanson.add(uneChanson);
				 }
				
			}else{
				colChanson =chansons;
			}
			try {
	            FileOutputStream fichier = new FileOutputStream(nomFichier);
	            ObjectOutputStream s = new ObjectOutputStream(fichier);
	            try {
	               
	                s.writeObject(colChanson);
	                s.flush();
	                s.close();
	                
	                }
	                    finally {
	                    	fichier.flush();
	                    	fichier.close();
	                            }
	       }
	         catch (IOException e) {
	            System.out.println("Probleme creation fichier "+ e.getMessage());
	           e.printStackTrace();
	        }
	     
	    	 }

		/**Ajoute la musique (PathFile) au fichier de referencement (nomFichier)
		 * 
		 * @param pathFile
		 * @param nomFichier
		 */
	     public void insertFichier(String pathFile,String  nomFichier){
	    	  File fichierAjout = new File(pathFile);
	    	  File fichiertest = new File(nomFichier);
	    	 // System.out.println("chemin "+nomFichier);
	    	  // si c'est un fichier alors on le recupere sous forme de chanson
		    	 if(fichierAjout.isFile()){
		    		 	AudioParser info = new AudioParser(pathFile);
		    		 	Chanson chanson = info.getUneChanson();
		    		 	//si des chansons sont deja presente dans le fichier alors on  les recuperes
		    		 	if(collectionChansonExiste(nomFichier)){
		    		 		 colChanson = recuperationMusique(nomFichier);
		    	   		 }
		    		 	else{
		    		 		 colChanson = new ArrayList<Object>();
		    		 	}
		    		 	colChanson.add(chanson);
		    		 	//insertion
		        try {
		            FileOutputStream fichier = new FileOutputStream(nomFichier);
		            ObjectOutputStream s = new ObjectOutputStream(fichier);
		            try {
		               
		                s.writeObject(colChanson);
		                s.flush();
		                s.close();
		                
		                }
		                    finally {
		                    	fichier.flush();
		                    	fichier.close();
		                            }
		       }
		         catch (IOException e) {
		            System.out.println("Probleme creation fichier "+ e.getMessage());
		           e.printStackTrace();
		        }
		     
		    	 }

	    	 }
	     /**
	      * Retourne la collection presente dans le fichier mis en parametre.
	      * @param nomFichier
	      * @return
	      */
	     public ArrayList<Object> recuperationMusique(String nomFichier) {
	    	 ArrayList<Object> colChanson = null;
	    	 File fichiertest = new File(nomFichier);
	         try {

	                 FileInputStream fichier = new FileInputStream(fichiertest);

	                 ObjectInputStream s = new ObjectInputStream(fichier);
	                 try{
	                	 colChanson = (ArrayList<Object>) s.readObject();
	                      
	                 }
	                 finally {
	 				// on ferme les flux
	 				try {
	 				
	 					s.close();
	 				} finally {
	 					
	 					fichier.close();
	 				}
	 			}

	                
	          } catch (IOException e) {
	                 e.printStackTrace();
	                 return colChanson;
	          }
	            catch(ClassNotFoundException e){
	                 e.printStackTrace();

	          }
			return colChanson;

	          }
	     /**
	      * Verifie qu'une collection existe dans le fichier mis en parametre s'il existe retourne true
	      * @param fichiertest
	      * @return
	      */
	     public Boolean collectionChansonExiste(String fichiertest) {
	    	  	Boolean rep = false;
	         try {

	                 FileInputStream fichier = new FileInputStream(fichiertest);
	                 
	                 ObjectInputStream s = new ObjectInputStream(fichier);
	                 try{
	                	 colChanson = (ArrayList<Object>) s.readObject();
	                      
	                 }
	                 finally {
	 				// on ferme les flux
	 				try {
	 					s.close();
	 				} finally {
	 					fichier.close();
	 				}
	 			}

	                
	          } catch (IOException e) {
	                 e.printStackTrace();
	                 System.out.println(e.getMessage());
	          }
	            catch(ClassNotFoundException e){
	                 e.printStackTrace();
	                 System.out.println(e.getMessage());
	          }
	         finally{
	         
	         if (colChanson != null)
	        	 rep = true;
	         
			return rep;
	         }
	          }

		public void insertFichierObject(Playlist playlist,
				String nomFichierPlaylist) {
			
			
			 
	    	  File fichiertest = new File(nomFichierPlaylist);
	    	 // System.out.println("chemin "+nomFichier);
	    	  // si c'est un fichier alors on le recupere sous forme de chanson
		    	 if(fichiertest.isFile()){
		    		 	
		    		 	
		    		 	//si des chansons sont deja presente dans le fichier alors on  les recuperes
		    		 	if(collectionChansonExiste(nomFichierPlaylist)){
		    		 		 colChanson = recuperationMusique(nomFichierPlaylist);
		    	   		 }
		    		 	else{
		    		 		 colChanson = new ArrayList<Object>();
		    		 	}
		    		 	colChanson.add(playlist);
		    		 	//insertion
		        try {
		            FileOutputStream fichier = new FileOutputStream(fichiertest);
		            ObjectOutputStream s = new ObjectOutputStream(fichier);
		            try {
		               
		                s.writeObject(colChanson);
		                s.flush();
		                s.close();
		                
		                }
		                    finally {
		                    	fichier.flush();
		                    	fichier.close();
		                            }
		       }
		         catch (IOException e) {
		            System.out.println("Probleme creation fichier "+ e.getMessage());
		           e.printStackTrace();
		        }
		     
		    	 }
			
			
		}
	     
	    }

	     

	    
	    
	
