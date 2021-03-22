package model;

/**
 * @author VorniceanuIuliana
 *
 */
public class Client {
	public static int ID = 0;
	public int id;
	public String nume;
	public String adresa;

	public Client() {
	}

	/***
	 * constructor pentru client
	 * 
	 * @param name    reprezinta numele clientului
	 * @param address reprezinta adresa clientului
	 */
	public Client(String name, String address) {

		this.nume = name;
		this.adresa = address;
		Client.ID++;
		this.id = ID;

	}

	public String getNume() {
		return nume;
	}

	public String getAdresa() {
		return adresa;
	}

	public int getId() {
		return id;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
