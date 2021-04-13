import java.util.Scanner;

import arete.Arete;
import graphe.Graphe;

public class Main {
		
	// Procédure de saisie d'un graphe non oriente pondere arete par arete 
	public static void saisie(Graphe g)
	{
		Scanner lire=new Scanner(System.in);
		
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
	}
	
	// Procédure d'affichage d'un graphe non oriente pondere arete par arete 
	public static void affichage(Graphe g) {
		for (int i = 0; i < g.getNbAretes(); i++)
			System.out.println("Arete n° "+ (i+1) + " : ("+g.getIndexArete(i).getOrigine() +" , "+g.getIndexArete(i).getDestination()+") poids : "+g.getIndexArete(i).getPoids());
	}
	
	public static void testKruskal() {
		
		//Graphe de départ g, graphe d'arrivée t
		Graphe g=new Graphe(), t=new Graphe();
		
		saisie(g);
		/*
		 * Exemple de saisie pour Kruskal :
		 * 
		 * Nombre de sommets : 6
		 * 
		 * Nombre d'aretes : 8
		 * 
		 * Arete n°1 : 
		 * 	extremite 1 : 1
		 * 	extremite 2 : 2
		 * 	poids : 7
		 * 
		 * Arete n°2 : 
		 * 	extremite 1 : 1
		 * 	extremite 2 : 3
		 * 	poids : 3
		 * 
		 * Arete n°3 : 
		 * 	extremite 1 : 1
		 * 	extremite 2 : 4
		 * 	poids : 4
		 * 
		 * Arete n°4 : 
		 * 	extremite 1 : 2
		 * 	extremite 2 : 4
		 * 	poids : 1
		 * 
		 * Arete n°5 : 
		 * 	extremite 1 : 3
		 * 	extremite 2 : 4
		 * 	poids : 4
		 * 
		 * Arete n°6 : 
		 * 	extremite 1 : 4
		 * 	extremite 2 : 5
		 * 	poids : 9
		 * 
		 * Arete n°7 : 
		 * 	extremite 1 : 4
		 * 	extremite 2 : 6
		 * 	poids : 9
		 * 
		 * Arete n°8 : 
		 * 	extremite 1 : 5
		 * 	extremite 2 : 6
		 * 	poids : 5
		 * 
		 */
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
		/*
		 * Affichage graphe d'arrivée t
		 * 
		 * Exemple de sortie attendue si saisie du graphe précédent :
		 * 
		 * Graphe d'arrivee t :
		 * Arête n°1 : (2,4) poids : 1
		 * Arête n°2 : (1,3) poids : 3
		 * Arête n°3 : (3,4) poids : 4
		 * Arête n°4 : (5,6) poids : 5
		 * Arête n°5 : (4,6) poids : 9
		 * 
		 */
		
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
		/*
		 * Affichage tableau pilch, prem et CFC :
		 * 
		 * Résultats attendus si saisie du graphe précédent :
		 * 
		 * Pilch : 0 3 1 2 4 5 
		 * Prem : 6 4 3 4 6 6 
		 * CFC : 1 1 1 1 1 1 
		 * 
		 */
	}

	
	public static void testCodagePrufer() {
		int[]P=null;
		
		/*
		 * On part d'une matrice d'adjacence adj pour tester le codage de Prufer 
		 * 
		 * adj[0][0]=nombre de sommets du graphe
		 * 
		 * Pour i allant de 1 à nombre de sommets :
		 * adj[i][0]=nombre de sommets relié au sommet i 
		 * par exemple adj[2][0]=4 donc le sommet 2 est relié à 4 sommets
		 * 
		 * Exemple pour la matrice d'adjacence adj :
		 *     0 1 2 3 4 5 6
		 * 
		 * 0   6 
		 * 1   1 0 1 0 0 0 0
		 * 2   4 1 0 1 1 1 0
		 * 3   1 0 1 0 0 0 0
		 * 4   1 0 1 0 0 0 0
		 * 5   2 0 1 0 0 0 1
		 * 6   1 0 0 0 0 1 0
		 * 
		 */
		int[][]adj=new int[7][7];
		for (int i=1;i<7;i++) {
			for (int j=1;j<7;j++) {
				adj[i][j]=0;
			}
		}
		adj[0][0]=6;
		
		adj[1][0]=1;
		adj[2][0]=4;
		adj[3][0]=1;
		adj[4][0]=1;
		adj[5][0]=2;
		adj[6][0]=1;

		
		adj[1][2]=1;
		adj[2][1]=1;
		adj[2][3]=1;
		adj[2][4]=1;
		adj[2][5]=1;
		adj[3][2]=1;
		adj[4][2]=1;
		adj[5][2]=1;
		adj[5][6]=1;
		adj[6][5]=1;
		
		Graphe g=new Graphe();
		g.codagePrufer(adj, P);
		
		/*
		 * Exemple de résultat attendu après application du codage de Prufer sur la matrice adj :
		 * (P[0]=n-2, où n est le nombre de sommets)
		 * 
		 * P={4,2,2,2,5}
		 */
	}
	
