package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {



  private String connexion;
  private String root;
  private String pass;
  private ResultSet rs;
  
  public Database(){
    this.connexion = ("jdbc:mysql://localhost:3306/moxmusic");
    this.root = "root";
    this.pass = "";
  }
  
  public Connection getConnection() throws Exception{
	  Connection c = null;
      String pilote = "com.mysql.jdbc.Driver";
      //String pilote = "org.gjt.mm.mysql.Driver";
      try {
		Class.forName(pilote);
	    c = DriverManager.getConnection(this.connexion, this.root, this.pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
	  return c;
  }
  
  public void setConn(String c){
    this.connexion = c;
  }
  
  public String getConn(){
    return this.connexion;
  }
  
  public void setRoot(String r){
    this.root = r;
  }
  
  public String getRoot(){
    return this.root;
  }
  
  public void setPass(String p){
    this.pass = p;
  }
  
  public String getPass(){
    return this.pass;
  }
  
  public ResultSet getRs(){
    return this.rs;
  }
  
  public int inscription(String pwd, String nom, String prenom, String mail)
  {
	  int nbAjout = 0;
	  try{
	  String requete = "INSERT INTO `moxmusic`.`utilisateur` (`id`, `nom`, `prenom`, `email`, `pwd`, `isAdmin`) VALUES (NULL, '"+nom+"', '"+prenom+"', '"+mail+"', '"+pwd+"', '0')";
	  Connection c = null;
	try {
		c = getConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      Statement s = c.createStatement();
      nbAjout = s.executeUpdate(requete);
	  }catch (SQLException e){
	      System.out.println(e.getMessage());
	  }
	  

	  return nbAjout;
  }
  
}