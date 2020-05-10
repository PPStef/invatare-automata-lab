import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.Utils;


public class Clustering {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
				
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("cpu.arff"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instances data = null, test = null;
		try {
			 data = new Instances(reader);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			SimpleKMeans kmeans = new SimpleKMeans();
	        kmeans.setNumClusters(3);
	        kmeans.setMaxIterations(10);
	        kmeans.setPreserveInstancesOrder(true);
		
		try {  
	            kmeans.buildClusterer(data);
	        } catch (Exception ex) {
	            System.err.println("Unable to buld Clusterer: " + ex.getMessage());
	            ex.printStackTrace();
	        }

	        // print out the cluster centroids
	        Instances centroids = kmeans.getClusterCentroids();
	        for (int i = 0; i < 3; i++) {
	            System.out.print("Cluster " + i + " size: " + kmeans.getClusterSizes()[i]);
	            System.out.println(" Centroid: " + centroids.instance(i));
	        }
	
	}

}
