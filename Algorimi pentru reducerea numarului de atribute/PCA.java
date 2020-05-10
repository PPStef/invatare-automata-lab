import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.PrincipalComponents;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.Utils;


public class PCA {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
		BufferedReader reader = null, readerT=null;
		try {
			reader = new BufferedReader(new FileReader("cpu.arff"));
			readerT = new BufferedReader(new FileReader("cpu.arff"));
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
		
		 // Performs a principal components analysis.
	      PrincipalComponents pcaEvaluator = new PrincipalComponents();

	      // Sets the amount of variance to account for when retaining principal
	      // components.
	      pcaEvaluator.setVarianceCovered(1.0);
	      // Sets maximum number of attributes to include in transformed attribute
	      // names.
	      pcaEvaluator.setMaximumAttributeNames(-1);

	      // Scaled X such that the variance of each feature is 1.
	     
	        pcaEvaluator.setCenterData(true);
	      

	      // Ranking the attributes.
	      Ranker ranker = new Ranker();
	      // Specify the number of attributes to select from the ranked list.
	      ranker.setNumToSelect(1);

	      AttributeSelection selector = new AttributeSelection();
	      selector.setSearch(ranker);
	      selector.setEvaluator(pcaEvaluator);
	      selector.SelectAttributes(data);

	      // Transform data into eigenvector basis.
	      Instances transformedData = selector.reduceDimensionality(data);
	      
	      Enumeration<weka.core.Attribute> enumAtrNew = transformedData.enumerateAttributes();
			System.out.println("Atributele sunt:  ");
			while(enumAtrNew.hasMoreElements())
				System.out.println(enumAtrNew.nextElement());

	
	}

}
