package test1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
 

public class myClass {
	public static void main(String[] args) throws IOException
	{
		System.out.println("Bienvenue dans notre Programme de graphes !");
		System.out.println("Veuillez choisir si vous voulez une saisie manuelle (Kruskal uniquement) ou par notre fichier de données donnees.txt");
		
		
		Scanner lire=new Scanner(System.in);
		 System.out.println("Tapez 1 pour le fichier de données");
	       System.out.println("Tapez 2 pour la saisie manuelle (Kruskal uniquement)");
	        int choix=lire.nextInt();
		
	     if(choix == 1)
	     {
	    	 
	     
	
		
			
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
			
			
			
			Scanner lire2=new Scanner(System.in);
			
		    
			
		    
		    boolean continuer = true;
		    while(continuer)
		    {
		    	System.out.println("");
		    	System.out.println("");
		    	System.out.println("");
		    	System.out.println("");
				System.out.println("Tapez le numéro correspondant à l'algorithme choisi:");
			    System.out.println("1: Distance");
			    System.out.println("2: Rang");
			    System.out.println("3: Tarjan");
			    System.out.println("4: Ordonnancement");
			    System.out.println("5: Kruskal");
			    System.out.println("6: Prüfer");
			    System.out.println("7: Quitter le programme");
			    
		    	int choix2=lire2.nextInt();
			    switch(choix2)
			    {
			    case 1:
			    	
			    	
			    	monGraphe.afficherMatricedistance();
			    	
			    	
			    	break;
			    	
			    case 2: 
			    	
			    	
			    	
			    	System.out.println("");
					System.out.println("Algorithme du rang:");
					
				
					System.out.println();
					
					System.out.println();
					monGraphe.afficheRang(monGraphe.rang());
			    	break;
			    
			    case 3: 
			    	monGraphe.tarjan();
					monGraphe.graphe_reduit();
			    	break;
			    case 4: 
			    	
			    	monGraphe.fs_aps2fp_app();
				    
					   
				    int[] D = {0,6,4,5,4,9,7,8,2,5,2,7,6,5,7,0};

				    monGraphe.ordonnancement(D);
			    	break;
			    case 5: 
			    	
			    	
			    	

					Graphe t=new Graphe();
					
					int n = monGraphe.getNbSommets();
					int []prem = new int[n + 1];
					int []pilch = new int[n + 1];
					int []cfc = new int[n + 1];
					int []NbElem = new int[n + 1];
					for(int i=1; i<=n;i++)
					{
						prem[i] = i;
						pilch[i] = 0;
						cfc[i] = i;
						NbElem[i] = 1;
					}
					System.out.println("");
					System.out.println("");
					System.out.println("");
					//Affichage graphe de départ g 
					System.out.println("Kruskal : ");
					System.out.println("Graphe de départ g : ");
					affichage(monGraphe);
					System.out.println();
					
					//tri du graphe g
					monGraphe.trier(monGraphe);
					
					//Affichage graphe de départ g trié 
					System.out.println("Graphe de départ g trié : ");
					affichage(monGraphe);
					System.out.println();
					
					//Application de l'algorithme de kruskal 
					 monGraphe.kruskal(monGraphe, t, prem, pilch, cfc,NbElem);
					
					System.out.println("Graphe d'arrivee t : ");
					affichage(t);
					System.out.println();
					
					
					System.out.print("Pilch : ");
					for (int i = 1; i < pilch.length; i++) {
						System.out.print(pilch[i] + " ");
					}
					System.out.println();

					System.out.print("Prem : ");
					for (int i = 1; i < prem.length; i++) {
						System.out.print(prem[i] + " ");
					}
					System.out.println();
					
					System.out.print("CFC : ");
					for (int i = 1; i < cfc.length; i++) {
						System.out.print(cfc[i] + " ");
					}
			    	
			    	
			    	
			    	break;
			    	
			    	
			    	
			    case 6: 
			    	
			    	
			    	

					int[]P=null;
					monGraphe.codagePrufer(P);
			    	break;
			  
			    	
			    case 7:
			    	continuer = false;
			    	System.out.println("");
			    	System.out.println("Programme correctement quitté !");
			    	break;
			    	default:
			    		
			    }
		    }
			
		    
			
			
			
			
			
			
			

		    
			
			
			
			
			
			
			
			
		    
	
	    }
		
	     else if(choix == 2)
	     {
	    	 Graphe g = new Graphe();
	    	 
	    	 int origine, destination;
	 		
	 		System.out.println("Saisie d'un graphe non oriente pondere arete par arete ");
	 		System.out.println();
	 		System.out.print("Donnez le nombre de sommets : ");
	 		g.setNbSommets(lire.nextInt());
	 		
	 		do
	 		{
	 			System.out.print("Donnez le nombre d'aretes : ");
	 			g.setNbAretes(lire.nextInt());
	 		} while (g.getNbAretes() < g.getNbSommets());
	 		g.setAretes(new Arete[g.getNbAretes()]);

	 		System.out.println("Saisissez les "+ g.getNbAretes() + " aretes");
	 		for (int i = 0; i < g.getNbAretes(); i++)
	 		{
	 			Arete ar = new Arete();
	 			System.out.println("arete n° "+ (i+1) + " :");
	 			do
	 			{
	 				System.out.print("extremite 1 (entre 1 et " + g.getNbSommets() + " ) : ");
	 				origine=lire.nextInt();
	 				ar.setOrigine(origine);
	 			} while ((origine < 1) && (origine > g.getNbSommets()));
	 			do
	 			{
	 				System.out.print("extremite 2 (entre 1 et "+ g.getNbSommets() +" ) : ");
	 				destination=lire.nextInt();
	 				ar.setDestination(destination);
	 			}while ((destination < 1) && (destination > g.getNbSommets()));
	 			System.out.print("Poids de l'arete : ");
	 			ar.setPoids(lire.nextInt());
	 			g.setIndexArete(ar, i);
	 		}
	 		
	 		
	 		
	 		
	 		
	 		
	 		Graphe t=new Graphe();
	
			int n = g.getNbSommets();
			int []prem = new int[n + 1];
			int []pilch = new int[n + 1];
			int []cfc = new int[n + 1];
			int []NbElem = new int[n + 1];
			for(int i=1; i<=n;i++)
			{
				prem[i] = i;
				pilch[i] = 0;
				cfc[i] = i;
				NbElem[i] = 1;
			}
			System.out.println();
			//Affichage graphe de départ g 
			System.out.println("Graphe de départ g : ");
			affichage(g);
			System.out.println();
			
			//tri du graphe g
			g.trier(g);
			
			//Affichage graphe de départ g trié 
			System.out.println("Graphe de départ g trié : ");
			affichage(g);
			System.out.println();
			
			//Application de l'algorithme de kruskal 
			g.kruskal(g, t, prem, pilch, cfc,NbElem);
			
			System.out.println("Graphe d'arrivee t : ");
			affichage(t);
			System.out.println();
			
			System.out.print("Pilch : ");
			for (int i = 1; i < pilch.length; i++) {
				System.out.print(pilch[i] + " ");
			}
			System.out.println();

			System.out.print("Prem : ");
			for (int i = 1; i < prem.length; i++) {
				System.out.print(prem[i] + " ");
			}
			System.out.println();
			
			System.out.print("CFC : ");
			for (int i = 1; i < cfc.length; i++) {
				System.out.print(cfc[i] + " ");
			}
			
	 		
	 		
	 		
	     }
		
	
		
		
		
	}
	
	
	public static void affichage(Graphe g) {
		for (int i = 0; i < g.getNbAretes(); i++)
			System.out.println("Arete n° "+ (i+1) + " : ("+g.getIndexArete(i).getOrigine() +" , "+g.getIndexArete(i).getDestination()+") poids : "+g.getIndexArete(i).getPoids());
	}
}
