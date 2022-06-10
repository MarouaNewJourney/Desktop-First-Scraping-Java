package extractIdproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import controller.BD;
import dao.Annonce;

public class RegExtract {
	public static String[] dev= {"developer","d�veloppeur","fullstack","frontend","backend","D�veloppement","ing�nieur logiciel","front end","back end","full stack"};
	public static String[] sec= {"security","s�curit�","Cybers�curit�","cyber security"};
	public static String[] cloud= {"cloud","cloud computing"};
	public static String[] sys= {"Syst�me et R�seaux","Syst�mes et R�seaux","Syst�me d'information et r�seaux","Syst�me dInformation et R�seaux"};
	public static String[] design= {"graphic design","graphic designer","design"};
	public static String[] domaine= {"production","data","dba","support informatique","vmware","sap","gestion","devops","it","Marketing Digital","Business Developpement"};
	  public static String[] ingenierie= {"dba","vmware","moe","business Analyst","devops","data","g�nie civil","hydro Informatique"};
	  public static   String[] rst= {"networks & telecoms","reseaux","rst"};
	  public static String[] dictio= {"intervewer","commerce","administration","chaudronnier","nettoyage","conditionnement","vente","m�lange","stock","activit�","tuyau","achat","marketing","atelier","qualit�","soudeur","hse","thermique","agroalimentaire","logistique","maintenance","hygi�ne","export","logistique","transport","juriste","tr�sorerie","comptable","gestion","trade","business","mon�tique","marketing","production","accueil","design","dba","vmware","moe","business Analyst","devops","data","g�nie civil","hydro Informatique","vente","conseiller","analyste","rh","industrie","caisse","administration","consultation","commerce","consultation","cuisine","comptabilit�","electromecanique","hydraulique","design","security","systeme et reseaux","cloud","developpement","teleprospection","teleconseil","rst","portefeuille","operation","mecanique","interviewer","magazin","infirmier","juriste","tr�sorerie","comptabilit�","gestion","trade","business","mon�tique","marketing","production","accueil","dba","vmware","moe","business Analyst","devops","data","g�nie civil","hydro Informatique","r�ception","th�rapeute","pontier","femme de chambre","auxiliaire de vie ","nounou","superviseur s�curit�","recrutement","gestion","trade","op�ration","chauffeur","manager","commercial","btob","electrom�canicien","production","barman","acheteur","btob","finance","laboratoire","auditeur","hse","marketing","webdesign","chaudronnier","nettoyage","conditionnement","vente","m�lange","stock","activit�","tuyau","achat","marketing","atelier","qualit�","soudeur","hse","thermique","agroalimentaire","logistique","maintenance","hygi�ne","export","logistique","transport","b�timent","ferrailleur","ma�on","boiseur","fraiseur","coursiers","contr�leur","conducteur","chaudronnier","dessinateur","plombier","chantier","ma�on","televente","marketing digital","btop"};
	  public static   String[]  banques= {"juriste","tr�sorerie","comptabilit�","gestion","trade","business","mon�tique","marketing","production","accueil"};
	  public static   String[]  conseilCommercial= {"conseiller commercial","conseillers commerciaux"};
	  public static   String[] commercial= {"commerciaux","commercial","commerciale","commerce"};
	  public static   String[] portefeuille= {"charg�s de portefeuille","charg�(es) de portefeuille","charg� de portefeuille"};
	  public static  String[]  analyste= {"analyst","analyste"};
	  public static  String[] security= {"securite","s�curit�","security"};
	  public static  String[] Develop= {"d�veloppeur","developer","developpeur","developpement web","dev web","d�veloppeurs"};
	  public static  String[] service= {"r�ception","th�rapeute","pontier","femme de chambre","auxiliaire de vie ","nounou","superviseur s�curit","recrutement","gestion","trade","op�ration","chauffeur","manager","commercial","btob","electrom�canicien","production","barman","acheteur","btob","finance","laboratoire","auditeur","hse","marketing","webdesign",};
	  public static String[] interviewer= {"intervieweur","interview","intervieweurs",};
	  public static	 String[] teleconseil= {"t�l�conseil","t�l�conseillers","t�l�conseill�re","t�l�conseil","teleconseil"};
	  public static	 String[] rh= {"ressources humaines","rh"};
	  public static	 String[] televendeur= {"t�l�vendeurs","t�l�vendeuses","t�l�vendeur","t�l�vente","televente"};
	  public static	 String[] caisse= {"caissier","caisse","caissi�re"};
	  public static  String[] comptable= {"comptabilit�","comptable"};
	  public static  String[] formation= {"formateur","formatrice","formateurs","formatrices"};
	  public static  String[] consultation= {"consultant","consultante","consultante"};
	  public static  String[] operation= {"operations specialist","operation specialists","operations","op�rateurs","op�rateur"};
	  public static  String[] cuisine= {"cuisinier","cuisini�res","cuisiniers","cuisines"};
	  public static  String[] hydraulique= {"hydraulicien","hydrologue","hydraulique"};
	  public static  String[] teleprospection= {"t�l�prospcteur","t�l�prospectrice","t�l�prospect"};
	  public static  String[] industrie= {"chaudronnier","nettoyage","conditionnement","vente","m�lange","stock","activit�","tuyau","achat","marketing","atelier","qualit�","soudeur","hse","thermique","agroalimentaire","logistique","maintenance","hygi�ne","export","logistique","transport"};
	  public static  String[] administration= {"administrator","administratif"};
	  public static  String[] magazin= {"magazinier","magazin","magasinier"};
	  public static  String[] mecanique= {"m�canicien","m�canique"};
	  public static  String[] infirmerie= {"infirmier","infirmi�re"};
	  public static  String[] conseiller= {"conseillers","conseill�re","conseiller","conseill�res"};
	  public static  String[] electromecanique= {"electromecanicien","electrique"};
	  public static  String[] btp= {"b�timent","ferrailleur","ma�on","boiseur","fraiseur","coursiers","contr�leur","conducteur","chaudronnier","dessinateur","plombier","chantier","ma�on"};
	  public static  String[] tab= {"informatique","banque","services","marketing","industrie","btp","ing�nierie","h�tellerie","vente"};
     public static String function(String[] nomtab,String nom,String profile) {
    	 for(String n:nomtab) {
    		 
    		 if(profile.contains(n)) {
    			 
    			 profile=nom;
    		 }
    		 
    		 
    	 }
    	 return profile;
    	 
     }
     public static String function1(String[] nomtab,String profile) {
    	 for(String n:nomtab) {
    		 
    		 if(profile.toLowerCase().contains(n.toLowerCase())) {
    			 
    			 profile=n;
    		 }
    		 
    	 }
    	 return profile;
    	 
     }
     public static boolean trouve(List<String> a,String word) {
    	boolean b=false;
    	 for(String el:a) {
    		 if(word.equals(el)) {
    			 
    			 b=true;
    		 }
    		 
    	 }
    	 return b;
    	 
     }
     
     
		public static void  extractInfo(String lien,String sect) {
                   String profil="";
                   String domaine="";
				Document doc2;
				Document doc3;
				List<String> dom=new ArrayList<String>();
				try {
					BD bdcon = new BD();
					Connection con1 = bdcon.connexion();
					
				
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
		   				String Region=doc3.select("div.field-name-field-offre-region>div.field-items>div.field-item").text();
		   		   		
		   		   		String ville=doc3.select("table.job-ad-criteria").select("tr").get(4).select("td").get(1).text();
		   		   	
		   			Elements jbDetails=doc3.select("div#job-ad-details-"+ident+">div>div.clearfix>div");
		   			
		   			Element jbDetail=jbDetails.get(2);
		   	/*	 String textComp=doc3.toString().toLowerCase();
		   		profil=function1(ingenierie,textComp);
		       	profil=function1(RegExtract.banques,textComp);
		    	profil=function1(RegExtract.btp,textComp);
		    	profil=function1(RegExtract.industrie,textComp);
		    	profil=function1(RegExtract.service,textComp);
		    	profil=function(RegExtract.analyste,"analyste",textComp);
		    	profil=function(RegExtract.rh,"rh",textComp);
		    	profil=function(RegExtract.caisse,"caisse",textComp);
		    	profil=function(RegExtract.administration,"administration",textComp);
		    	profil=function(RegExtract.commercial,"commerce",textComp);
		    	profil=function(RegExtract.conseilCommercial,"commerce",textComp);
		    	profil=function(RegExtract.consultation,"consultation",textComp);
		    	profil=function(RegExtract.cuisine,"cuisine",textComp);
		    	profil=function(RegExtract.comptable,"comptabilit�",textComp);
		    	profil=function(RegExtract.Develop,"developpement",textComp);
		    	profil=function(RegExtract.electromecanique,"electromecanique",textComp);
		    	profil=function(RegExtract.hydraulique,"hydraulique",textComp);
		    	profil=function(RegExtract.infirmerie,"infirmier",textComp);
		    	profil=function(RegExtract.interviewer,"interviewer",textComp);
		    	profil=function(RegExtract.magazin,"magazin",textComp);
		    	profil=function(RegExtract.mecanique,"mecanique",textComp);
		    	profil=function(RegExtract.operation,"operation",textComp);
		    	profil=function(RegExtract.portefeuille,"portefeuille",textComp);
		    	profil=function(RegExtract.rst,"rst",textComp);
		    	profil=function(RegExtract.security,"security",textComp);
		    	profil=function(RegExtract.teleconseil,"teleconseil",textComp);
		        profil=function(RegExtract.teleprospection,"teleprospection",textComp);
		        profil=function(RegExtract.dev,"developpement",textComp);
		        profil=function(RegExtract.cloud,"cloud",textComp);
		        profil=function1(RegExtract.domaine,textComp);
		        profil=function(RegExtract.sys,"systeme et reseaux",textComp);
		        profil=function(RegExtract.sec,"security",textComp);
		        profil=function(RegExtract.design,"design",textComp);
		        profil=function(RegExtract.televendeur,"televente",textComp);*/
		   		
		   			Elements links2=classJob3.select("h5").select("a");
		   			doc3 = Jsoup.connect(urlAnnonce).get();
		   			for(int y=0;y<contJob;y++)
		       		{
		       	
		       		
		       
		       	 profil=domaine=links2.get(y).text().toLowerCase();
		       
		      
		   			
		   			//.out.println(typeContrat+" "+NiveauEtude);
		   			
		   			//.out.println(doc3.select("table.job-ad-criteria"));
		   			
		   		String nbr_posts=doc3.select("table.job-ad-criteria").select("tr").get(doc3.select("table.job-ad-criteria").select("tr").size()-1).select("td").get(1).text();
		   		String typeContrat=doc3.select("div.field-name-field-offre-contrat-type ").text();
	   			String  NiveauExperience=doc3.select("div.field-name-field-offre-niveau-experience").text();
	   			
	   		    typeContrat=doc3.select("div.field-name-field-offre-contrat-type ").text();
	   			
	   			//
		       		
		       		
		       		//.out.println("links3"+links3);
		       		Elements p2=classJob3.select("p.job-recruiter");
			
		       	
		       			String Ptext=p2.get(y).text();
			       		String delims1="[/|-]";
			       		 String[] date=Ptext.split(delims1);
			       		 delimiter="[.]";
			       		String Date[]=date[0].trim().split(delimiter);
		               String nomCompagnie=date[1].replace("'","''");
		               Region=Region.replace("'","''");
		               ville=ville.replace("'","''");
	                    int nbr=Integer.parseInt(nbr_posts);
	                    sect=sect.replace("'", "''");
		       		    String date1=Date[2]+"-"+Date[1]+"-"+Date[0];
		       		    
		   
		       			
		       		 profil=function1(ingenierie,profil);
				       	profil=function1(RegExtract.banques,profil);
				    	profil=function1(RegExtract.btp,profil);
				    	profil=function1(RegExtract.industrie,profil);
				    	profil=function1(RegExtract.service,profil);
				    	profil=function(RegExtract.analyste,"analyste",profil);
				    	profil=function(RegExtract.rh,"rh",profil);
				    	profil=function(RegExtract.caisse,"caisse",profil);
				    	profil=function(RegExtract.administration,"administration",profil);
				    	profil=function(RegExtract.commercial,"commerce",profil);
				    	profil=function(RegExtract.conseilCommercial,"commerce",profil);
				    	profil=function(RegExtract.consultation,"consultation",profil);
				    	profil=function(RegExtract.cuisine,"cuisine",profil);
				    	profil=function(RegExtract.comptable,"comptabilit�",profil);
				    	profil=function(RegExtract.Develop,"developpement",profil);
				    	profil=function(RegExtract.electromecanique,"electromecanique",profil);
				    	profil=function(RegExtract.hydraulique,"hydraulique",profil);
				    	profil=function(RegExtract.infirmerie,"infirmier",profil);
				    	profil=function(RegExtract.interviewer,"interviewer",profil);
				    	profil=function(RegExtract.magazin,"magazin",profil);
				    	profil=function(RegExtract.mecanique,"mecanique",profil);
				    	profil=function(RegExtract.operation,"operation",profil);
				    	profil=function(RegExtract.portefeuille,"portefeuille",profil);
				    	profil=function(RegExtract.rst,"rst",profil);
				    	profil=function(RegExtract.security,"security",profil);
				    	profil=function(RegExtract.teleconseil,"teleconseil",profil);
				        profil=function(RegExtract.teleprospection,"teleprospection",profil);
				        profil=function(RegExtract.dev,"developpement",profil);
				        profil=function(RegExtract.cloud,"cloud",profil);
				        profil=function1(RegExtract.domaine,profil);
				        profil=function(RegExtract.sys,"systeme et reseaux",profil);
				        profil=function(RegExtract.sec,"security",profil);
				        profil=function(RegExtract.design,"design",profil);
				        profil=function(RegExtract.conseiller,"conseiller",profil);
				        profil=function(RegExtract.comptable,"comptabilit�",profil);
				       System.out.println(profil);
				    
				    
                   for(String el:dictio) {
                    	if(profil.equals(el)) {
				     bdcon.insertionReg(con1,nbr,sect,nomCompagnie,Region,ville,profil,domaine,date1);
                    }}
		       		 
		       	      	
					        
	                 //.out.println(annonce.toString());
		       		}

		       		 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
	   		 }


	

		public static void main(String[] args) {
			

	}

}
