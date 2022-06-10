package ML;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.BD;
import extractIdproj.append;
import extractIdproj.extraction;


public class DataWriter extends append {
	public static char  separator=File.separatorChar;
	public static String cheminLocal=".."+separator+"extractIdproj"+separator+"src"+separator+"main"+separator+"java"+separator;
	public static String chemin=cheminLocal+"data"+separator+"data.arff";
	public static String cheminReg=cheminLocal+"data"+separator+"reg.arff";
	public static String cheminTrain=cheminLocal+"data"+separator+"train.arff";
	public static String cheminclass=cheminLocal+"data"+separator+"class.arff";
	public static String[] engineer= {"engineer","ingénieur","ingenieur"};
	public static String[] televente= {"télévendeur","télévendeurs","télévendeuses","experts en télévente","expert en télévente","superviseur télévente"};
	public static String[] comptable= {"financial accountant","comptabilité","comptable","experts comptable","expert comptable"};
	public static String[] developpeur= {"developer","full stack","front end","back end","développement web","développeur"};
	public static String[] profil= {"webdesigner","administrateur","agent","devops","adjoint","analyste","architecte","assistant","auditeur","business analyst","manager","chef de projet","commercial","conseiller","consultant","data scientist","data analyst","dba","gestionnaire","infographiste","informaticien","juriste","manager","professeur","rédacteur","référenceur","responsable","stagiaire","technicien","téléconseiller","téléopérateur","webmaster","référenceur"};
	public static String[] tous= {"webdesigner","administrateur","agent","devops","adjoint","analyste","architecte","assistant","auditeur","business analyst","manager","chef de projet","commercial","conseiller","consultant","data scientist","data analyst","dba","gestionnaire","infographiste","informaticien","juriste","manager","professeur","rédacteur","référenceur","responsable","stagiaire","technicien","téléconseiller","téléopérateur","webmaster","référenceur","developpeur","ingenieur","televendeur","comptable"};
	
