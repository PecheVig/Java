package model;

/**
 * @author VorniceanuIuliana
 *
 */
public class Product {
	public static int ID;
	public int id;
	public double pret;
	public int cantitate;
	public String nume;

	public Product() {

	}

	/***
	 * constructor pentru produs
	 * 
	 * @param p    reprezinta pretul noului produs
	 * @param q    reprezinta cantitatea noului produs
	 * @param name reprezinta numele noului produs
	 */
	public Product(double p, int q, String name) {
		this.pret = p;
		this.cantitate = q;
		this.nume = name;
		Product.ID++;
		this.id = Product.ID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

}
