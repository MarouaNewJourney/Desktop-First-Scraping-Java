package ML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.util.Random;

import extractIdproj.ScraperJava;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;

import weka.core.Instances;

public class Cluster {

	public static void Clusteringstart() throws Exception {
		String chemin=DataWriter.chemin;
		Instances data;
		try {
			data = new Instances(new BufferedReader(new FileReader(chemin)));
			EM model = new EM();
			// build the clusterer
			model.buildClusterer(data);
			System.out.println(model);
			
			double logLikelihood = ClusterEvaluation.crossValidateModel(model,data, 10, new Random(1));
			System.out.println(logLikelihood);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