	public static void testDecodagePrufer() {
		/*
		 * On part de la suite de Prufer P pour trouver le graphe correspondant 
		 * 
		 * On utilise un tableau d'occurence b pour savoir si un element de I est dans P et combien de fois
		 * On utilise un tableau I qui représente les sommets du graphe
		 * (I[0] vaut 0, I[i] vaut 1 si le sommet i est présent dans le futur graphe)
		 * 
		 * Exemple 1 : 
		 * 
		 * Saisie pour la suite de Prufer P :
		 * (P[0]=n-2, où n est le nombre de sommets)
		 * P= {4,2,2,2,5}
		 * 
		 * Résultat attendu après application du décodage de Prufer sur la suite de Prufer P  :
		 * (ici le sommet 2 est relié aux sommets 1, 3, 4 et 5 et le sommet 5 est relié au sommet 6)
		 * (2,1)(2,3)(2,4)(5,2)(5,6)
		 * 
		 * Exemple 2 :
		 * 
		 * Saisie pour la suite de Prufer P :
		 * (P[0]=n-2, où n est le nombre de sommets)
		 * P= {7,2,2,2,5,6,5,7}
		 * 
		 * Résultat attendu après application du décodage de Prufer sur la suite de Prufer P  :
		 * (2,1)(2,3)(2,4)(5,2)(6,8)(5,6)(7,5)(7,9)
		 *
		 */
		int[]P= {4,2,2,2,5};
		//int[]P= {7,2,2,2,5,6,5,7};
		int[]I= null;
		int[]b = null;
		Graphe g=new Graphe();
		g.decodagePrufer(P, I, b);
	}
	
	public static void testTarjan() {
		Graphe g=new Graphe(8);
		
		/*
		 * Ici il faudrait générer un tableau fs et un tableau aps de ce graphe :
		 * 
		 * graphe(8,true, false)
		 * A(1,2) 
		 * A(1,3)
		 * A(1,4)
		 * A(2,5)
		 * A(5,1)
		 * A(5,6)
		 * A(3,6)
		 * A(6,3)
		 * A(6,4)
		 * A(6,7)
		 * A(6,8)
		 * A(4,7)
		 * A(7,4)
		 * A(8,7)
		 * 
		 * Puis assigner les tableaux fs et aps obtenus au graphe g comme cela :
		 * 
		 * g.setFs(fs);
		 * g.setAps(aps);
		 * 
		 * Ensuite il ne reste plus qu'à écrire pour afficher les tableaux prem pilch et cfc ainsi que les fs et aps du graphe reduit :
		 * 
		 * g.tarjan();
		 * g.graphe_reduit();
		 */
	}

	public static void main(String[] args) {
		//testCodagePrufer();
		//testDecodagePrufer();
		//testKruskal();
		//testTarjan();
	}
}
