package extractIdproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ML.DataWriter;
import dao.Annonce;


public class extraction extends append{
	public static char  separator=File.separatorChar;
	public static String cheminLocal=".."+separator+"extractIdproj"+separator+"src"+separator+"main"+separator+"java"+separator;
	public static String chemin=cheminLocal+"data_extraite"+separator;
	public static String[] dev= {"developer","développeur","fullstack","frontend","backend","Développement","ingénieur logiciel"};
	public static String[] sec= {"security","sécurité","Cybersécurité"};
	public static String[] cloud= {"cloud","cloud computing"};
	public static String[] sys= {"Systèmes et Réseaux","Système d'information et réseaux","Système dInformation et Réseaux"};
	public static String[] design= {"graphic design","graphic designer","design"};
	public static String[] domaine= {"Production","data","VMWare","BIG DATA","SAP","gestion"," Business Analyst","Devops","it","Marketing Digital","Business Development"};
	public static String[] Keys={"jquery","css","git","php","laravel","symfony","maven","docker","jenkins","java","spring boot","html","css","mariadb","mongodb","mysql","ux","ui","angular","react","java","rest","bootstrap","agile","swift","javascript","nodejs","sql","c/c++","c++","poo","oop","ruby","c#","sap","node.js","express","nestjs"};
	public static String[] j2ee= {"j2ee","java ee","jee","java entreprise edition"};
	public static String[] node= {"node","nodejs","node-js","node.js"};
	public static String[] Sboot= {"springboot","spring boot","spring-boot","java spring boot"};
	public static void SearchDom(String [] ab,String nom,String profil,Annonce annonce)
	{
	for(int u=0;u<ab.length;u++)
	{
		if(profil.toLowerCase().contains(ab[u])){
			annonce.setDomaine(nom);
		}
	}
	}
	public static boolean Searchlang(String[]ab,String nom) {
		boolean a=false;
		for(int q=0;q<ab.length;q++) {
			String lang=ab[q];
			if(nom.equals(lang)) {
				a=true;
			}
		}
		return a;
	}
	public static String SearchLang2(String nom,String[] ab) {
		String a="";
		for(int q=0;q<ab.length;q++) {
			String lang=ab[q];
			if(lang.equals(nom)) {
				a=nom;
			}
		}
		return a;
		
	}
	public static List<String>  extractInfo(String lien,List<String> list,String sect) {
		
		List<String> Compagnies=new ArrayList<String>(); 

		     
			Document doc2;
			Document doc3;
			try {
				
				/*.out.println("File.separator = "+File.separator);
		        .out.println("File.separatorChar = "+File.separatorChar);
		        .out.println("File.pathSeparator = "+File.pathSeparator);
		        .out.println("File.pathSeparatorChar = "+File.pathSeparatorChar);*/
				Annonce annonce=new Annonce();
				doc2 = Jsoup.connect(lien).get();
                                  String secteur=sect;
				String delimiters="[|]";
								String [] Sec =secteur.split(delimiters);
				
				secteur=Sec[0];
	   			
	   			
	   			Elements classJob2=doc2.getElementsByClass("search-results jobsearch-results");
                 int contJob=doc2.getElementsByClass("job-description-wrapper").size();
                 
	   			for(Element classJob3:classJob2)
	       		 {

	   			String urlAnnonce=classJob3.getElementsByClass("job-description-wrapper").attr("data-href");
	   			String delimiter="[/]";
	   			String [] urlComp= urlAnnonce.split(delimiter);
	   			String ident=urlComp[4];
	   			delimiter="-";
	   			
	   			String []tab=urlComp[4].split(delimiter);
	   			ident=tab[tab.length-1];
	   			//.out.println(ident);
	   			doc3 = Jsoup.connect(urlAnnonce).get();
	   			String content=doc3.text();
	   			String typeContrat=doc3.select("div.field-name-field-offre-contrat-type ").text();
	   			String  NiveauExperience=doc3.select("div.field-name-field-offre-niveau-experience").text();
	   			String NiveauEtude=doc3.select("div.field-name-field-offre-niveau-etude").text();
	   			//.out.println(typeContrat+" "+NiveauEtude);
	   			Elements jbDetails=doc3.select("div#job-ad-details-"+ident+">div>div.clearfix>div");
	   			
	   			Element jbDetail=jbDetails.get(2);
	   			//.out.println(doc3.select("table.job-ad-criteria"));
	   			String Region=doc3.select("div.field-name-field-offre-region>div.field-items>div.field-item").text();
	   		
	   		String ville=doc3.select("table.job-ad-criteria").select("tr").get(4).select("td").get(1).text();
	   		String nbr_posts=doc3.select("table.job-ad-criteria").select("tr").get(doc3.select("table.job-ad-criteria").select("tr").size()-1).select("td").get(1).text();
	   		//.out.println("nbr_postes :"+nbr_posts);
	   			//Competences (add after -structure incomprise)
	   		   String textComp=jbDetail.toString().toLowerCase();

	   		   
	   		
	   		   for(int el=0;el<Keys.length;el++)
	   		   {
	   			   if(textComp.contains(Keys[el]))
	   			   {
	   				   annonce.addTechniques(Keys[el]);
	   			   }
	   		   }
	   		for(int el=0;el<j2ee.length;el++)
	   		   {
	   			   if(textComp.contains(j2ee[el]))
	   			   {
	   				   annonce.addTechniques("j2ee");
	   			   }
	   		   }
	   		for(int el=0;el<node.length;el++)
	   		   {
	   			   if(textComp.contains(node[el]))
	   			   {
	   				   annonce.addTechniques("node");
	   			   }
	   		   }
	   		for(int el=0;el<Sboot.length;el++)
	   		   {
	   			   if(textComp.contains(Sboot[el]))
	   			   {
	   				   annonce.addTechniques("springboot");
	   			   }
	   		   }
	       		Elements links2=classJob3.select("h5").select("a");
	       		Elements links4=classJob3.select("div.hidden-xs > a");	
	       		String idCompagnie;
	       		Elements links3=classJob3.select("a").select("img");
	       		//.out.println("links3"+links3);
	       		Elements p2=classJob3.select("p.job-recruiter");
	       		Elements description2=classJob3.select("div.search-description");
	       		
	       	
	       		
	       	//.out.println("links4"+links4);

	       		for(int y=0;y<contJob;y++)
	       		{
	       		
	       			//.out.println("link1 "+links1.get(y));
	       			if(links3.size()-1>=y)
	       			{
	       				String image=links3.get(y).attr("src");
	       				//.out.println("image"+image);
	       			}
	       			else {
	       				String image="";
	       				//.out.println("image"+image);
	       			}
	       			if(links4.size()-1>=y)
	       			{
	       				 idCompagnie=links4.get(y).attr("href");
	       				

	       			}
	       			else {
	       				 idCompagnie="";
	       				
	       			}
	       			
	       			String profil=links2.get(y).text();
	       			SearchDom(dev,"developpement",profil,annonce);
	       			SearchDom(sec,"security",profil,annonce);
	       			SearchDom(cloud,"Cloud Computing",profil,annonce);
	       			SearchDom(sys,"systeme et Reseau",profil,annonce);
	       			SearchDom(design,"Design",profil,annonce);
	       			for(int u=0;u<domaine.length;u++)
	       			{
	       				if(profil.toLowerCase().contains(domaine[u].toLowerCase())){
	       					annonce.setDomaine(domaine[u].toLowerCase());
	       				}
	       			}
	       			
	       			List<String> ech=new ArrayList<String>();
	       			
	       			/*for(int x=0;x<Keys.length;x++)
	       			{
	       				if(txtContent.text().toLowerCase().contains(Keys[x])){
	       					annonce.addTechniques(Keys[x]);;
	       				}
	       			}*/
	       			for(int x=0;x<Keys.length;x++)
	       			{
	       				if(profil.toLowerCase().contains(Keys[x])){
	       					annonce.addTechniques(Keys[x]);;
	       				}
	       			}
	       			for(int x=0;x<j2ee.length;x++)
	       			{
	       				if(profil.toLowerCase().contains(j2ee[x])){
	       					annonce.addTechniques("j2ee");;
	       				}
	       			}
	       			for(int x=0;x<node.length;x++)
	       			{
	       				if(profil.toLowerCase().contains(node[x])){
	       					annonce.addTechniques("node");;
	       				}
	       			}
	       			for(int x=0;x<Sboot.length;x++)
	       			{
	       				if(profil.toLowerCase().contains(Sboot[x])){
	       					annonce.addTechniques("springboot");;
	       				}
	       			}
	       			//.out.println(annonce.getTechniques());
	       			ech=removeDuplicates(annonce.getTechniques());
	       			//.out.println(annonce.getTechniques());
	       			
	       			
	       			
	       			String hrefp=links2.get(y).attr("href");
	       			String société=p2.get(y).select("b").select("a").text();
	       			//String Missions=description2.get(y).text();
	       			
	       			String Ptext=p2.get(y).text();
	       	
	       			
	       		    //.out.println("compagnie "+compagnie);
	       		 //.out.println(Ptext);
	       		String delims1="[/|-]";
	       		 String[] date=Ptext.split(delims1);
                 Compagnies.add(idCompagnie);
	     
	            annonce.setDate(date[0]);//date
	        	annonce.setCdi(typeContrat);//typecontrat
	            annonce.setNiveau_Experience(NiveauExperience);
	        	annonce.setNomCompagnie(date[1]);//nom compagnie
	        	annonce.setNiveauEtude(NiveauEtude);
	        	annonce.setSecteur(secteur);
	        	annonce.setVille(ville);
	        	annonce.setProfil(profil.split(delims1)[0]);
	        	annonce.setRegion(Region);//region
                annonce.setId();
                annonce.setTechniques(ech);
                //.out.println(ech);
                int [] langages=new int[34];
                 List<String> listTry=annonce.getTechniques();
                 for(int q=0;q<listTry.size();q++)
                 {
                 if(extraction.Searchlang(j2ee,listTry.get(q)))
                 		{
                 	        langages[0]=1;
                 		}
                 if(extraction.Searchlang(node,listTry.get(q)))
         		{
         	        langages[33]=1;
         		}
                 if(extraction.Searchlang(Sboot,listTry.get(q)))
         		{
         	        langages[7]=1;
         		}
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("jquery")) {
                 	langages[1]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("css")) {
                 	langages[2]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("git")) {
                 	langages[3]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("php")) {
                 	langages[4]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("laravel")) {
                 	langages[5]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("symfony")) {
                 	langages[6]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("maven")) {
                 	langages[8]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("docker")) {
                 	langages[9]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("jenkins")) {
                 	langages[10]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("java")) {
                 	langages[11]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("html")) {
                 	langages[12]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("mariadb")) {
                 	langages[13]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("mongodb")) {
                 	langages[14]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("mysql")) {
                 	langages[15]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("ux")) {
                 	langages[16]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("ui")) {
                 	langages[17]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("angular")) {
                 	langages[18]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("react")) {
                 	langages[19]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("rest")) {
                 	langages[20]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("bootstrap")) {
                 	langages[21]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("agile")) {
                 	langages[22]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("swift")) {
                 	langages[23]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("javascript")) {
                 	langages[24]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("c/c++")) {
                 	langages[25]=1;
                 	langages[26]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("c++")) {
                 	langages[26]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("poo")||extraction.SearchLang2(listTry.get(q),Keys).equals("oop")) {
                 	langages[27]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("ruby")) {
                 	langages[28]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("c#")) {
                 	langages[29]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("sap")) {
                 	langages[30]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("express")) {
                 	langages[31]=1;
                 	
                 }
                 if(extraction.SearchLang2(listTry.get(q),Keys).equals("nestjs")) {
                 	langages[32]=1;
                 	
                 }
                 }String c="";
                 for(int b=0;b<langages.length;b++) {
                	 
                	 String q=","+"'";
                	 String a=q+String.valueOf(langages[b])+"'";
                	  c=c+a;
                	 
                 }
              
        
                
               
                //INSERT INTO table2 (column1, column2, column3, ...)
                //id_annonce-nomCompagnie-date-profil-region-secteur-domaine-niveauEtude-type_contrat-Niveau-Experience	-
                //j2ee-jquery-css-git-php-laravel-symfony-springboot-maven-docker
                //-jenkins,java,html,mariadb	mongodb	mysql	ux	ui	angular	react	
                //rest	bootstrap	agile	swift	javascript	c	c++	poo	ruby	c#
                //sap	express	nestjs node
             /* String normalized = Normalizer.normalize(NiveauExperience, Normalizer.Form.NFD);
              String exp = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
              normalized = Normalizer.normalize(reg1[1], Normalizer.Form.NFD);
              String regionn = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

              String domainee = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
              normalized = Normalizer.normalize(profil, Normalizer.Form.NFD);
              String profill = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
              normalized = Normalizer.normalize(date[1], Normalizer.Form.NFD);
              String nomCompagniie = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
               */delimiter="[.]";
               String Date[]=date[0].trim().split(delimiter);
               String nomCompagnie=date[1].replace("'","''");
               profil=profil.split(delims1)[0].replace("'","''");
               Region=Region.replace("'","''");
               NiveauExperience=NiveauExperience.replace("'","''");
               NiveauEtude=NiveauEtude.replace("'","''");
               typeContrat=typeContrat.replace("'","''");
               ville=ville.replace("'","''");
             
               
              //String data ="INSERT  INTO `annonce` (`nomCompagnie`, `date`, `profil`, `region`, `secteur`, `domaine`, `niveauEtude`, `type_contrat`, `Niveau_Experience`, `j2ee`, `jquery`, `css`, `git`, `php`, `laravel`, `symfony`, `springboot`, `maven`, `docker`, `jenkins`, `java`, `html`, `mariadb`, `mongodb`, `mysql`, `ux`, `ui`, `angular`, `react`, `rest`, `bootstrap`, `agile`, `swift`, `javascript`, `c`, `c++`, `poo`, `ruby`, `c#`, `sap`, `express`, `nestjs`, `node`) VALUES("+"'"+nomCompagniie+"'"+","+"'"+Date[2]+"-"+Date[1]+"-"+Date[0]+"'"+","+"'"+profill+"'"+","+"'"+regionn+"'"+","+"'"+secteur+"'"+","+"'"+annonce.getDomaine()+"'"+","+"'"+NiveauEtude+"'"+","+"'"+typeContrat+"'"+","+"'"+exp+"'"+c+");";
                 String data=nomCompagnie+"#"+Date[2]+"-"+Date[1]+"-"+Date[0]+"#"+profil+"#"+Region+"#"+secteur+"#"+annonce.getDomaine()+"#"+NiveauEtude+"#"+typeContrat+"#"+NiveauExperience+"#"+ville+"#"+c+"#"+nbr_posts;
                
              write(chemin+"json.txt",data);
              	String b="";
              	/* for(int q=0;q<listTry.size();q++)
                 {
              		 b=b+listTry.get(q)+",";
                 }*/
             // b=DataWriter.removeLastChar(b);
              	 
	        	//data=nomCompagnie+"#"+Date[2]+"-"+Date[1]+"-"+Date[0]+"#"+profil+"#"+Region+"#"+secteur+"#"+annonce.getDomaine()+"#"+NiveauEtude+"#"+typeContrat+"#"+NiveauExperience+"#"+ville+"#"+b+"#"+nbr_posts;
	        	//append.write(chemin+"json1.sql",data);
	       		 }
		       
                 //.out.println(annonce.toString());
	       		}

	       		 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Compagnies;
			
   		 }

	public static List<String> removeDuplicates(List<String> list)
    {
  
        // Create a new ArrayList
       List<String> newList = new ArrayList<String>();
  
        // Traverse through the first list
        for (String element : list) {
  
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
  
                newList.add(element);
            }
        }
  
        // return the new list
        return newList;
    }
	
	static List<String> extractInfoOfEachLine(String Filename) throws IOException{
		List<String> listeDeString=new ArrayList<String>();
	      // Créer l'objet File Reader
	      FileReader fr = new FileReader(Filename);
	      // Créer l'objet BufferedReader 
	      BufferedReader br = new BufferedReader(fr);  
	      String str;
	      // Lire le contenu du fichier
	      while((str = br.readLine()) != null)
	      {
	    	  listeDeString.add(str);
	      }
	         //Pour chaque ligne, incrémentez le nombre de lignes
	         
	        return listeDeString;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
	}

}