	public static String[] tab= {"j2ee","jquery","css","git","php","laravel","symfony","springboot","maven","docker","jenkins","java","html","mariadb","mongodb","mysql","ux","ui","angular","react","rest","bootstrap","agile","swift","javascript","c","c++","poo","ruby","c#","sap","express","nestjs","node"};
	public static void anotherBoucle(String lang,String filename) {
		String name="@attribute "+lang+" {yes,no}";
		System.out.println(name);
		write(filename, name.toLowerCase());
	}
	public static String[] langages= {"j2ee","jquery","css","git","php","laravel","symfony","springboot","maven","docker","jenkins","java","html","mariadb","mongodb","mysql","ux","ui","angular","react","rest","bootstrap","agile","swift","javascript","c/c++","poo","ruby","c#","sap","express","nestjs","node"};
	public static void boucle(String field,String filename,Connection con1) throws SQLException{
		String sql0="SELECT DISTINCT "+field+" FROM annonce WHERE domaine !=\"null\" and ville !=\"expérience entre 2 ans et 5 ans\"";
		System.out.println(sql0);
			Statement smt0=con1.createStatement();
			ResultSet rs0=smt0.executeQuery(sql0);
			String name1="@attribute "+field+" "+"{ ";
			while(rs0.next())
			{
				name1=name1+rs0.getString(field).split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+",";
			
			}
			name1=removeLastChar(name1);
			
			name1=name1+"}";
			System.out.println(name1);
			write(filename, name1.toLowerCase());
	}
	public static void WriteClass(Connection Con1) throws SQLException, IOException {
		 efface(cheminclass);
		
		write(cheminclass,"@relation Annonce");
		write(cheminclass,"@Attribute profil {administrateur,agent,adjoint,analyste,architecte,assistant,auditeur,business-analyst,webdesigner,chef-de-projet,commercial,conseiller,consultant,data-scientist,data-analyst,dba,gestionnaire,infographiste,informaticien,juriste,manager,professeur,rédacteur,référenceur,responsable,stagiaire,technicien,téléconseiller,téléopérateur,webmaster,ingenieur,developpeur,comptable,televendeur}");
		  String a="@Attribute ville{";
		  String b="@Attribute domaine{";
		  String sql="select distinct ville from annonce";
		  Statement smt=Con1.createStatement();
		  ResultSet rs=smt.executeQuery(sql);
		  String ville="";
		  String domaine="";
		  while(rs.next()) {
			  
			  String ss=rs.getString("ville").toLowerCase();
			 if( !rs.getString("ville").equals("Etudiant, jeune diplômé")&&!rs.getString("ville").equals("Expérience entre 2 ans et 5 ans")&&!rs.getString("ville").equals("Expérience entre 5 ans et 10 ans")&&!rs.getString("ville").equals("Débutant < 2 ans")&&!rs.getString("ville").equals("Stagiaire")) 
			 { 
				 if(!ss.contains("ou") && !ss.contains("-") ) {
					 ss=ss.replace(" ","-").replace(",","-");
				 ville=ville+ss.toLowerCase()+',';
				 
			 }

		  }}
		  a=a+removeLastChar(ville)+"}";
		  sql="select distinct domaine from annonce";
		  smt=Con1.createStatement();
		  rs=smt.executeQuery(sql);
		  
  while(rs.next()) {
			  
			  String ss=rs.getString("domaine").toLowerCase();
		
				 if(!ss.equals("null") ) {
					 ss=ss.replace(" ","-").replace(",","-");
				 domaine=domaine+ss+',';}
		
		  }
  sql="select distinct niveauEtude from annonce";
  smt=Con1.createStatement();
  rs=smt.executeQuery(sql);
  String c="@attribute niveauEtude{";
  String et="";
while(rs.next()) {
	  
	  String ss=rs.getString("niveauEtude").toLowerCase();

		 if(!ss.equals("null") ) {
			 ss=ss.replace(" ","-").replace(",","-");
		 et=et+ss+',';}

  }
sql="select distinct type_contrat from annonce";
smt=Con1.createStatement();
rs=smt.executeQuery(sql);
String d="@attribute typeContrat{";
String tc="";
while(rs.next()) {
	  
	  String ss=rs.getString("type_contrat").toLowerCase().replace(",","-");

		 if(!ss.equals("null") ) {
			 ss=ss.replace(" ","-");
		 tc=tc+ss+',';}

}
sql="select distinct Niveau_Experience from annonce";
smt=Con1.createStatement();
rs=smt.executeQuery(sql);
String e="@attribute NiveauExperience{";
String ne="";
while(rs.next()) {
	  
	  String ss=rs.getString("Niveau_Experience").toLowerCase().replace(",","-");

		 if(!ss.equals("null") ) {
		ss=ss.replace(" ","-");
		 ne=ne+ss+',';}

}
		  b=b+removeLastChar(domaine)+"}";
		  c=c+removeLastChar(et)+"}";
		  d=d+removeLastChar(tc)+"}";
		  e=e+removeLastChar(ne)+"}";
		  //append.write(cheminclass,a);
		  //append.write(cheminclass,b);
		  //append.write(cheminclass,c);
		 // append.write(cheminclass,d);
		 // append.write(cheminclass,e);
		  for (String el:DataWriter.tab) {
		  DataWriter.littleBoucle(cheminclass,el);}
		  
	
		  
		  
		  
		  write(cheminclass,"@DATA");
		  
		  sql="select * from annonce";
		  smt=Con1.createStatement();
		  rs=smt.executeQuery(sql);
		  String data;
		  while(rs.next()) {
			  String ss=rs.getString("ville").toLowerCase();
				 if( !rs.getString("ville").equals("Etudiant, jeune diplômé")&&!rs.getString("ville").equals("Expérience entre 2 ans et 5 ans")&&!rs.getString("ville").equals("Expérience entre 5 ans et 10 ans")&&!rs.getString("ville").equals("Débutant < 2 ans")&&!rs.getString("ville").equals("Stagiaire")) 
				 { 
					 if(!ss.contains("ou") && !ss.contains("-") ) {
						 ss=ss.replace(" ","-");
						 if(!rs.getString("domaine").equals("null")) {
							 String profile=rs.getString("profil").toLowerCase();
							 
							 for(String el:DataWriter.profil) {
									//System.out.println(profile);
								 if(profile.contains(el))
								 {	//System.out.println(profile);
									 profile=el.replace(" ","-");
								 }
								 else {
									 for(String el1:DataWriter.engineer) {
										 if(profile.contains(el1))
										 {	//System.out.println(profile);
											 profile="ingenieur";
										 }
										 else {
											 
											 for(String el2:DataWriter.televente) {
												 if(profile.contains(el2))
												 {	//System.out.println(profile);
													 profile="televendeur";
												 }
												 else {
													 
													 for(String el3:DataWriter.comptable) {
														 if(profile.contains(el3))
														 {	//System.out.println(profile);
															 profile="comptable";
														 }
														 else {
															 for(String el4:DataWriter.developpeur) {
																 if(profile.contains(el4))
																 {	//System.out.println(profile);
																	 profile="developpeur";
																 }
																
																
														 
														 
														 }
														 }
												 
												 }
												 }
											 
										 }
									 }
								 }
								
							 }}
							
							 for(String tout:tous) {
						if(profile.contains(tout)) {
							System.out.println("profil "+profile);
						 //String dom=rs.getString("domaine").toLowerCase().replace(" ","-").replace(",","-");
						 //String nE=rs.getString("niveauEtude").toLowerCase().replace(" ","-").replace(",","-");
						 //String tC=rs.getString("type_contrat").toLowerCase().replace(" ","-").replace(",","-");
						 //String  nEx=rs.getString("Niveau_Experience").toLowerCase().replace(" ","-").replace(",","-");
						 
					// data=profile+','+ss+','+dom+','+nE+','+tC+','+nEx+',';
							data=profile+',';
					 for(String el0:DataWriter.tab) {
						 int lg=rs.getInt(el0);
						 if(lg==0)
						 {
							 
							data=data+"false"+",";
						 }
						 else {
							 data=data+"true"+",";
					 }
					 
				 }data=removeLastChar(data);
					 write(cheminclass, data);}}
					 
			  
		  }
					 }
		  
		  }}}
		  
