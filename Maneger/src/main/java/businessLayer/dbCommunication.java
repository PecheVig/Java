package businessLayer;
/***
 * @author VorniceanuIuliana
 */
import java.sql.Connection;
import java.sql.Statement;

import connection.ConnectionFactory;

public class dbCommunication {

	/**
	 * Metoda primeste o interogare de insert/delete si o executa
	 * @param query reprezinta interogarea generata in AccessLayer, care 
	 * poate fi de tip insert/delete
	 */
	public static void updateDB(String query) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement interogare = null;
		try {
			interogare = dbConnection.createStatement();			
			interogare.executeUpdate(query);
			ConnectionFactory.close(interogare);
			ConnectionFactory.close(dbConnection);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
}
