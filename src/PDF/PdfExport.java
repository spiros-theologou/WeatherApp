/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF;

/**
 *
 * @author stheo
 */
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import DB.Connector;
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table;  
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.colors.ColorConstants;
import java.io.FileNotFoundException;
import java.util.List;


public class PdfExport {
    
    // creates CITY_COUNT table in PDF form
    public static void generatePdf() throws FileNotFoundException{
      
    
      List<Object[]> cityCounts = Connector.retreiveCounts();
      
      // Creating a PdfDocument object   
      final String dest = "CityCounts.pdf";   
      PdfWriter writer = new PdfWriter(dest);       
         
      // Creating a PdfDocument object      
      PdfDocument pdf = new PdfDocument(writer);                  
      
      // Creating a Document object       
      Document doc = new Document(pdf);                       
         
      // Creating a table       
      float [] pointColumnWidths = {275F, 275F};   
      Table table = new Table(pointColumnWidths);
      
      
      // Adding header cells to the table       
      table.addCell(new Cell().add(new Paragraph("City").setBold()).setBackgroundColor(ColorConstants.GRAY));  
      table.addCell(new Cell().add(new Paragraph("Search Count#").setBold()).setBackgroundColor(ColorConstants.GRAY));  
      
      
      // Adding the appropriate values to the table
      for (Object[] row : cityCounts) {
                String city = (String) row[0];
                int count = (int) row[1];
                table.addCell(new Cell().add(new Paragraph(city)));
                Paragraph countParagraph = new Paragraph().add(String.valueOf(count));
                table.addCell(new Cell().add(countParagraph));
            }
         
      // Adding Table to document        
      doc.add(table);                  
         
      // Closing the document       
      doc.close();
      System.out.println("Table created successfully..");   
      
    }
}
