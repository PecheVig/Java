package model;

/**
 * @author VorniceanuIuliana
 *
 */
public class Orders {
	public static int ID = 0;
	public int id;
	public String client;
	public String produs;
	public int cantitate;

	/***
	 * constructor pentru orders
	 * 
	 * @param c reprezinta numele unui client
	 * @param p reprezinta numele unui produs
	 * @param q reprezinta cantitate de produs comandata
	 */
	public Orders(String c, String p, int q) {
		this.id = ++Orders.ID;
		this.client = c;
		this.produs = p;
		this.cantitate = q;
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

}
