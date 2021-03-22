package validators;

/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;
/**
 * @author VorniceanuIuliana
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.DocumentException;

import businessLayer.dbCommunication;
import connection.ConnectionFactory;
import model.FailedOrder;
import model.Orders;

public class OrderValidators {

	/***
	 * Metoda verifica daca o comanda poate fi procesata, procesarea se face daca
	 * cantitatea de produs din depozit este mai mare decat cantitatea de produs
	 * solicitata de client
	 * 
	 * @param o reprezinta comanda clientului
	 * @return cantitatea de produs ramasa in depozit daca comanda poate fi
	 *         procesata/ -1 daca comanda nu este valida sau -2 daca comanda poate
	 *         fi procesata dar nu exista suficiente materiale in depozit
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	public static int eOK(Orders o) throws FileNotFoundException, DocumentException {
		int cantitateDepozit = -1;
		int cantitateFinala = -1;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String interogare = "Select Quantity as cant From `Product` Where `Name`='" + o.getProdus() + "' ";
		try {
			findStatement = dbConnection.prepareStatement(interogare);
			rs = findStatement.executeQuery();

			if (rs.next()) {
				cantitateDepozit = rs.getInt("CANT");
			} else {
				cantitateDepozit = 0;
			}
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);

		} catch (Exception e) {
			System.out.println(e.toString());
			return cantitateDepozit;
		}

		if (cantitateDepozit != -1) {
			if (cantitateDepozit >= o.getCantitate()) {
				cantitateFinala = cantitateDepozit - o.getCantitate();
				return cantitateFinala;
			} else {

				FailedOrder f = new FailedOrder(o.getClient(), o.getProdus(), o.getCantitate(),
						"Cantitate de produs indisponibila in depozit.");
				interogare = "INSERT INTO `FailedOrder`(`ID`, `Product`, `Quantity`, `Client`, `Reason`) VALUES ("
						+ f.getId() + ", '" + f.getProdus() + "', '" + f.getCantitate() + "','" + f.getClient() + "','"
						+ f.getMotiv() + "') ";

				try {
					dbCommunication.updateDB(interogare);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return -2;
				}
				FailedOrder.generateReport();

				return -2;
			}
		}
		return -1;
	}
}
