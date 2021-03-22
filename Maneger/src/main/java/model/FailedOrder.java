package model;

/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import businessLayer.PDFGenerator;

public class FailedOrder {
	public static int ID;
	public int id;
	public String client;
	public String produs;
	public int cantitate;
	public String motiv;
	

	/***
	 * constructor pentru orders
	 * 
	 * @param c reprezinta numele unui client
	 * @param p reprezinta numele unui produs
	 * @param q reprezinta cantitate de produs comandata
	 * @param m reprezinta motivul pentru care comanda nu a putut fi onorata
	 */
	public FailedOrder(String c, String p, int q, String m) {
		this.id = ++ID;
		this.client = c;
		this.produs = p;
		this.cantitate = q;
		this.motiv = m;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProdus() {
		return produs;
	}

	public void setProdus(String produs) {
		this.produs = produs;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public String getMotiv() {
		return motiv;
	}

	public void setMotiv(String motiv) {
		this.motiv = motiv;
	}

	/**
	 * Metoda care apeleaza functia de scriere in pfd (functia de generarea a
	 * raporturilor)
	 * 
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void generateReport() throws FileNotFoundException, DocumentException {
		PDFGenerator.report(".\\report_" + (PDFGenerator.reportNumar++) + ".pdf", 3);
	}

}
