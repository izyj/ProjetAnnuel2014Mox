import interfaces.Acceuil;


import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;






public class Main {


     

	public static void main(String[] args) {

          
			Runnable unThread = new Runnable(){
        		//traitement
        	  
				@Override
				public void run() {
				
//					new AudioControledPlayerGUI();

					try {
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					    // If Nimbus is not available, you can set the GUI to another look and feel.
					}
					new Acceuil();
					ToolTipManager.sharedInstance().setInitialDelay(0);
					
				}
        	};
        	SwingUtilities.invokeLater(unThread);
        	


    }
	

}