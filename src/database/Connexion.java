package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;

public class Connexion {
	
	 private Connection connection = null;

	    public Connection getConnecion() {
	        try {
	            String url = "jdbc:postgresql://localhost:5432/dvdrental";
	            String username = "postgres";
	            String pwd = "postgres";
	            connection = DriverManager.getConnection(url, username, pwd);
	            System.out.println("Connexion établie avec succès");
	        } catch (SQLException ex) {
	            System.out.println("Erreur de connexion à la base de données");
	            ex.printStackTrace();
	        }

	        return connection;
	    }

	public static void main(String[] args) {
		 System.out.println(new Connexion().callBuilInFunction("essaie de transformer cette phrase"));

	}
	
	 private String callBuilInFunction(String chaine) {
	        String resultat = chaine;
	        final String SQL = "{ ? = call initcap( ? ) }";

	        try {
	            Connection connection = new Connexion().getConnecion();
	            CallableStatement statement = connection.prepareCall(SQL);
	            statement.registerOutParameter(1, Types.VARCHAR);
	            statement.setString(2, chaine);
	            statement.execute();

	            resultat = statement.getString(1);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return resultat;
	    }

}
