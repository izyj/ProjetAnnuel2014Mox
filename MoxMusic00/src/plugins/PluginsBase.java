package plugins;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Interface de base pour les plugins de notre application.
 * @author Isiramen Jonathan 
 *
 * Cette interface n'est destin�e � �tre directement impl�ment� dans un plugins, 
 * elle sert � d�finir un comportement commun � toutes les interfaces de plugins. 
 *
 */
public interface PluginsBase {

	/**
	 * Obtient le libell� � afficher dans les menu ou autre pour le plugins
	 * @return Le libell� sous forme de String. Ce libell� doit �tre clair et compr�hensible facilement 
	 */
	public String getLibelle();
	
	/**
	 * Obtient la cat�gorie du plugins. Cette cat�gorie est celle dans laquelle le menu du plugins sera ajout� une fois charg�
	 * @return
	 */
	public int getCategorie();
	
	/**
	 * recupere la JFrame avec le panel
	 * @return
	 */
	public JLabel getLabel();
	
	/**
	 * Demarre l'application
	 */
	public void launch();
	
}
