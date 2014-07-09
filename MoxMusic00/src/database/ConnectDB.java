package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	Statement MaCo;
    Connection Connect;

    public ConnectDB() {
    
       
 
           try{ //Connexion Ã  la base de donnÃ©es
              //Charge le driver
                Class.forName("org.postgresql.Driver");
              //Connexion Ã  la base de donnÃ©es
                Connection maConnexion = DriverManager.getConnection("jdbc:postgresql:shopmusic", "iisiramen", "alexiane91");
              //CrÃ©ation de l'objet permettant d'exÃ©cuter des requÃªtes
                Connect = maConnexion;
                Statement stat = maConnexion.createStatement();
                MaCo = stat;
          }

        //Afficher un message si il y a une erreur avec le driver
          catch (ClassNotFoundException erreur){
           System.out.print("Erreur du driver");
          }

       //Afficher un message si il y a une erreur SQL ou autre
          catch (SQLException erreur){
            System.out.print(erreur+" "+2);
          }
    }//FIN CONNEXION

    public Statement getStat(){
        return(MaCo);
    }

}
