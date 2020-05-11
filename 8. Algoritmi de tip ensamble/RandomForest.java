import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.Utils;


public class RandomForest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
		BufferedReader reader = null, readerT=null;
		try {
			reader = new BufferedReader(new FileReader("train.arff"));
			readerT = new BufferedReader(new FileReader("test.arff"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instances data = null, test = null;
		try {
			 data = new Instances(reader);
			 test = new Instances(readerT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
			readerT.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		data.setClassIndex(data.numAttributes()-1);
		test.setClassIndex(test.numAttributes()-1);
		Enumeration<weka.core.Attribute> enumAtr = data.enumerateAttributes();
		System.out.println("Atributele sunt:  ");
		while(enumAtr.hasMoreElements())
			System.out.println(enumAtr.nextElement());
		
		RandomForest lnr = new RandomForest();
		
		try {
			lnr.buildClassifier(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(lnr.toString());
		
		Evaluation eval = null;
		try {
			eval = new Evaluation(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			eval.evaluateModel(lnr, test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(eval.toSummaryString("\nResults\n======\n", true));
		
	
	}

}
