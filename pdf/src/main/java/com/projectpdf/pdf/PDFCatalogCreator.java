package com.projectpdf.pdf;


import com.itextpdf.text.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PDFCatalogCreator {

	public static void main(String[] args) throws DocumentException, IOException, ParseException {
		String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        JSONParser parser = new JSONParser();
        try {
        	
        	 Object single=parser.parse(new FileReader(current+"\\single.json"));
        	 JSONObject jsonObjectsingle = (JSONObject) single; 
        	 singcat single1=new singcat();
        	 single1.singleProductCatalog(jsonObjectsingle,current+"\\single.pdf");
        	 Object multiple=parser.parse(new FileReader(current+"\\multiple.json"));
        	 JSONObject jsonObjectmultiple = (JSONObject) multiple; 
        	 mulcat mt=new mulcat();
        	 mt.multipleProductCatalog(jsonObjectmultiple,current+"\\multiple.pdf");
        }
        catch (FileNotFoundException e) 
        {
	    
        }
		
		
	}

}
