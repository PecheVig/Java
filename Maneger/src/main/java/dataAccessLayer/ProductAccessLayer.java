package dataAccessLayer;
/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import businessLayer.PDFGenerator;
/**
 * @author VorniceanuIuliana
 */
import businessLayer.dbCommunication;
import model.Product;
import validators.ProductValidators;


public class ProductAccessLayer {

	/**
	 * Metoda care executa inserarea/stergerea in/din baza de date. pentru inserare se verifica
	 * daca inregistrarea mai exista in baza de date, daca exista se face update pe inetrogarea respectiva
	 * @param p reprezinta produsul care urmeaza sa fie inserat/sters
	 * @param functie poate fi 1 sau 0, altfel se primeste eroare, 1 pentru inserare, 0 pentru stergere in/din baza de date
	 * @return true daca s-a putut insera/sterge din baza de date/ false daca nu s-a putut insera/sterge
	 */
	public Boolean updateProdus(Product p, int functie) {	
		String interogare="";
		if(functie ==1) {
		int cantitate=ProductValidators.exista(p);
		switch(cantitate) {
		case 0:
			interogare="INSERT INTO `Product`(`ID`, `Name`, `Price`,`Quantity`) VALUES ("+p.getId()+",'"+p.getNume()+"',"+p.getPret()+","+p.getCantitate()+")";
			break;
		case -11:
			return false;
		default:			
			interogare="UPDATE `Product` SET `Quantity`="+(cantitate+p.getCantitate())+" WHERE `Name`= '"+p.getNume()+"' and `Price`="+p.getPret();
		}
		}
		else {
			if(functie==0)
			{
				interogare="DELETE FROM `Product` WHERE `Name`='"+p.getNume()+"' ";
			}
		}
		
		try
		{
			dbCommunication.updateDB(interogare);		
		}
		catch(Exception e)
		{
			System.out.println("Eroarea asta o tot cauti: "+e.getMessage());
			return false;
		}		
		return true;	
	}
	
	
	/**
	 * Metoda folosita pentru a apela clasa care genereaza rapoartele
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void generateReport() throws FileNotFoundException, DocumentException {
		PDFGenerator.report(".\\report_" + (PDFGenerator.reportNumar++)+ ".pdf", 1);		
	}

	
}
