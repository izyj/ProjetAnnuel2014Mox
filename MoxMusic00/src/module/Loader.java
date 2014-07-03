package module;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Loader {
	private static List<URL> urls = new ArrayList<URL>();

	
	private static List<String> getModuleClasses(){ 
		List<String> classes = new ArrayList<String>(); 
 
		//On liste les fichiers de module  
		File[] files = new File("dossier").listFiles(new ModuleFilter()); 
 
		for(File f : files){ 
			JarFile jarFile = null; 
 
			try { 
				//On ouvre le fichier JAR 
				jarFile = new JarFile(f); 
				
				
				//On récupère le manifest 
				Manifest manifest = jarFile.getManifest(); 
 
				//On récupère la classe 
				String moduleClassName = manifest.getMainAttributes().getValue("Module-Class"); 
 
				classes.add(moduleClassName); 
 
				urls.add(f.toURI().toURL()); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} finally { 
				if(jarFile != null){ 
					try { 
						jarFile.close(); 
					} catch (IOException e) { 
						e.printStackTrace(); 
					} 
				} 
			} 
		} 
 
		return classes; 
	}
	
	private static class ModuleFilter implements FileFilter { 
		@Override 
		public boolean accept(File file) { 
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar"); 
		} 
	}
	
	private static ClassLoader classLoader; 
	 
	public static List<IModule> loadModules(){ 
		List<IModule> modules = new ArrayList<IModule>(); 
	 
		List<String> classes = getModuleClasses(); 
	 
		AccessController.doPrivileged(new PrivilegedAction<Object>(){ 
			@Override 
			public Object run() { 
				classLoader = new URLClassLoader( 
				urls.toArray(new URL[urls.size()]),Loader.class.getClassLoader()); 
				
				return null; 
			} 
		}); 
	 
		for(String c : classes){ 
			try { 
				Class<?> moduleClass = Class.forName(c, true, classLoader); 
	 
				if(IModule.class.isAssignableFrom(moduleClass)){ 
					Class<IModule> castedClass = (Class<IModule>)moduleClass; 
	 
					IModule module = castedClass.newInstance(); 
	 
					modules.add(module); 
				}  
			} catch (ClassNotFoundException e1) { 
				e1.printStackTrace(); 
			} catch (InstantiationException e) { 
				e.printStackTrace(); 
			} catch (IllegalAccessException e) { 
				e.printStackTrace(); 
			} 
		} 
	 
		return modules; 
	}

	
		
		
}

