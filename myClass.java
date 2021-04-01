package test1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.regex.*;
 

public class myClass {
	public static void main(String[] args) throws IOException
	{
		
		
		Graphe test = new Graphe();
	
		int[] fs= {13,2,3,4,0,2,3,4,0,5,0,0,4,0}, aps = {5,1,5,9,11,12};
		
		int[][] matAdj;
		

		matAdj = test.fsApsVersMatAdj(fs, aps);
		
		test.afficherMatAdj(matAdj);
		
		
		test.setMatriceAdjacence(matAdj);
		test.matAdjVersFsAps();
		
		test.afficherFs();
		test.afficherAps();
		
		
		
		
		System.out.println("");
		System.out.println("");
		
		
		
		BufferedReader in = new BufferedReader(new FileReader("D:/Graphes/donnees.txt"));
		String line;
		while ((line = in.readLine()) != null)
		{
		
			Pattern pattern1;
		    Matcher matcher1;
	    
		    pattern1 = Pattern.compile("sommet *P *(-*[1-9]+)");
		    matcher1 = pattern1.matcher(line);
		  
		    
		    while(matcher1.find()) {
		        System.out.println(matcher1.group(1));
	
		    }
		}
		
			
		
		
		in.close();
		
		

	    
		
	}
	
	
}
