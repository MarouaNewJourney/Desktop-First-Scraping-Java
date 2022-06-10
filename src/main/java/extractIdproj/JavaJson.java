package extractIdproj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.nodes.Document;

public class JavaJson {
     public static boolean writeJson(String chemin,String content){
    	
 		
 		//String jsonContent = "\"developers\":[{\"firstName\": \"Hack\",\"lastName\": \"Track\"},{\"firstName\": \"ad\",\"lastName\": \"il\"}]";
 		File file = new File(chemin);

 		try {
 			if (!file.exists())
 				file.createNewFile();
 			FileWriter writer = new FileWriter(file);
 			writer.write(content);
 			writer.flush();
 			writer.close();
 			// other operations
 			writer.close();
 			return true;
 		} catch (IOException e) {
 			System.out.println("Erreur: impossible de créer le fichier '"
 					+ chemin + "'");
 			return false;
 		}
 	}

     
     
	public static void main(String[] args) {
		
		}}
