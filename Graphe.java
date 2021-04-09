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
	
	
	
	public int[][] fsApsVersMatAdj(int[] fs, int aps[])
	{
		int n =aps[0];
		int m = fs[0] -n;
		
		int[][] matAdj = new int[n+1][n+1];
		matAdj[0][0] = n;
		matAdj[0][1] = m;
		
		for(int i =1; i <=n; i++)
		{
			for(int j = 1; j <=n;j++)
			{
				matAdj[i][j] =0;
			}
		}
		
		for(int i = 0; i <= n;i++)
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
		int n = matriceAdjacence[0][0];		
		int m = matriceAdjacence[0][1];
		
		aps = new int[n+1];
		aps[0] = n;
		
		fs = new int[n+m+1];
		fs[0] = n+m;int k = 1;
		
		for (int i = 1; i <= n; i++)
		{
			aps[i] = k;
			
			for (int j = 1; j <= n; j++)
			{
				if (matriceAdjacence[i][j] != 0)
				{
					fs[k++] = j;
					
				}
			}
			fs[k++] = 0;
		}
	}
	
	
	public void afficherMatAdj(int[][] matAdj)
	{
		int n = matAdj[0][0];
		
		System.out.println("");
		System.out.println("Matrice d'adjacence:");
		System.out.println("");
		
		for(int i = 1; i <=n; i++)
		{
			for(int j = 1; j <=n; j++)
			{
				System.out.print(matAdj[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void afficherFs()
	{
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
		System.out.println("aps:");
		System.out.println("");
		int n = aps[0];
		for(int i =1; i<=n; i++)
		{
			System.out.print(aps[i]);
			System.out.print("-");
		}
	}

	
	
	
	
	
	
	
	
	public int[] desc_largeur(int r) 
	{ 
		int nb_som = aps[0]; 
		int i = 0, j = 1, k = 0, ifin, s, t, it; 
		int[] fil = new int[nb_som+1];
		fil[0] = nb_som; 
		int[] dist = new int[nb_som+1]; 
		dist[0] = nb_som; 
		fil[1] = r;
		
		for (int h = 1; h <= nb_som; h++) 
		{
				dist[h] = -1; 
				dist[r] = 0;
		}
			
		while ( i < j )     
		{
			k++; 
			ifin = j; 
			while (i < ifin)   
			{
				i++; s = fil[i]; 
				it = aps[s]; 
				t = fs[it];
				while (t > 0)    
				{ 
					if (dist[t] == -1) 
					{ 
						j++; 
						fil[j] = t;  
						dist[t] = k;      
					}
					t = fs[++it];
				}
			}
			
		}
		return dist;
	}

}
