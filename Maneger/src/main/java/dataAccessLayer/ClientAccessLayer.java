package dataAccessLayer;
/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import businessLayer.PDFGenerator;
/***
 * @author VorniceanuIuliana
 */
import businessLayer.dbCommunication;
import model.Client;
import validators.ClientValidators;

public class ClientAccessLayer {
	/** Metoda este folosita pentru a insera/sterge un client in/din baza de date, metoda verifica
	 * daca campurile care alcatuiesc clientul sunt valide iar apoi incearca sa insereze/stearga in/din baza de date
	 * @param c  reprezinta clientul care urmeaza sa fie inserat/sters
	 * @param functie 0 pentru a sterge un client/ 1 pentru a adauga un client
	 * @return true daca s-a inserat/sters clientul cu succes/ false daca nu s-a putut insera/sterge clientul
	 */	
	public boolean updateClient(Client c, int functie) {
		
		if(ClientValidators.eOK(c)==false)
			return false;
		
		String interogare="";
		
		switch(functie) {
		case 1:
			interogare="INSERT INTO `Client`(`ID`, `Name`, `Address`) VALUES ("+c.getId()+", '" + c.getNume() + "', '" + c.getAdresa() + "') ";
			break;
		case 0:
			interogare="DELETE FROM `Client` WHERE `Name`='"+c.getNume()+"' and `Address`='"+c.getAdresa()+"' ";
			break;
		default: 
			return false;			
		}
		
		try
		{
			dbCommunication.updateDB(interogare);		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static void generateReport() throws FileNotFoundException, DocumentException {
		PDFGenerator.report(".\\report_" + (PDFGenerator.reportNumar++)+ ".pdf", 0);		
	}

}