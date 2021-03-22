package main;
/**
 * 
 * @author VorniceanuIuliana
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

import connection.ConnectionFactory;


public class MainClass {

	/**
	 * Metoda curata baza de date pentru a functiona fara probleme exemplul care trebuie rulat
	 */
	public static void curataBazaDeDate() {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		Statement curata = null;
		try {			
			curata = dbConnection.createStatement();
			curata.executeUpdate("TRUNCATE `Client` ");	
			curata.executeUpdate("TRUNCATE `Product` ");
			curata.executeUpdate("TRUNCATE `Orders` ");					
			curata.executeUpdate("TRUNCATE `FailedOrder` ");
			ConnectionFactory.close(curata);		
			ConnectionFactory.close(dbConnection);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	/**
	 * Metoda main care citeste comenzile din fisierul txt
	 * @param args reprezinta fisierul de unde se citesc comenzile, fisierul trebuie sa fie in acelasi fisier cu jar-ul
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		MainClass.curataBazaDeDate();
		String fisier=".\\"+args[0];
		BufferedReader reader= new BufferedReader(new FileReader(fisier));
		String comanda=null;
		Commands executa= new Commands();
		while((comanda=reader.readLine())!=null) {
			System.out.println(">"+comanda);
			executa.setComanda(comanda);
			executa.executaComanda();
		}
		
		reader.close();
		

	}

}
