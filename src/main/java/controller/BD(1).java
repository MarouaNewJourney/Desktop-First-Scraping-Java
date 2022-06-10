package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

import dao.Annonce;
import extractIdproj.ScraperJava;
import extractIdproj.append;


public class BD {
	static String  cheminLocal=ScraperJava.chemin;

	public  Connection connexion() {
		
		try{
			Class c = Class.forName("com.mysql.jdbc.Driver");
			Driver pilote = (Driver) c.newInstance();
			DriverManager.registerDriver(pilote);
			String nomConnexion = "root";
			String motDePasse = "";
			String UrlDB="jdbc:mysql://localhost:3306/emploidb";
			Connection con1 = DriverManager.getConnection(UrlDB, nomConnexion, motDePasse);
			
			return con1;

		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet selection_Annonce(Connection con1){
		
		try {
			String sql="SELECT * FROM annonce";
			Statement smt = con1.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next())
			{
				System.out.println(rs.getString("nomCompagnie") + "\n" + rs.getString("date")+ "\n" + rs.getString("profil") + "\n\n");
			}
			return rs;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	public ResultSet selection_Compagnie(Connection con1){
		
		try {
			String sql="SELECT * FROM `compagnie` ";
			Statement smt = con1.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			while (rs.next())
			{
				
				System.out.println(rs.getString("nom_compagnie") + "\n" + rs.getString("secteur")+ "\n" + rs.getString("ville") + "\n\n");
			}
			return rs;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	public int insertionCompagnie(Connection con1, String filename){
		int compteur;
		try {
			String sq="Select Count(*) from compagnie";
			Statement stmt=con1.createStatement();
			ResultSet rs1=stmt.executeQuery(sq);
			rs1.next();
			compteur=rs1.getInt("Count(*)");
			
			File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);



            String line;
            
            int rs;
           
         
          int n=append.nbrline(filename);
        
          
         
			while((line = br.readLine())!= null){ 
				
			
				if(!line.equals("#####")) {
					
				
                String[]tab=line.split("[#]");
                String nom_compagnie=tab[0];
             
                String site=tab[1];
                String secteur=tab[4];
                String  ville=tab[2];
                String pays=tab[3];
                String urlImage=tab[5];

                String sql0="SELECT * FROM compagnie where nom_compagnie="+"'"+tab[0]+"';";
               // System.out.println(sql0);
    			Statement smt0 = con1.createStatement();
    			ResultSet rs0 = smt0.executeQuery(sql0);
    		
              if(!rs0.next()) {
                String data ="INSERT  INTO `compagnie` (`nom_compagnie`, `site`, `secteur`, `ville`,`pays`,`urlImage`) VALUES("+"'"+nom_compagnie+"'"+","+"'"+site+"'"+","+"'"+secteur+"'"+","+"'"+ville+"'"+","+"'"+pays+"'"+","+"'"+urlImage+"'"+");";
                Statement smt = con1.createStatement();
               
                try {
                      rs=smt.executeUpdate(data);
                   

               } catch (SQLException e) {
                    log("exception", e);
                    }
                
                                }
			}

              
               }  stmt=con1.createStatement();
   			 rs1=stmt.executeQuery(sq);
   			 rs1.next();
   			 int compteur1=rs1.getInt("Count(*)");
   			 compteur=compteur1-compteur;
			
		
            System.out.println("nbr de lignes insérées "+compteur);
        
          
      
			
            fr.close();
       br.close();
       return compteur;}
		
          
          
          catch (Exception e) {
			
			e.printStackTrace();
			return 0;
			
		}

		
	}
	
public int insertion(Connection con1,String filename){
		int compteur=0;
		try {
			 String sql="Select Count(*) from annonce;";
 			 Statement st = con1.createStatement();
 			 
 			ResultSet rs1 = st.executeQuery(sql);
 			rs1.next();
			int compteurA=rs1.getInt("Count(*)");

			File file = new File(filename);
            Scanner scan=new Scanner(file);
            String line;
           
            
        
         line=scan.nextLine();
         
			while(scan.hasNextLine()){
			    line=scan.nextLine();
			  
			    
		
                String[]tab=line.split("[#]");
                String nomCompagnie=tab[0].replace("'","''");
             
                String date=tab[1];
                String profil=tab[2];
                String  region=tab[3];
                String secteur=tab[4];
                String domaine=tab[5];
                String NiveauEtude=tab[6];
                String typeContrat=tab[7];
                String exp=tab[8];
                String c=tab[10];
                String ville=tab[9];
                String post=tab[11];
           
                sql="Select COUNT(*) from annonce where nomCompagnie="+"'"+nomCompagnie+"'"+"and date="+"'"+date+"'"+"and profil="+"'"+profil+"'"+";"; System.out.println(sql);
				 st = con1.createStatement();
				 rs1 = st.executeQuery(sql);
				 rs1.next();
				 if(rs1.getInt("COUNT(*)")==0) {
                String data ="INSERT  INTO `annonce` (`nomCompagnie`, `date`, `profil`,`nbr_post`,`ville`,  `region`, `secteur`, `domaine`, `niveauEtude`, `type_contrat`, `Niveau_Experience`, `j2ee`, `jquery`, `css`, `git`, `php`, `laravel`, `symfony`, `springboot`, `maven`, `docker`, `jenkins`, `java`, `html`, `mariadb`, `mongodb`, `mysql`, `ux`, `ui`, `angular`, `react`, `rest`, `bootstrap`, `agile`, `swift`, `javascript`, `c`, `c++`, `poo`, `ruby`, `c#`, `sap`, `express`, `nestjs`, `node`) VALUES("+"'"+nomCompagnie+"'"+","+"'"+date+"'"+","+"'"+profil+"'"+","+"'"+post+"'"+","+"'"+ville+"'"+","+"'"+region+"'"+","+"'"+secteur+"'"+","+"'"+domaine+"'"+","+"'"+NiveauEtude+"'"+","+"'"+typeContrat+"'"+","+"'"+exp+"'"+c+");";
                Statement smt = con1.createStatement();
              System.out.println(data);  
        
              int  res=smt.executeUpdate(data);
             
				 }
				 
                    
                    /*String sql1="Select t1.id_annonce FROM annonce t1 INNER JOIN  annonce t2 WHERE t1.id_annonce>t2.id_annonce AND t1.nomCompagnie=t2.nomCompagnie AND t1.profil = t2.profil AND t1.date=t2.date;";;
         			 st = con1.createStatement();
         			 
         			 rs1 = st.executeQuery(sql1);
         			while(rs1.next()) {
         			int id=rs1.getInt("t1.id_annonce");
         			 String sql2="Delete FROM annonce  WHERE id_annonce="+id+";";
        			 Statement st1 = con1.createStatement();
        			  res=st1.executeUpdate(sql2);
        			  */
        	
			}
			
        			
        				
         			
         			 
         			
			
	     String sql1="Select Count(*) from annonce;";
		Statement st1 = con1.createStatement();
		
			 ResultSet rs2 = st1.executeQuery(sql1);
			 rs2.next();
			  compteur=rs2.getInt("Count(*)");
			 compteur=compteur-compteurA;
		 System.out.println("nbr de lignes insérées "+compteur); 
		 System.out.println(compteurA);
		
		if(compteur>0) {
			BD.drop_reg(con1);
			BD.insertionRegression(con1);
			
		}
			
			
		scan.close();
		return compteur;
			
         }  
		catch (Exception e) {
			
			e.printStackTrace();
			return 0;
			
		}

		
}
	
			

	
public void insertionDataScrapping(Connection con1,int nbrcompagnie,int nbrAnnonce){
		int rs;
		LocalDateTime now = LocalDateTime.now();  
		try {
		
		    String data ="INSERT  INTO `datascraping` (`date_scraping`, `nbr_compagnie`,`nbr_annonce`) VALUES("+"'"+now+"'"+","+"'"+nbrcompagnie+"'"+","+"'"+nbrAnnonce+"'"+");";
		
            Statement smt1 = con1.createStatement();
            String sql =data;
            rs=smt1.executeUpdate(sql);
            System.out.println("nous avons inséré "+nbrcompagnie+"compagnies et "+nbrAnnonce+"annonces \n");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
public static void drop_reg(Connection con1) {
	
	 try{ 
		
		
			 String sql="DELETE FROM `regression`;";;
			 Statement st = con1.createStatement();
			
			 st.executeUpdate(sql); 
		

		 }
	  catch (Exception e) {
			
			e.printStackTrace();
			
			
		}
	
		
}
public static void insertionRegression(Connection con1){
	
	try {
	
		String sql = "SELECT distinct domaine from `regressiongen`" ;
		Statement smt = con1.createStatement();
		ResultSet rs = smt.executeQuery(sql);
	
		while(rs.next()) {
		
		String domaine=rs.getString("domaine");
		sql = "SELECT sum(nbrPostes),count( DISTINCT compagnie),count(*) FROM `regressiongen` where domaine="+"'"+domaine+"'"+" ;";
		 System.out.println(sql);
		smt= con1.createStatement();
		 ResultSet rs0 = smt.executeQuery(sql);
		
		 while(rs0.next()) {
			 
		 
		int nbr_compagnies=rs0.getInt("count( DISTINCT compagnie)");
		int nbr_postes=rs0.getInt("sum(nbrPostes)");
		int nbr_annonces=rs0.getInt("count(*)");
		sql="SELECT COUNT(*) from regression where domaine="+"'"+domaine+"'"+" ;";
		smt=con1.createStatement();
		 ResultSet rs2=smt.executeQuery(sql);
		 rs2.next();
		if(rs2.getInt("COUNT(*)")==0) {
		String sql1 = "INSERT  INTO `regression` (`domaine`, `nbr_annonces`,`nbr_postes`,`nbr_compagnies`) VALUES("+"'"+domaine+"'"+","+"'"+nbr_annonces+"'"+","+"'"+nbr_postes+"'"+","+"'"+nbr_compagnies+"'"+");";
		 Statement smt1= con1.createStatement();
		 int rs1 = smt1.executeUpdate(sql1);}
		/*else if(rs2.getInt("COUNT(*)")==1) {
			String sql2="SELECT nbr_postes,nbr_compagnies from regression";
			Statement smt2=con1.createStatement();
			ResultSet rs3=smt2.executeQuery(sql2);
			rs3.next() ;
			nbr_postes=nbr_postes+rs3.getInt("nbr_postes");
		    int nbr_cmpagnies=nbr_compagnies+rs3.getInt("nbr_compagnies");
			String sql1 = "UPDATE `regression` set nbr_postes="+"'"+nbr_postes+"'"+"and nbr_compagnies="+"'"+nbr_compagnies+"'"+"and nbr_annonces="+"'"+nbr_annonces+"'" +"where domaine="+"'"+domaine+"'"+";";
			 System.out.println(sql1);
			Statement smt1= con1.createStatement();
			 int rs1 = smt1.executeUpdate(sql1);}*/
			
		
		
		 
		
		}
		}
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
}
public  void insertionReg(Connection con1,int nbr,String sec,String comp,String Region,String ville,String  domaine,String profil,String date){
	
	try {
	  profil=profil.replace("'", "''");
	   String sql1="Select Count(*) from regressiongen where profil="+"'"+profil+"'"+"and date="+"'"+date+"'"+"and domaine="+"'"+domaine+"'"+"and compagnie="+"'"+comp.trim()+"'"+";";
	   
	   System.out.println(sql1);
	Statement smt=con1.createStatement();
	   ResultSet rs=smt.executeQuery(sql1);
	   rs.next();
	   if(rs.getInt("Count(*)")==0) {
		 sql1 = "INSERT  INTO `regressiongen` (`nbrPostes`, `compagnie`,`secteur`,`Region`,`ville`,`domaine`,`profil`,`date`) VALUES("+"'"+nbr+"'"+","+"'"+comp.trim()+"'"+","+"'"+sec+"'"+","+"'"+Region+"'"+","+"'"+ville+"'"+","+"'"+domaine+"'"+","+"'"+profil+"'"+","+"'"+date+"'"+");";
		 System.out.println(sql1);
		Statement smt1= con1.createStatement();
		 
		 int rs1 = smt1.executeUpdate(sql1);
		 
	   }
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
}
public  void deleteReg(Connection con1){
	
	try {
	
	
	
		String sql1 = "DELETE from  `regressiongen`  ";
		 Statement smt1= con1.createStatement();
		 int rs1 = smt1.executeUpdate(sql1);
		 
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
}

	private void log(String string, SQLException e) {
		// TODO Auto-generated method stub
		
	}
			

	public static void main(String[] args) {
		// TODO Auto-generated method stub

				
				BD bdcon = new BD();
				Connection con1 = bdcon.connexion();
		          int nb_Annonce=bdcon.insertion(con1,cheminLocal+"json.txt");//fichier avec les données annonces
		        int nb_compagnie=bdcon.insertionCompagnie(con1,cheminLocal+"json2.txt");//fichier avec les données compagnies
		         bdcon.insertionDataScrapping(con1,nb_compagnie, nb_Annonce);
		         bdcon.insertionRegression(con1);
		          //insertionRegression(con1);

				
				
			}
			
			
	
	


}