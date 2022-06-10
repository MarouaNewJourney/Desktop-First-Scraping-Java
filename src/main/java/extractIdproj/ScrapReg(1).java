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

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScrapReg {

	public static char  separator=ScraperJava.separator;
	public static String cheminLocal=ScraperJava.cheminLocal;
	public static String chemin=cheminLocal+"data_extraite"+separator;
	public static float findNumbPages(String lien) throws IOException {
		
		int somme=0;
	
   	 Document doc1=Jsoup.connect(lien).get();
 	// Document doc2=Jsoup.connect("https://www.emploi.ma/"+chemin1+sec[0]+"?").get();
 	 Elements div1=doc1.getElementsByClass("current-search-item current-search-item-emasearch-text current-search-item-jobsearch-text-results");
		    String textDiv1=div1.text();
		   //System.out.println(div1);
		 String delims = " ";
		 String [] expression=textDiv1.split(delims);
		 //System.out.println(expression[0]);
		 int u=Integer.valueOf(expression[0]);
		 somme=somme+Integer.valueOf(expression[0]);
		 float numb=(float)u/25;
		 String numbString=Float.toString(numb);
		 
		 delims = "[.]";
		 String [] virgule=numbString.split(delims);
		 //System.out.println(numbString);
		 if(!virgule[1].equals("0"))
		 {
			 numb=(int)numb+1;
		 }
		 

		 String textContent=String.valueOf(somme)+" annonces trouvées";
		System.out.println(textContent);
		return numb;
	}
	public static String findNumbAnnonces(String lien) throws IOException {
		
		int somme=0;
		
		
   	 Document doc1=Jsoup.connect(lien).get();
 	// Document doc2=Jsoup.connect("https://www.emploi.ma/"+chemin1+sec[0]+"?").get();
 	 Elements div1=doc1.getElementsByClass("current-search-item current-search-item-emasearch-text current-search-item-jobsearch-text-results");
		    String textDiv1=div1.text();
		   //System.out.println(div1);
		 String delims = " ";
		 String [] expression=textDiv1.split(delims);
		 //System.out.println(expression[0]);
		 int u=Integer.valueOf(expression[0]);
		 somme=somme+Integer.valueOf(expression[0]);
		 float numb=(float)u/25;
		 String numbString=Float.toString(numb);
		 
		 delims = "[.]";
		 String [] virgule=numbString.split(delims);
		 //System.out.println(numbString);
		

		 String textContent=String.valueOf(somme)+" annonces trouvées";
		
		return String.valueOf(somme);
	}
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
           			RegExtract.extractInfo(lien1,secteur);
           		 }
           		 
System.out.println("traitement done");
			

		}
	           		 catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BD bdcon = new BD();
		Connection con1 = bdcon.connexion();
		 bdcon.deleteReg(con1);

  String[] tab= {"informatique","banque","services","marketing","industrie","btp","ingénierie","hôtellerie","vente"};
    for( String el:tab) {
    	ScrapReg.scraper(el);
    }
 
		
		

}
}