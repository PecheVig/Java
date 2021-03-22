package main;
/**
 * @author VorniceanuIuliana
 *
 */
import java.util.ArrayList;
import dataAccessLayer.ClientAccessLayer;
import dataAccessLayer.OrderAccessLayer;
import dataAccessLayer.ProductAccessLayer;
import model.Client;
import model.Orders;
import model.Product;

public class Commands {
	public String comanda;	

	public void setComanda(String comanda) {
		this.comanda = comanda;
	}

	/**Metoda separa Stringul citit in main in cuvinte, apoi le interpreteaza si trimite mai departe instructiunea corespunzatoare comenzii 
	 * @return true daca totul a functionat/ false daca nu
	 * @throws Exception
	 */
	public Boolean executaComanda() throws Exception {

		ArrayList<String> comenzi = new ArrayList<String>();

		this.comanda = this.comanda.replace(':', ' ');
		this.comanda = this.comanda.replace(',', ' ');

		for (String cuv : this.comanda.split(" ")) {
			if (cuv.equals("") == false) {
				comenzi.add(cuv);
			}
		}

		
		if (comenzi.get(0).equals("Insert")) {
			if (comenzi.get(1).equals("client")) {						
					ClientAccessLayer cal = new ClientAccessLayer();
					Client clientNou= new Client(comenzi.get(2)+" "+comenzi.get(3), comenzi.get(4));					
					if(cal.updateClient(clientNou,1)==false)
						return false;
				
			}
			if (comenzi.get(1).equals("product")) {				
				ProductAccessLayer pal = new ProductAccessLayer();
				Product produsNou = new Product(Double.parseDouble(comenzi.get(4)),Integer.parseInt(comenzi.get(3)),comenzi.get(2));			
				if(pal.updateProdus(produsNou,1)==false)
					return false;				
			}
		}
		if (comenzi.get(0).equals("Delete")) {
			if (comenzi.get(1).equals("client")) {				
				ClientAccessLayer cal = new ClientAccessLayer();
				Client client = new Client();
				client.setNume(comenzi.get(2) + " " + comenzi.get(3));
				client.setAdresa(comenzi.get(4));
				if(cal.updateClient(client, 0)==false)
					return false;

			}
			if (comenzi.get(1).equals("product")) {
				ProductAccessLayer pal= new ProductAccessLayer();
				Product produs= new Product();
				produs.setNume(comenzi.get(2));
				
				pal.updateProdus(produs,0);
			}
		}
		if (comenzi.get(0).equals("Report")) {
			if (comenzi.get(1).equals("client")) {
				ClientAccessLayer.generateReport();
			}
			if (comenzi.get(1).equals("product")) {
				ProductAccessLayer.generateReport();
			}

			if (comenzi.get(1).equals("order")) {
				OrderAccessLayer.generateReport();
			}
		}

		if (comenzi.get(0).equals("Order")) {
			OrderAccessLayer oal = new OrderAccessLayer();
			Orders comandaNoua = new Orders(comenzi.get(1) + " " + comenzi.get(2), comenzi.get(3), Integer.parseInt(comenzi.get(4)));
			
			oal.updateOrder(comandaNoua);
		}

		return true;
	}
}
