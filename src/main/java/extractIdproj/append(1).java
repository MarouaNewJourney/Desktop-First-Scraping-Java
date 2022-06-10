package extractIdproj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.common.io.Files;
 

public class  append {

    public static void write(String filename, String text) {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        File file = new File(filename);

        try {
        	if (!file.exists())
 				file.createNewFile();
            fileWriter = new FileWriter(filename,true);
           

            bufWriter = new BufferedWriter(fileWriter);
            //InsÃ©rer un saut de ligne
            bufWriter.write(text);
            bufWriter.newLine();
            bufWriter.close();
          
            
 			// other operations
 			
        } catch (IOException ex) {
            Logger.getLogger(append.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(append.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
        public static void efface(String filename) throws IOException
        {
        	PrintWriter pw = new PrintWriter(new BufferedWriter
        			(new FileWriter(filename, false))); // >>>> on ajoutera après suppression de ce qui existait éventuellement
        			pw.print("");// ajout de la ligne vierge si print sans ln bien entendu
        			pw.close();
        }
        
        public static int nbrline(String filename) throws IOException
        {
        	 int nbrLine = 0;            
     	      // Créer l'objet File Reader
     	      FileReader fr = new FileReader(filename);
     	      // Créer l'objet BufferedReader 
     	      BufferedReader br = new BufferedReader(fr);  
     	      String str;
     	      // Lire le contenu du fichier
     	      while((str = br.readLine()) != null)
     	      {
     	         //Pour chaque ligne, incrémentez le nombre de lignes
     	         nbrLine++;               
     	            
     	      }
     	      return nbrLine;
        }
        
        public static void noRepeatCompagnie(String filename,List<String> listCompagnie,String codeAnnonce,String jsonContent) throws IOException
        {
        	
             List<String> chek=new ArrayList<String>();
        	int nbrL=append.nbrline(filename);
      	    File file = new File(filename);  
	        // initialize the scanner  
	        Scanner scan1 = new Scanner(filename);  
	        // iterate through the file line by line  
	         int a=0;
	         int ligne=0;
	        if(file.length()== 0)
	        {
	        	
        			append.write(filename, jsonContent);
	        }
	        
	        	else if(file.length()!=0) {
	        		boolean stop =false;
	        	 System.out.println(scan1.nextLine()); 
	        		while(scan1.hasNextLine() & !stop){  
			            // print the contents of a file by line  
			           
			            String delimiter="[,]";			            
			            String data=scan1.nextLine();
			            String [] codes =data.split(delimiter);
			            String code=codes[0];
			            chek.add(scan1.nextLine());
			            System.out.println(chek);
	        			
                  
	        		a=0;
	        		for(int j=0;j<listCompagnie.size();j++) {
	        			
	        			if(codeAnnonce==listCompagnie.get(j))
	        			{
	        				a=1;
	        			}
	 		        }
	        		if(a==0) {
	 		        	append.write(filename, jsonContent);
	        		}
	 		        
	        		 ligne++;
	        		 if(ligne==nbrL) {
	        			 stop=true;
	        		 }
	        		}
	        		}
	         scan1.close();
        }
        public static void noRepeat(String chemin,List<String> newList) throws IOException
        { 
        	
        	 File file = new File(chemin);  
		        // initialize the scanner  
		        Scanner scan1 = new Scanner(file);  
		        // iterate through the file line by line  
		          
		        if(file.length() == 0)
		        {
		        	for(String s:newList)
	        		{
	        			append.write(chemin, s);
	        			System.out.println("done");  
	        			
	        		}
		        }
		        
		        	else if(file.length()!=0) {
		        	
		        		int nbrL=append.nbrline(chemin);
		        		System.out.println("nbr de lignes");
		        		boolean stop=false;
		        		int ligneActu=0;
		        		 List<String> chek=new ArrayList<String>(); 
		        		 int a=0;

		        		 
		        		while(scan1.hasNextLine() & !stop){  
				            // print the contents of a file by line  
				            System.out.println(scan1.nextLine()); 
				            chek.add(scan1.nextLine());
				            System.out.println(chek);
		        		
		        			for(String s:newList) {
		        				for(int g=0;g<chek.size();g++)
		        				{
		        					if(chek.get(g).equals(s)) {
		        						a=1;
		        						break;
		        						}
		        					}
		        				if(a==0) {append.write(chemin, s);}
		        					
		        			 ligneActu=ligneActu+1;
                      
		        			 
		        			if(ligneActu==nbrL) {
		        				 stop=true;
		        			
		        		}
		        			 }
		        			}
		        		
		        		
		        	}
		        scan1.close();  
        }
     

        
    
 
    /**
     * Exemple
     * @param args 
     */
    public static void main(String[] args) {
        
    }
}