package dataAccessLayer;
/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import businessLayer.PDFGenerator;
import businessLayer.dbCommunication;
import model.Orders;
import validators.OrderValidators;

public class OrderAccessLayer {
	
	/** Metoda este folosita pentru a insera o coamnda in baza de date, metoda verifica
	 * daca comanda paote di onorata
	 * @param o  reprezinta coamnda care urmeaza sa fie inserat sau nu
	 * @return true daca s-a inserat comanda cu succes/ false daca nu s-a putut insera comanda
	 */	
	public boolean updateOrder(Orders o) throws FileNotFoundException, DocumentException {
		
		int cantitateRamasa=OrderValidators.eOK(o);
		String interogareModificareCantitate="", interogare;
		switch(cantitateRamasa) {
		case -1:
			return false;
		case -2:
			return false;
		default:
			interogareModificareCantitate="UPDATE `Product` SET `Quantity`="+cantitateRamasa+" WHERE `Name`= '"+o.getProdus()+"'";
			interogare="INSERT INTO `Orders`(`ID`,`Product`,`Quantity`,`Client`) VALUES ("+o.getId()+",'"+o.getProdus()+"',"+o.getCantitate()+",'"+o.getClient()+"')";
		}
		
		try
		{
			dbCommunication.updateDB(interogareModificareCantitate);	
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
		PDFGenerator.report(".\\report_" + (PDFGenerator.reportNumar++)+ ".pdf", 2);		
	}

}
