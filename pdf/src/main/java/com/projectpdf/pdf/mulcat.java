package com.projectpdf.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class mulcat {

	public void multipleProductCatalog(JSONObject catalog, String outputFileName) throws DocumentException, MalformedURLException, IOException
	{
		    Document document = new Document();
		    PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(outputFileName));
			document.open();
			PdfContentByte canvas = writer.getDirectContent();

			//Front Page
		     Image img=Image.getInstance(new java.io.File( "." ).getCanonicalPath()+"\\frontpage.jpg");
		     img.setAbsolutePosition(0,0);
		     img.scaleToFit(605, 855);
		     document.add(img);
		     
		     JSONObject jsonObjectproduct;
		for (Object keyObject : catalog.keySet())
        { 
			document.newPage();
			
			//Vaxia logo
		     img=Image.getInstance(new java.io.File( "." ).getCanonicalPath()+"\\Vaxia_Logo.jpg");
		     img.setAbsolutePosition(475,800);
		     img.scaleToFit(100, 100);
		     document.add(img);
			
		     String key = (String)keyObject;	               
	         jsonObjectproduct = (JSONObject) catalog.get(key);
	         
	         //Add title
	         PdfPCell cell;
	         PdfPTable table1 = new PdfPTable(1);
	         table1.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase((jsonObjectproduct.get("Product Title").toString()).toUpperCase(),FontFactory.getFont("Algerian",15,new BaseColor(255,255,255))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(38,43,94));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setMinimumHeight(30);
	         table1.addCell(cell);
	         table1.writeSelectedRows(0, 1, 20, 800, canvas);
	         
	       //Add Description
	         float inc=table1.getTotalHeight()+2;
	         PdfPTable table2 = new PdfPTable(1);
	         table2.setTotalWidth(300);
	         cell = new PdfPCell(new Phrase(jsonObjectproduct.get("Description").toString(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(255,255,255));
	         cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table2.addCell(cell);
	         table2.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	         
	         //Add Image
	         img=Image.getInstance(new java.io.File( "." ).getCanonicalPath()+"\\"+jsonObjectproduct.get("Image Name").toString());
	         img.setAbsolutePosition(322,518);
	         img.scaleToFit(250,250);
	         document.add(img);
	         
	         
	         //Key Features -1
	         inc=inc + table2.getTotalHeight()+2;
	         PdfPTable table3 = new PdfPTable(1);
	         if((800-inc)>518)
	        	 table3.setTotalWidth(300);
	         else
	        	 table3.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase("KEY FEATURES",FontFactory.getFont("Algerian",12,new BaseColor(255,255,255))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(38,43,94));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setMinimumHeight(23);
	         table3.addCell(cell);
	         table3.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	         
	      //Key Features -2
	         inc=inc + table3.getTotalHeight()+2;
	         PdfPTable table4 = new PdfPTable(1);
	         if((800-inc)>518)
	        	 table4.setTotalWidth(300);
	         else
	        	 table4.setTotalWidth(555);
	         String kf=jsonObjectproduct.get("Key Features").toString();
	         int s=0,e=1;
	         for(int i = 0;i<kf.length()-5;i++) 
	         {
	        	 	if((kf.substring(i, i+5).toString()).equals("<br/>"))
	        	 	{
	        	 		cell = new PdfPCell(new Phrase(e+". "+ kf.substring(s,i).toString(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	        	 		cell.setBorderColor(new BaseColor(255,255,255));
	        	 		cell.setBackgroundColor(new BaseColor(255,255,255));
	        	 		cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	        	 		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        	 		table4.addCell(cell);
	        	 		s=i+5;
	        	 		e++;
	        	 	}
	         }
	         cell = new PdfPCell(new Phrase(e+". "+ kf.substring(s,kf.length()).toString(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255));
	    		cell.setBackgroundColor(new BaseColor(255,255,255));
	    		cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	    		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    		table4.addCell(cell);
	         
	         table4.writeSelectedRows(0, -1, 20, 800-inc, canvas);
	         
	
	         //Technical Information -1
	         inc=inc + table4.getTotalHeight()+2;
	         if(inc<282)
	        	 inc=282;
	         PdfPTable table5 = new PdfPTable(1);
	        	 table5.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase("TECHNICAL INFORMATION",FontFactory.getFont("Algerian",12,new BaseColor(255,255,255))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(38,43,94));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table5.addCell(cell);
	         table5.writeSelectedRows(0, 1, 20, 518, canvas);
	         
	       //Technical Information -2
	         inc=inc + table5.getTotalHeight()+2;
	         PdfPTable table6 = new PdfPTable(2);
	         int col=0;
	         for (Object keyObject2 : jsonObjectproduct.keySet())
	         {
	        	 table6 = new PdfPTable(2);
	        	 table6.setTotalWidth(555);
	        	 cell.setBorderColor(new BaseColor(255,255,255));
	             String key2 = (String)keyObject2;
	             if(key.equals("Price") || key2.equals("Product Title") || key2.equals("Description") || key2.equals("Short Description") || key2.equals("Key Features") || key2.equals("Standards & Certifications") || key2.equals("Image Name"))
	             {
	                 continue;
	             }
	            	 if((jsonObjectproduct.get(key2).toString()).trim()!="")
	                {
	            	 cell = new PdfPCell(new Phrase(key2,FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	            	 cell.setBorderColor(new BaseColor(255,255,255)); 
	            	 if(col==0)
	            		 cell.setBackgroundColor(new BaseColor(236,233,229));
	            	 else
	            		 cell.setBackgroundColor(new BaseColor(255,255,255));
	                 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	                 cell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
	            	 table6.addCell(cell);
	            	 cell = new PdfPCell(new Phrase((jsonObjectproduct.get(key2).toString()).trim(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	            	 cell.setBorderColor(new BaseColor(255,255,255)); 
	            	 if(col==0) {
	            		 cell.setBackgroundColor(new BaseColor(236,233,229));
	            		 col=1;
	            		 }
	            	 else {
	            		 cell.setBackgroundColor(new BaseColor(255,255,255));
	            		 col=0;
	            		 }
	                 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
	                 cell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
	            	 table6.addCell(cell); 
	            	 table6.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	            	 inc=inc+table6.getTotalHeight();  
	                }   
	             }
	         
	       //Ordering Information -1
	         inc=inc + 2;
	         PdfPTable table7 = new PdfPTable(1);
	         table7.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase("ORDERING INFORMATION",FontFactory.getFont("Algerian",12,new BaseColor(255,255,255))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(38,43,94));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell.setMinimumHeight(23);
	         table7.addCell(cell);
	         table7.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	         inc=inc+table7.getTotalHeight()+2;  
	
	         //Delivery Lead Time (In Days)
	         PdfPTable table8 = new PdfPTable(2);
	         table8.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase("Delivery Lead Time (In Days)",FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(236,233,229));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table8.addCell(cell);
	         cell = new PdfPCell(new Phrase(jsonObjectproduct.get("Delivery Lead Time (In Days)").toString(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(236,233,229));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table8.addCell(cell);
	         table8.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	         inc=inc+table8.getTotalHeight();          
	         
	         //Price
	         PdfPTable table9 = new PdfPTable(2);
	         table9.setTotalWidth(555);
	         cell = new PdfPCell(new Phrase("Price",FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(255,255,255));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table9.addCell(cell);
	         cell = new PdfPCell(new Phrase(jsonObjectproduct.get("Price").toString(),FontFactory.getFont("Algerian",12,new BaseColor(0,0,0))));
	         cell.setBorderColor(new BaseColor(255,255,255)); 
	         cell.setBackgroundColor(new BaseColor(255,255,255));
	         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         table9.addCell(cell);
	         table9.writeSelectedRows(0, 1, 20, 800-inc, canvas);
	         inc=inc+table9.getTotalHeight(); 
	         
	         
		     //footer
		     img=Image.getInstance(new java.io.File( "." ).getCanonicalPath()+"\\footer.jpg");
		     img.setAbsolutePosition(20,2);
		     img.scaleToFit(550, 100);
		     document.add(img);            
        }   
		document.close();
	}
}
