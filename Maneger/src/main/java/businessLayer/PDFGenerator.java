package businessLayer;
/**
 * @author VorniceanuIuliana
 *
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import connection.ConnectionFactory;

public class PDFGenerator {
	public static int reportNumar=0;
	
	/**
	 * Metoda genereaza fisiere PDF cu date din tabelele bazei de date
	 * @param path reprezinta calea catre noul fisier
	 * @param tip reprezinta tabelul asupra caruia se face raportul
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void report(String path, int tip) throws FileNotFoundException, DocumentException {
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(path));
		doc.open();
		
		Paragraph intro;
		PdfPTable tabel;
		int numarColoane=0;
		ArrayList<PdfPCell> coloane = new ArrayList<PdfPCell>();
		String interogareString="";
		
		switch(tip) {
		case 0:
			numarColoane=3;
			intro= new Paragraph("REPORT CLIENTI:\n\n");
			coloane.add(new PdfPCell(new Paragraph("ID")));
			coloane.add(new PdfPCell(new Paragraph("Name")));
			coloane.add(new PdfPCell(new Paragraph("Address")));
			interogareString="Select * From Client";
			break;
		case 1:
			numarColoane=4;
			intro= new Paragraph("REPORT PRODUSE:\n\n");
			coloane.add(new PdfPCell(new Paragraph("ID")));
			coloane.add(new PdfPCell(new Paragraph("Price")));
			coloane.add(new PdfPCell(new Paragraph("Name")));
			coloane.add(new PdfPCell(new Paragraph("Quantity")));
			interogareString="Select * From Product";
			break;
		case 2:
			numarColoane=4;
			intro= new Paragraph("REPORT COMENZI:\n\n");
			coloane.add(new PdfPCell(new Paragraph("ID")));
			coloane.add(new PdfPCell(new Paragraph("Product")));
			coloane.add(new PdfPCell(new Paragraph("Quantity")));
			coloane.add(new PdfPCell(new Paragraph("Client")));
			interogareString="Select * From Orders";
			break;
		case 3:
			numarColoane=5;
			intro= new Paragraph("REPORT COMANDA ESUATA:\n\n");
			coloane.add(new PdfPCell(new Paragraph("ID")));
			coloane.add(new PdfPCell(new Paragraph("Product")));
			coloane.add(new PdfPCell(new Paragraph("Quantity")));
			coloane.add(new PdfPCell(new Paragraph("Client")));
			coloane.add(new PdfPCell(new Paragraph("Reason")));
			interogareString="Select * From FailedOrder";
			break;
		default:
			return;
			
		}
		
		tabel=new PdfPTable(numarColoane);
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement interogare = null;
		ResultSet result = null;

		try {
			interogare = dbConnection.prepareStatement(interogareString);
			result = interogare.executeQuery();

			while (result.next()) {
				for(int i=0;i<numarColoane;i++) {
					String col=coloane.get(i).getPhrase().toString();
					String col2="";
					for(int j=1;j<col.length()-1;j++)
						col2+=col.charAt(j);					
					coloane.add(new PdfPCell(new Paragraph(result.getString(col2))));
				}				
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for(int i=0;i<coloane.size();i++) {
			tabel.addCell(coloane.get(i));
		}

		doc.add(intro);		
		doc.add(tabel);

		doc.close();


	}
}
