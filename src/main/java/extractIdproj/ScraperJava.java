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

public class ScraperJava extends extraction {
	

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
	public static List<String> scraper(String search)
	{List<String> retour=new ArrayList<String>();
	
		try {

			efface(chemin+"json.txt");
			efface(chemin+"json2.txt");
			efface(chemin+"json0.txt");
			System.out.println("on commence");
			
			String chemin1="//recherche-jobs-maroc//";
			 String site="https://www.emploi.ma/";//url site 
			 Document doc=Jsoup.connect(site).get();//page d'accueil du site
		efface(chemin+"json.txt");
			    Compagnies compagnies=new Compagnies();
			    Elements ul =doc.getElementsByClass("content-search-job-frontpage");  
		        //button(la fct dès que tu cherches , elle scrappe et insère dans la base )
		        //variables Listes
			    
		        List<String> Links=new ArrayList<String>(); 
		        List<String> Lists=new ArrayList<String>(); 
		   	    List<String> newList=new ArrayList<String>(); 
		        List<List<String>> Compagnies1=new ArrayList<List<String>>(); 
		        List<String> Compag=new ArrayList<String>();
		       //mot de la zone de la recherche 
		        String delims = "(/w)([-.,\\n]())*";
		        String []sec = null;
		        
		        for(Element minUl:ul)
		        {   
		        	Elements links =minUl.getElementsByTag("a");
		        	//System.out.println(links);
		        	for(Element link:links)
		        	{
		        		String Content=link.text().toLowerCase();
		        		if(Content.contains(search))
		        		{
		        			String linkHref=link.attr("href");
		        			Links.add(linkHref);
		        			sec=Content.split("[,]");
		        		}
		        		
		        		
		        	}
		        	
		        }
		        
		        //System.out.println(Links);
		        int somme=0;
		        float numb;
		        float numb2;
		        numb2=ScraperJava.findNumbPages(site+chemin1+sec[0]+"?");
		       
		        	numb=ScraperJava.findNumbPages(site+Links.get(1));
		        	
		        	retour.add(site+Links.get(1));
		        	retour.add(ScraperJava.findNumbAnnonces(site+Links.get(1)));
	        	    String lienP=site+Links.get(1);
	        	    //System.out.println(lienP);
	        	    retour.add(lienP);
	           		 for(int j=0;j<=numb-1;j++) {
	           			 
	           			 ///recherche-jobs-maroc/informatique?
	           			 ///www.emploi.ma/recherche-jobs-maroc?f%5B0%5D=im_field_offre_secteur%3A133&page=1
	           			//https://www.emploi.ma/recherche-jobs-maroc/informatique?page=1
	           			
	           			Compagnies1.add(extractInfo(site+Links.get(1)+"&page="+j,Lists,search));
	           			
	           		 }
	           		 
	numb=ScraperJava.findNumbPages(site+Links.get(0));
		        	
		        	retour.add(site+Links.get(0));
		        	retour.add(ScraperJava.findNumbAnnonces(site+Links.get(0)));
	        	   lienP=site+Links.get(0);
	        	    //System.out.println(lienP);
	        	    retour.add(lienP);
	           		 for(int j=0;j<=numb-1;j++) {
	           			 
	           			 ///recherche-jobs-maroc/informatique?
	           			 ///www.emploi.ma/recherche-jobs-maroc?f%5B0%5D=im_field_offre_secteur%3A133&page=1
	           			//https://www.emploi.ma/recherche-jobs-maroc/informatique?page=1
	           			
	           			Compagnies1.add(extractInfo(site+Links.get(0)+"&page="+j,Lists,search));
	           			
	           		 }
		        
		        String lien="https://www.emploi.ma/recherche-jobs-maroc/informatique?";
              retour.add(lien);
	        	retour.add(ScraperJava.findNumbAnnonces(lien));
              
           		for(int j=0;j<=numb2-1;j++) {
           			 
           			 ///recherche-jobs-maroc/informatique?vcb
           			 ///www.emploi.ma/recherche-jobs-maroc?f%5B0%5D=im_field_offre_secteur%3A133&page=1
           			//https://www.emploi.ma/recherche-jobs-maroc/informatique?page=1

           			String secteur=sec[0];
           			String lien1=lien+"?page="+j;
           			Compagnies1.add(extractInfo(lien1,Lists,secteur));
           		 }
           		 

				 List<String> newLittleList=new ArrayList<String>(); 
	           		
	           		 for(int x=0;x<Compagnies1.size();x++)
	           		 {
	           			List<String> newLittleList2=removeDuplicates(Compagnies1.get(x));
	           			
	           			for(int y=0;y<newLittleList2.size();y++) {
	           				
	           				newLittleList.add(newLittleList2.get(y));
	           			}
	           			
	           			 
	           		 }
	           		 newList=removeDuplicates(newLittleList);
	           		 //System.out.println(newList);

	            
		        noRepeat(chemin+"json0.txt", newList);
		        Compag=extractInfoOfEachLine(chemin+"json0.txt");
		        // close the scanner object;  

	           		for(int c=0;c<Compag.size();c++)
	           		{

	           	    try {
	           		Document doc3=Jsoup.connect(site+Compag.get(c)).get();
       				Elements table=doc3.select("td#company-profile-city+td+td");
       				Elements Pays=doc3.select("td#company-profile-country+td+td");
       				Elements Site=doc3.select("td#company-profile-site+td+td");
       				Elements Secteur=doc3.select("td#company-profile-industries+td+td");
       				Elements Name=doc3.select("td#company-profile-name+td+td");
       			   String img=doc3.select("div.company-profile-logo>img").attr("src");
       				String VilleComp=table.text();
       				String pays=Pays.text();
       				String siteC=Site.text();
       				String secteur=Secteur.text().trim();
       				String name=Name.text().replace("'","''");
       			  //public Compagnie(String nom,String site, String ville, String pays, String secteur,String urlImage)
       				Compagnie society=new Compagnie(name, siteC, VilleComp, pays, secteur, img);
       			     String data =name+"#"+siteC+"#"+VilleComp+"#"+pays+"#"+secteur+"#"+img;
       			
	           		write(chemin+"json2.txt",data);
	           		compagnies.addCompagnies(society);
	           		}
	           		 catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	           		
	           	  
	           		}
	           		System.out.println("nbr de compagnies scrapées "+compagnies.getNbr());
	           		retour.add("nbr de compagnies scrapées "+compagnies.getNbr());
	           		int n=append.nbrline(chemin+"json.txt");
	           		retour.add("nbr d'annonces scrappées dans le fichier "+n);
	           		
		        System.out.println("finish");
		      /*  BD bdcon=new BD();
		        Connection con1 = bdcon.connexion();
		        int nb_Annonce=bdcon.insertion(con1,chemin+"json.txt");//fichier avec les données annonces
		        int nb_compagnie=bdcon.insertionCompagnie(con1,chemin+"json2.txt");//fichier avec les données compagnies
		          bdcon.insertionDataScrapping(con1,nb_compagnie, nb_Annonce);*/
		        }
		catch (IOException e){
			 e.printStackTrace();
		      System.out.println("err");
			
		}
		return retour;

	}

	public static void startscraping() {

		List<String> a=ScraperJava.scraper("informatique");
		BD bdcon = new BD();
		Connection con1 = bdcon.connexion();
        int nb_Annonce=bdcon.insertion(con1,chemin+"json.txt");//fichier avec les données annonces
        int nb_compagnie=bdcon.insertionCompagnie(con1,chemin+"json2.txt");//fichier avec les données compagnies
         bdcon.insertionDataScrapping(con1,nb_compagnie, nb_Annonce);
         

		removeDuplicates(a);
		System.out.println(a);

}
}