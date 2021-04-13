package test1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.regex.*;
 

public class myClass {
	public static void main(String[] args) throws IOException
	{
		//début de mon code de lecture de données
		
		
		/*
		Graphe test = new Graphe();
	   
		int[] fs= {13,1,3,5,0,2,3,4,0,5,0,0,4,0}, aps = {5,1,5,9,11,12};
		//int[] fs= {12,1,2,3,0,1,2,3,0,1,2,3,0}, aps = {3,1,5,9};	
		int[][] matAdj;
		matAdj = test.fsApsVersMatAdj(fs, aps);
		
		*/
		
	
		
		
	
	
		
		
		boolean oriente = false;
		boolean pondere = false;
		int nombreSommets = 0;
		int nombreSuccesseurs = 0;
		
		int[][] matriceAdj = new int[100][100];
		
		String[] nomSommets = new String[100];
		
		
		BufferedReader in = new BufferedReader(new FileReader("donnees.txt"));
		String line;
		while ((line = in.readLine()) != null)
		{
			
			//remplacement caractères spéciaux, pour que les regex fonctionnent
			line = line.replace('(','P');
			line = line.replace(')','Q');
			line = line.replace(',','V');
			
			

			//enlèvement des commentaires
			Pattern patternSlash;
		    Matcher matcherSlash;
		    patternSlash = Pattern.compile("(.*)//");
		    matcherSlash = patternSlash.matcher(line);
		  
		    
		    while(matcherSlash.find()) {
		        
		    	line = matcherSlash.group(1);
	
		    }
		    
		  
			

			
			Pattern pattern1;
		    Matcher matcher1;
	    
		    pattern1 = Pattern.compile("graphe *P *(\\d+) *V *(\\w+) *V *(\\w+) *Q");
		    matcher1 = pattern1.matcher(line);
		  
		    
		    while(matcher1.find()) 
		    {
		  
		        nombreSommets = Integer.parseInt(matcher1.group(1));
			        
		        
		        if(matcher1.group(2).toString().equals("true"))
		        {
		        	oriente = true;
		        	
		        }
		     
		    		   	           	        
		        if(matcher1.group(3).toString().equals("true"))
		        {
		        	pondere = true;
		    	        	
		        }		
		        

		        matriceAdj[0][0] = nombreSommets;
		        
		        
		        for(int i =1; i <=nombreSommets; i++)
				{
					for(int j = 1; j <=nombreSommets;j++)
					{
						matriceAdj[i][j] = 0;
					}
				}		
		        
		       
		    }
		            
		    
		    
		    Pattern pattern2;
		    Matcher matcher2;
	    
		    pattern2 = Pattern.compile("A *P *(\\d+) *V *(\\d+) *Q");
		    matcher2 = pattern2.matcher(line);
		    
		    
		    while(matcher2.find()) 
		    {
		    	
		    	
		        int origine =Integer.parseInt(matcher2.group(1)) ;
		        int destination = Integer.parseInt(matcher2.group(2));
		    	
		        matriceAdj[origine][destination] = 1;
		        nombreSuccesseurs++;
		        
		        if(oriente == false)
		        {
		        	matriceAdj[destination][origine] = 1;
		        	nombreSuccesseurs++;
		        }
		    }
		    
		 
		    
		    
		    
		    Pattern pattern3;
		    Matcher matcher3;
	        
		    pattern3 = Pattern.compile("A *P *(\\d+) *V *(\\d+) *V *(\\d+) *Q");
		    matcher3 = pattern3.matcher(line);
		    
		    
		    while(matcher3.find()) 
		    {
		    	int inter;
		    	
		    	if(pondere == true)
		    	{
		    	    inter = Integer.parseInt(matcher3.group(3));
		    	}
		    	else
		    	{
		    		inter = 1;
		    		
		    	}
		    	
		    	
		    	int origine =Integer.parseInt(matcher3.group(1)) ;
		        int destination = Integer.parseInt(matcher3.group(2));
		    	
		        matriceAdj[origine][destination] = inter;
		        nombreSuccesseurs++;
		        
		        if(oriente == false)
		        {
		        	matriceAdj[destination][origine] = inter;
		        	nombreSuccesseurs++;
		        }
		    }
		    
		    
		    
		    
		    Pattern pattern4;
		    Matcher matcher4;
	    
		    pattern4 = Pattern.compile("S *P *(\\d+) *V *(\\w+) *Q");
		    matcher4 = pattern4.matcher(line);
		  
		    
		    while(matcher4.find()) 
		    {
		    	nomSommets[Integer.parseInt(matcher4.group(1))] =matcher4.group(2);
		    	
		    }
		
		    
		    
		}	
		in.close();
		
		matriceAdj[0][1] = nombreSuccesseurs;
				
		
		//opération de sécurité/optimisation: réduire la matrice à sa taille utile
		int[][] matriceInter = new int[nombreSommets+1][nombreSommets+1];
		
		for(int i =0;i<= nombreSommets; i++)
		{
			for(int j =0;j<= nombreSommets; j++)
			{
				matriceInter[i][j] = matriceAdj[i][j];
			}
		}
		
		
		//de même pour le tableau des noms
		String[] nomSommetsInter = new String[nombreSommets+1];
		
		for(int i = 1; i <= nombreSommets; i++)
		{
			nomSommetsInter[i] = nomSommets[i];
			
			
		}
		
		
		
	
		Graphe monGraphe = new Graphe(matriceInter);
		monGraphe.setNomsSommets(nomSommetsInter);
		monGraphe.afficherMatAdj();
		monGraphe.afficherFs();
		monGraphe.afficherAps();
		monGraphe.afficherMatricedistance();
		
		//fin de mon code de lecture de données
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
