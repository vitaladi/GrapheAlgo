package test1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.regex.*;
 

public class myClass {
	public static void main(String[] args) throws IOException
	{
		
		
		Graphe test = new Graphe();
	
		int[] fs= {13,1,3,5,0,2,3,4,0,5,0,0,4,0}, aps = {5,1,5,9,11,12};
		//int[] fs= {12,1,2,3,0,1,2,3,0,1,2,3,0}, aps = {3,1,5,9};
		
		int[][] matAdj;
		

		matAdj = test.fsApsVersMatAdj(fs, aps);
		
		test.afficherMatAdj(matAdj);
		
		
		test.setMatriceAdjacence(matAdj);
		test.matAdjVersFsAps();
		
		test.afficherFs();
		System.out.println("");
		test.afficherAps();
		
		
		
		
		System.out.println("");
		System.out.println("");
		
		
		
		System.out.println("Matrice des distances :");
		
		
		
		for(int j = 1; j <= aps[0];j++)
		{
			int[] r = test.desc_largeur(j);
			System.out.println("");
			
			for(int i = 1; i <= r[0]; i++)
			{
				System.out.print(r[i]+"|");
			}
		}
		
		
		/*
		
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
	
		*/
		
		
	}
	
	
}
