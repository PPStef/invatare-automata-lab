import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.Utils;


public class Regression {

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
		
		LinearRegression lnr = new LinearRegression();
		
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
		
		 System.out.println("# \t-\t actual \t-\t predicted \t-\t error \t-\t distribution \t-\t diff");
		 double diff=0;
		 double	 diffT=0;
		 double	 markSum=0;
		 double	 predSum=0;
		 double distributedErr=0;
		    for (int i = 0; i < test.numInstances(); i++) {
		      double pred = lnr.classifyInstance(test.instance(i));
		      double[] dist = lnr.distributionForInstance(test.instance(i));
		      System.out.print((i+1));
		      
		      System.out.print(" \t- \t");
		     
		      System.out.print(test.instance(i).toString(test.classIndex()));
		      
		      System.out.print(" \t- \t");
		     
		    
		      System.out.print(pred);
		      System.out.print(" \t\t- ");
		     
		      if (pred != test.instance(i).classValue()){
		    System.out.print("\tyes");
		      }
		      else{
		    System.out.print("\tno");
		   
		      }
		      System.out.print(" \t- \t");
		     
		     System.out.print(Utils.arrayToString(dist));
		     System.out.print(" \t- \t");
		     double actual= Double.parseDouble(test.instance(i).toString(test.classIndex()));
		     markSum+=actual;
		     predSum+=pred;
		     if (actual>pred)
		    	 diff= actual - pred;
		     else
		    	 diff=pred-actual;
		     System.out.println(diff);
		      System.out.println();
		  diffT=diffT+diff;
		    }
		    distributedErr = diffT/test.numInstances();
	}

}
