package test1;



public class Graphe {

	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;
	
	private int[] fs;
	private int[] aps;

	public Graphe(int nbSommets,  Arete [] aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
	}

	public Graphe() {
		this(0, null);
	}
	
	public Graphe(int[][] matriceAdjacence) // mon constructeur utile
	{
		this.matriceAdjacence = matriceAdjacence;
		
		matAdjVersFsAps();
	}
	
	public String [] getNomsSommets() {
		return nomsSommets;
	}
	public int getNbSommets() {
		return nbSommets;
	}
	

	public  Arete [] getAretes() {
		return aretes;
	}

	public int [][] getMatriceAdjacence() {
		return matriceAdjacence;
	}
	
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}

	public void setAretes(Arete [] aretes) {
		this.aretes = aretes;
	}

	public void setMatriceAdjacence(int [][] matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}

	public void setNomsSommets(String [] nomsSommets) {
		this.nomsSommets = nomsSommets;
	}
	
	
	
	public int[][] fsApsVersMatAdj(int[] fs, int aps[]) //méthode plus globale disponible au besoin, à intégrer dans son constructeur + matAdj calculée
	{
		int nombreSommets =aps[0];
		int nombreSuccesseurs = fs[0] -nombreSommets;
		
		int[][] matAdj = new int[nombreSommets+1][nombreSommets+1];
		matAdj[0][0] = nombreSommets;
		matAdj[0][1] = nombreSuccesseurs;
		
		for(int i =1; i <=nombreSommets; i++)
		{
			for(int j = 1; j <=nombreSommets;j++)
			{
				matAdj[i][j] =0;
			}
		}
		
		for(int i = 0; i <= nombreSommets;i++)
		{			
		
		    for(int k=aps[i] ;fs[k] !=0 ;k++)
		    {
		        matAdj[i][fs[k]]=1 ;
		    }
		}
		
		return matAdj;
	}
	
	public void matAdjVersFsAps()
	{
		int nombreSommets = matriceAdjacence[0][0];		
		int nombreSuccesseurs = matriceAdjacence[0][1];
		
		aps = new int[nombreSommets+1];
		aps[0] = nombreSommets;
		
		fs = new int[nombreSommets+nombreSuccesseurs+1];
		fs[0] = nombreSommets+nombreSuccesseurs;int k = 1;
		
		for (int i = 1; i <= nombreSommets; i++)
		{
			aps[i] = k;
			
			for (int j = 1; j <= nombreSommets; j++)
			{
				if (matriceAdjacence[i][j] != 0)
				{
					fs[k++] = j;
					
				}
			}
			fs[k++] = 0;
		}
	}
	
	
	public void afficherMatAdj()
	{
		int n =matriceAdjacence[0][0];
		
		System.out.println("");
		System.out.println("Matrice d'adjacence:");
		System.out.println("");
		
		for(int i = 1; i <=n; i++)
		{
			for(int j = 1; j <=n; j++)
			{
				System.out.print(matriceAdjacence[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void afficherFs()
	{
		System.out.println("");
		System.out.println("");
		System.out.println("fs:");
		System.out.println("");
		int n = fs[0];
		for(int i =1; i<=n; i++)
		{
			System.out.print(fs[i]);
			System.out.print("-");
		}
		
	}
	
	public void afficherAps()
	{
		System.out.println("");
		System.out.println("");
		System.out.println("aps:");
		System.out.println("");
		int n = aps[0];
		for(int i =1; i<=n; i++)
		{
			System.out.print(aps[i]);
			System.out.print("-");
		}
	}

	
	
	
	
	
	public void afficherMatricedistance()
	{

		System.out.println("");
		System.out.println("");
		System.out.println("Matrice des distances :");
		
		for(int j = 1; j <= aps[0];j++)
		{
			int[] r = parcours_largeur(j);
			System.out.println("");
			
			for(int i = 1; i <= r[0]; i++)
			{
				System.out.print(r[i]+"|");
			}
		}
	}
				
	
	public int[] parcours_largeur(int depart) 
	{ 
		int nombreSommets = aps[0]; 
		int a = 0, b = 1, c = 0, fin, inter, inter2, inter3; 
		int[] fil = new int[nombreSommets+1];
		fil[0] = nombreSommets; 
		int[] dist = new int[nombreSommets+1]; 
		dist[0] = nombreSommets; 
		fil[1] = depart;
		
		for (int h = 1; h <= nombreSommets; h++) 
		{
				dist[h] = -1; 
				dist[depart] = 0;
		}
			
		while ( a < b )     
		{
			c++; 
			fin = b; 
			while (a < fin)   
			{
				a++; 
				inter = fil[a]; 
				inter3 = aps[inter]; 
				inter2 = fs[inter3];
				while (inter2 > 0)    
				{ 
					if (dist[inter2] == -1) 
					{ 
						b++; 
						fil[b] = inter2;  
						dist[inter2] = c;      
					}
					inter2 = fs[++inter3];
				}
			}
			
		}
		return dist;
	}

}
