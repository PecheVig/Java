package validators;

/**
 * @author VorniceanuIuliana
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionFactory;
import model.Product;

public class ProductValidators {

	/***
	 * Metoda cauta in baza de data produsul cu pretul curent
	 * 
	 * @param price reprezita pretul produsului care urmeaza sa fe inserat
	 * @param name  reprezinta numele produslui care urmeaza sa fie inserat
	 * @return cantitatea de produs inregistat in baza de date
	 *         nu exista in baza de date
	 */
	public static int exista(Product p) {

		int cantitate = -1;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String interogare = "Select Quantity as cant From `Product` Where `Name`='" + p.getNume() + "' and `Price`="
				+ p.getPret();
		try {
			findStatement = dbConnection.prepareStatement(interogare);
			rs = findStatement.executeQuery();

			if (rs.next()) {
				cantitate = rs.getInt("CANT");
			} else {
				cantitate = 0;
			}

			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		} catch (Exception e) {
			System.out.println(e.toString());
			return cantitate;

		}

		return cantitate;
	}
}
