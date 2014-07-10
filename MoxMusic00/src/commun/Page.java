package commun;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Page extends JFrame {

    protected ImageIcon hh;
    protected Toolkit tk = Toolkit.getDefaultToolkit();
    protected Dimension d = tk.getScreenSize(); // dimension de l'ecran
    protected Menu menu = new Menu(this);
    protected AfficheImage image ;
    
    public Page() {

try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
   System.out.println(e.getMessage());
   
}
		//Definition du fond d'ecran
//		image =new AfficheImage("lib\\images\\logo-cv-filter.png");
//		image.setOpaque(false);
//		this.setContentPane(image);
    	this.setJMenuBar(menu.getMenu());
        this.setResizable(false);
        this.setLocationRelativeTo(null);//centre la fenetre a l'ouverture
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// fermer grace a la croix
        this.setTitle("MoXMusic");
        this.setSize(800,600);
        this.setVisible(true);

    }

    

class AfficheImage extends JPanel {

    private Image fond;

    public AfficheImage(String s) {
        fond = getToolkit().getImage(s);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }

}



public ImageIcon getHh() {
	return hh;
}



public void setHh(ImageIcon hh) {
	this.hh = hh;
}



public Toolkit getTk() {
	return tk;
}



public void setTk(Toolkit tk) {
	this.tk = tk;
}



public Dimension getD() {
	return d;
}



public void setD(Dimension d) {
	this.d = d;
}



public Menu getMenu() {
	return menu;
}



public void setMenu(Menu menu) {
	this.menu = menu;
}





	}

