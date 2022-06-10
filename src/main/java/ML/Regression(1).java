package ML;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

import controller.BD;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.M5P;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 * This class is used to explain the Linear Regression with Java.
 * 
 * @author Gowtham Girithar Srirangasamy
 *
 */
public class Regression {
	
	/** file names are defined*/
	public static char  separator=File.separatorChar;
	public static String cheminLocal=".."+separator+"extractIdproj"+separator+"src"+separator+"main"+separator+"java"+separator;
	public static String TRAINING_DATA_SET_FILENAME=cheminLocal+"data"+separator+"reg.arff";
	public static  String TESTING_DATA_SET_FILENAME=cheminLocal+"data"+separator+"train.arff";
	public static  String PREDICTION_DATA_SET_FILENAME=cheminLocal+"data"+separator+"dataSet.arff";

	/**
	 * This method is to load the data set.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	
	public static Instances getDataSet(String fileName) throws IOException {
		
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 1;
		/** the arffloader to load the arff file */
		Instances dataSet = new Instances(new BufferedReader(new FileReader(fileName)));
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	public static void process() throws Exception {

		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		/** Classifier here is Linear Regression */
		//Classifier classifier = new weka.classifiers.functions.LinearRegression();
		/** */
		//classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		LinearRegression model = new LinearRegression();
		model.buildClassifier(trainingDataSet);
		System.out.println(model);

		Evaluation eval = new Evaluation(trainingDataSet);
		/*eval.evaluateModel(classifier, testingDataSet);
		/** Print the algorithm summary */
		/*System.out.println("** Linear Regression Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);

		Instance predicationDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME).lastInstance();
		double value = classifier.classifyInstance(predicationDataSet);
		/** Prediction Output */
		
		eval.crossValidateModel(model, trainingDataSet, 10, new Random(1), new String[] {});
		System.out.println(eval.toSummaryString());
		double coef[] = model.coefficients();
		System.out.println();
		M5P md5 = new M5P();
		md5.setOptions(new String[] { "" });
		md5.buildClassifier(trainingDataSet);
		System.out.println(md5);
		eval.crossValidateModel(md5, trainingDataSet, 10, new Random(1), new String[] {});
		System.out.println(eval.toSummaryString());
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

				Regression.process();
				
			}
			
			
	
}
