package extractIdproj;
import java.util.regex.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.archiver.zip.AddedDirs;
import org.jsoup.select.Elements;

import controller.BD;
import dao.Compagnie;
import dao.Compagnies;
import weka.core.PropertyPath.Path;

//import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScrapReg extends RegExtract {

	public static char  separator=ScraperJava.separator;
	public static String cheminLocal=ScraperJava.cheminLocal;
	public static String chemin=cheminLocal+"data_extraite"+separator;
	

	
	 public static void scraper(String secteur)
	{
	 String[] sec=null;
		try {
			System.out.println("on commence");
			//https://www.emploi.ma/recherche-jobs-maroc/informatique?
			
			 String site="https://www.emploi.ma/recherche-jobs-maroc/"+secteur+"?";//url site 
			 Document doc=Jsoup.connect(site).get();//
	
		        int somme=0;
		        float numb;
		        float numb2;
		        numb2=ScraperJava.findNumbPages(site);
              
           		for(int j=0;j<=numb2-1;j++) {

           			String lien1=site+"?page="+j;
           			extractInfo(lien1,secteur);
           		 }
           		 
           System.out.println("traitement done");
			

		}
	           		 catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}


	}

	 public static void startscrapingreg() {
		BD bdcon = new BD();
		Connection con1 = bdcon.connexion();
		 bdcon.deleteReg(con1);
		 bdcon.deleteRegression(con1);
		

  String[] tab= {"informatique","banque","services","marketing","industrie","btp","ingénierie","hôtellerie","vente"};
    for( String el:tab) {
    	ScrapReg.scraper(el);
    	 bdcon.insertionRegression(con1);
    }
 
		
		

}
}