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


public class Apriori {

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
		
		Apriori model = new Apriori();
		model.buildAssociations(data);
		System.out.println(model);
		
	}

}