		public static void littleBoucle(String lien,String name)  {
			String e="@attribute "+name+" {true,false}";
			  write(lien,e);
		}
		
	

	public static String removeLastChar(String s) {
	    return (s == null || s.length() == 0)
	      ? null 
	      : (s.substring(0, s.length() - 1));
	}
       public static void  writeDataArff() throws SQLException, IOException {
    	   List<String> ab=new ArrayList<String>();
    	  
    	 efface(chemin);
    	  write(chemin,"@relation data-annonce");
            BD bdcon = new BD();
			Connection con1 = bdcon.connexion();
			String name = null;
			/*String sql0="SELECT DISTINCT nomCompagnie FROM annonce where domaine IS NOT NULL  ;";
            // System.out.println(sql0);
 			Statement smt0 = con1.createStatement();
 			ResultSet rs0 = smt0.executeQuery(sql0);
 			String name="@attribute compagnie{";
 			while(rs0.next())
 			{
 				name=name+rs0.getString("nomCompagnie").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+",";
 			}
 			name=removeLastChar(name);
 			name=name+"}";
 		    append.write(chemin, name);*/
 		     //boucle("date",chemin,con1);
			boucle("region",chemin,con1);
          // boucle("domaine",chemin,con1);
 			boucle("niveauEtude",chemin,con1);
 			boucle("type_contrat",chemin,con1);
 			boucle("Niveau_Experience",chemin,con1);
 			//boucle("ville",chemin,con1);
 			
 				String name1="@attribute date {1_10,11_20,21_31}";
 				write(chemin, name1);
 				name1="@attribute java {yes,no}";
 				write(chemin, name1);
 				name1="@attribute j2ee {yes,no}";
 			write(chemin, name1);
 				name1="@attribute springboot {yes,no}";
 				write(chemin, name1);
 				name1="@attribute php {yes,no}";
 				write(chemin, name1);
 				name1="@attribute symfony {yes,no}";
 				write(chemin, name1);
 				name1="@attribute laravel {yes,no}";
 				write(chemin, name1);
 				name1="@attribute react {yes,no}";
 				write(chemin, name1);
 				name1="@attribute ui {yes,no}";
 				write(chemin, name1);
 				name1="@attribute ux {yes,no}";
 				write(chemin, name1);
 				
 					//System.out.println(name1);

 				 write(chemin,"@data");
 		   /*for(int i=0;i<langages.length;i++) {
 			   anotherBoucle(langages[i],chemin);
 		   }*/
 		
 		
				
 		  String sql0="SELECT* FROM annonce  where domaine !=\"null\" and ville !=\"expérience entre 2 ans et 5 ans\";";
          // System.out.println(sql0);
			  Statement smt0 = con1.createStatement();
			 ResultSet rs0 = smt0.executeQuery(sql0);
			

				// name=rs0.getString("nomCompagnie").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("date").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("domaine").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("niveauEtude").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("type_contrat").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("Niveau_Experience").split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+",";
				 while(rs0.next()) {
				 name=rs0.getString("region").toLowerCase().split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("niveauEtude").toLowerCase().split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("type_contrat").toLowerCase().split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+","+rs0.getString("Niveau_Experience").toLowerCase().split("[,]")[0].replaceFirst("/","").replaceFirst(" ","").replaceAll(" ","-").replaceAll("'","_")+",";
				 String date="";
				 for(int i=1;i<32;i++) {
					 if(Integer.valueOf(rs0.getString("date").split("[-]")[1])<=10) {
						 date="1_10";
					 }
					 if(Integer.valueOf(rs0.getString("date").split("[-]")[1])<=20) {
						 date="11_20";
					 }
					 if(Integer.valueOf(rs0.getString("date").split("[-]")[1])<=31) {
						 date="21_31";
					 }
					 
				 }
				 name=name+date+",";
				 
				 System.out.println(rs0.getInt("java"));
				 if(rs0.getInt("java")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("j2ee")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("springboot")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("php")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				if(rs0.getInt("symfony")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("laravel")==0){
						name=name+"no"+",";} 
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("react")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("ui")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 if(rs0.getInt("ux")==0){
						name=name+"no"+",";}
							 else {
								 name=name+"yes"+",";}
				 
						 //System.out.println(name);*/
			 		   name=removeLastChar(name);
			 		  write(chemin, name);	 }
			
				 
				 
				 /*for(int i=0;i<langages.length;i++) {
					 System.out.println(rs0.getString(langages[i]));
					 if(rs0.getString(langages[i]).equals('0')){
				name=name+"no"+",";}
					 else {
						 name=name+"yes"+",";}
					 }System.out.println(name);
	 		   name=removeLastChar(name);
	 		   append.write(chemin, name);
				}*/

       }
       public static void writeDataReg1(String filereg, String fileTrain) throws SQLException, IOException {
    	   //1-3 : annonces-posts train and test
    	   
    	   BD bdcon = new BD();
    	   Connection con1 = bdcon.connexion();
    	   String sql="SELECT count(*) FROM regression where domaine IS NOT NULL ;";  
    	   Statement smt = con1.createStatement();
    	   ResultSet rs = smt.executeQuery(sql);
    	   rs.next();
    	   int nbre=rs.getInt("count(*)");
    	   int reste=(nbre*80)%100;
    	   int p8,p2;
    	   if(reste!=0)
 			{
 				p8=((nbre*80)/100)+1;
 				p2=((nbre*20))/100;
 			}
 			p8=((nbre*80)/100);
			p2=((nbre*20))/100;

			efface(filereg);
		write(filereg,"@relation nbrPost-nbrAnnonce");
			write(filereg,"@attribute nbrAnnonce numeric");
			write(filereg,"@attribute nbrPost numeric");
          		
          write(filereg,"@data");
    		String sql0="SELECT * FROM regression where domaine IS NOT NULL  ORDER BY \"domaine\" ASC LIMIT "+p8+";";
            System.out.println(sql0);
                
            Statement smt0 = con1.createStatement();
    		ResultSet rs0 = smt0.executeQuery(sql0);
    		while(rs0.next()) {
    			String nbrA=String.valueOf(rs0.getInt("nbr_annonces"));
   				String nbrP=String.valueOf(rs0.getInt("nbr_postes"));
   				append.write(filereg,nbrA+"    "+nbrP);
   			}
    			
    		   
    		

    		efface(fileTrain);
    		write(fileTrain,"@relation nbrPost-nbrAnnonce");
    		write(fileTrain,"@attribute nbrAnnonce numeric");
          	write(fileTrain,"@attribute nbrPost numeric");
       		write(fileTrain,"@data");
       		String sql1="SELECT * FROM regression where domaine IS NOT NULL  ORDER BY \"domaine\" DESC LIMIT "+p2+";";
            System.out.println(sql1);
       			
     		Statement smt2 = con1.createStatement();
       		ResultSet rs2 = smt2.executeQuery(sql1);
       		while(rs2.next()) {
       			String nbrA=String.valueOf(rs2.getInt("nbr_annonces"));
       			String nbrP=String.valueOf(rs2.getInt("nbr_postes"));
       			write(fileTrain,nbrA+"    "+nbrP);
       		}

    	  	    
       }
       
       
       public static void writeDataReg2(String filereg, String fileTrain) throws SQLException, IOException {
    	   //compagnie-posts regression
    	   BD bdcon = new BD();
    	   Connection con1 = bdcon.connexion();
    	   String sql="SELECT count(*) FROM regression where domaine IS NOT NULL ;";  
    	   Statement smt = con1.createStatement();
    	   ResultSet rs = smt.executeQuery(sql);
    	   rs.next();
    	   int nbre=rs.getInt("count(*)");
    	   int reste=(nbre*80)%100;
    	   int p8,p2;
    	   if(reste!=0)
    	   {
    		   p8=((nbre*80)/100)+1;
    		   p2=((nbre*20))/100;
    	   }
    	   p8=((nbre*80)/100);
    	   p2=((nbre*20))/100;
    


    	  efface(filereg);
    	   write(filereg,"@relation nbrPost-nbrCompagnie");
          		
    	  write(filereg,"@attribute nbrCompagnie numeric");
    	   write(filereg,"@attribute nbrPost numeric");
    	   write(filereg,"@data");
       	   String sql1="SELECT * FROM regression where domaine IS NOT NULL  ORDER BY \"domaine\" ASC LIMIT "+p8+";";
       	   System.out.println(sql1);
       			
       	   Statement smt2 = con1.createStatement();
       	   ResultSet rs2 = smt2.executeQuery(sql1);
       	   while(rs2.next()) {
       		   String nbrA=String.valueOf(rs2.getInt("nbr_compagnies"));
       		   String nbrP=String.valueOf(rs2.getInt("nbr_postes"));
       		   write(filereg,nbrA+"    "+nbrP);
       	   }
       			
   
       	  efface(fileTrain);
       	   write(fileTrain,"@relation nbrPost-nbrCompagnie");
       	  write(fileTrain,"@attribute nbrCompagnie numeric");
       	   write(fileTrain,"@attribute nbrPost numeric");
       		
       	  write(fileTrain,"@data");
       	   sql1="SELECT * FROM regression where domaine IS NOT NULL  ORDER BY \"domaine\" DESC LIMIT "+p2+";";
           System.out.println(sql1);
          			
           smt2 = con1.createStatement();
           rs2 = smt2.executeQuery(sql1);
           while(rs2.next()) {
        	   String nbrA=String.valueOf(rs2.getInt("nbr_compagnies"));
        	   String nbrP=String.valueOf(rs2.getInt("nbr_postes"));
        	   write(fileTrain,nbrA+"    "+nbrP);
           }
          			
			    
       }
       
       
/*       public static void dwarff() {
    	   try {
			DataWriter.writeDataArff();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Clustering
    	   
       }
	 public static void dwreg() {
		 try {
			DataWriter.writeDataReg(cheminReg,cheminTrain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       }
	 public static void dwclass() {
		 BD db=new BD();
			Connection con=db.connexion();
			try {
				DataWriter.WriteClass(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
*/
	public static void datawriterclass() {
		BD db=new BD();
		Connection con=db.connexion();
		try {
			DataWriter.WriteClass(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Classification
	}
	
	public static void datawriterclustering() {
        try {
			DataWriter.writeDataArff();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Clustering
	}
		
    public static void datawrriterreg1() {    //annonces-posts  
        try {
			DataWriter.writeDataReg1(cheminReg,cheminTrain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Regression
	}
    
    public static void datawrriterreg2() {     //compagnies-posts 
        try {
			DataWriter.writeDataReg2(cheminReg,cheminTrain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Regression
	}

}
