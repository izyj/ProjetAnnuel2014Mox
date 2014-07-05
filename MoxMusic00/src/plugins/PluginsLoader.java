package plugins;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;



/**
 * Classe g�rant le chargement et la validation des plugins
 * @author Lain� Vincent (dev01, http://vincentlaine.developpez.com/ )
 *
 */
public class PluginsLoader {

	private String[] files;
	private ArrayList classStringPlugins;
	
	
	/**
	 * Constructeur par d�faut
	 *
	 */
	public PluginsLoader(){
		
		this.classStringPlugins = new ArrayList();
	}
	
	/**
	 * Constucteur initialisant le tableau de fichier � charger.
	 * @param files Tableau de String contenant la liste des fichiers � charger.
	 */
	public PluginsLoader(String[] files){
		this();
		this.files = files;
		try {
			this.initializeLoader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage()+"1");
			
		}
	}
	
	/**
	 * D�fini l'ensemble des fichiers � charger
	 * @param files
	 */
	public void setFiles(String[] files ){
		this.files = files;
	}
	

	
	private void initializeLoader() {
		//On v�rifie que la liste des plugins � charger � �t� initialis�
//		if(this.files == null || this.files.length == 0 ){
//			throw new Exception("Pas de fichier sp�cifi�");
//		}

		//Pour eviter le double chargement des plugins
		if(this.classStringPlugins.size() != 0 ){
			return ;
		}
		
		File[] f = new File[this.files.length];
//		Pour charger le .jar en memoire
		URLClassLoader loader;
		//Pour la comparaison de chaines
		String tmp = "";
		//Pour le contenu de l'archive jar
		Enumeration enumeration = null;
		//Pour d�termin� quels sont les interfaces impl�ment�es
		Class tmpClass = null;
		
		for(int index = 0 ; index < f.length ; index ++ ){
			
			f[index] = new File(this.files[index]);
			
			if( !f[index].exists() ) {
				
				System.out.println("pas de fichier"+f[index].exists());
				break;
			}
			
			URI u1 =  f[index].toURI();
			URL u = null;
			try {
				u = u1.toURL();
			} catch (MalformedURLException e) {
				System.out.println("causeURI :  "+ e.getMessage());
				e.printStackTrace();
			
			}
			//On cr�er un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
			loader = new URLClassLoader(new URL[] {u}); 
			
			//On charge le jar en m�moire
			JarFile jar;
			try {
				jar = new JarFile(f[index].getAbsolutePath());
				//On r�cup�re le contenu du jar
				enumeration = jar.entries();
			} catch (IOException e) {
				System.out.println("causejar");
				e.printStackTrace();
			}
			
			
			
			while(enumeration.hasMoreElements()){
				
				tmp = enumeration.nextElement().toString();

				//On v�rifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
				if(tmp.length() > 6 && tmp.substring(tmp.length()-6).compareTo(".class") == 0) {
					
					tmp = tmp.substring(0,tmp.length()-6);
					tmp = tmp.replaceAll("/",".");
					
					try {
						tmpClass = Class.forName(tmp ,true,loader);
					} catch (ClassNotFoundException e) {
						System.out.println("causetmpClass");
						e.printStackTrace();
					}
					
					for(int i = 0 ; i < tmpClass.getInterfaces().length; i ++ ){
						
						//Une classe ne doit pas appartenir � deux cat�gories de plugins diff�rents. 
						//Si tel est le cas on ne la place que dans la cat�gorie de la premi�re interface correct
						// trouv�e
						if(tmpClass.getInterfaces()[i].getName().toString().equals("plugins.PluginsBase") ) {
							this.classStringPlugins.add(tmpClass);
						}
					}		
				}	
			}
			PluginsBase[] tmpPlugins = new PluginsBase[this.classStringPlugins.size()];
			for(int i = 0 ; i < tmpPlugins.length; i ++ ){
				
				//On cr�er une nouvelle instance de l'objet contenu dans la liste gr�ce � newInstance() 
				//et on le cast en StringPlugins. Vu que la classe impl�mente StringPlugins, le cast est toujours correct
				try {
					tmpPlugins[i] = (PluginsBase)((Class)this.classStringPlugins.get(i)).newInstance() ;
				} catch (InstantiationException | IllegalAccessException e) {
					System.out.println("cause tmpplug : "+e.getMessage());
					e.printStackTrace();
				}
				System.out.println("passer");
				//tmpPlugins[i].launch();
				
			}
		
		}
		
	}
	
	
	
}
