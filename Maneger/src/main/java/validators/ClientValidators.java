package validators;

/**
 * @author VorniceanuIuliana
 *
 */
import model.Client;

public class ClientValidators {

	/***
	 * metoda care verifica daca un client este valid sau nu
	 * 
	 * @param c reprezinta un client care urmeaza sa treaca orin verificarea de
	 *          validitate
	 * @return true daca datele despre client sunt valide/ false daca datele nu sunt
	 *         valide
	 */
	public static Boolean eOK(Client c) {
		if (c.getNume().length() >= 100)
			return false;
		if (c.getAdresa().length() >= 100)
			return false;
		return true;
	}

}
