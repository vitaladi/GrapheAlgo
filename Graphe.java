package graphe;

import arete.Arete;

public class Graphe {

	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;
	
	private int[] fs;
	private int[] aps;

	public Graphe(int nbSommets,  Arete [] aretes)
	{
		this.nbSommets = nbSommets;
		this.aretes = aretes;
	}
	
	public Graphe(int fs[], int aps[])
	{
		this.fs=fs;
		this.aps=aps;
		this.nbSommets = aps[0];
	}
	
	public Graphe(int [][] matrice)
	{
		this.matriceAdjacence=matrice;
	}

	public Graphe()
	{
		this(0, null);
	}
	
	public String [] getNomsSommets()
	{
		return nomsSommets;
	}
	
	public int getNbSommets()
	{
		return nbSommets;
	}
	

	public  Arete [] getAretes()
	{
		return aretes;
	}

	public int [][] getMatriceAdjacence() 
	{
		return matriceAdjacence;
	}

	public void setNbSommets(int nbSommets)
	{
		this.nbSommets = nbSommets;
	}

	public void setAretes(Arete [] aretes)
	{
		this.aretes = aretes;
	}

	public void setMatriceAdjacence(int [][] matriceAdjacence)
	{
		this.matriceAdjacence = matriceAdjacence;
	}

	public void setNomsSommets(String [] nomsSommets)
	{
		this.nomsSommets = nomsSommets;
	}
	
	public void empiler(int s, int [] pilch)
	{
		pilch[s] = pilch[0];
		pilch[0] = s;
	}
	
	public void afficherFs()
	{
		System.out.print("FS: ");
		int n = fs[0];
		for(int i =1; i<=n; i++)
		{
			System.out.print(fs[i]);
			System.out.print("-");
		}
	}
	
	public void afficherAps()
	{
		System.out.print("APS: ");
		int n = aps[0];
		for(int i =1; i<=n; i++)
		{
			System.out.print(aps[i]);
			System.out.print("-");
		}
	}
	
	
	public int[] rang(int[] fs, int[] aps)
	{
	    int n = aps[0], taillefs = fs[0], s, k, h, t ;
	    int []rang = new int[n+1];
	    int[] ddi = new int[n+1];
	    int[] pilch = new int[n+1];
	    int[] prem = new int[n+1];
	    
	    for(int i=1 ; i<=n ; ++i)
	    {
	        ddi[i] = 0 ;
	    }
	    
	    // Calcul du ddi
	    for(int i=1 ; i<=taillefs ; ++i)
	    {
	        s = fs[i];
	        if(s > 0)
	        {
	            ++ddi[s];
	        }
	    }
	    
	    // Calcul du rang
	    pilch[0] = 0 ;
	    for(s=1 ; s<=n ; ++s)
	    {
	        rang[s] = -1;
	        if(ddi[s] == 0)
	        {
	            empiler(s,pilch);
	        }
	    }
	    
	    k = -1;
	    s = pilch[0];
	    prem[0] = s;
	    
	    while(pilch[0] > 0)
	    {
	        ++k;
	        pilch[0] = 0;
	        while(s > 0)
	        {
	            rang[s] = k;
	            h = aps[s];
	            t = fs[h];
	            while(t > 0)
	            {
	                --ddi[t];
	                if(ddi[t] == 0)
	                {
	                    empiler(t,pilch);
	                }
	                h++;
	                t = fs[h];
	            }
	            s = pilch[s];
	        }
	        
	        s = pilch[0];
	        prem[k+1] = s;
	    }
	    return rang;
	}
	
	public void afficheRang(int rang[])
	{
		System.out.print("Rang : ");
		for(int i=1 ; i<rang.length ; ++i)
		{
			System.out.print(rang[i]);
			System.out.print("-");
		}
	}
	
	public void fusion(int i, int j, int[] prem, int[] pilch, int[] cfc, int[] nb)
	{
	    int aux;
		
		if(nb[i] < nb[j])
	    {
	        aux = i;
	        i = j;
	        j = aux;
	    }
	    
	    aux = prem[j];
	    cfc[aux] = i;
	    
	    while(pilch[aux] != 0)
	    {
	        aux = pilch[aux];
	        cfc[aux] = i;
	    }
	    
	    pilch[aux] = prem[i];
	    prem[i] = prem[j];
	    nb[i] += nb[j];
	}
	
	
	public int dmin(int[] d, boolean[] InS)
	{
		int min = Integer.MAX_VALUE;
		int min_index = 0;
		
		for(int v = 0; v < getNbSommets(); ++v)
		{
			if (InS[v] == false && d[v] <= min)
			{
				min = d[v];
				min_index = v;
			}
		}
		return min_index;
	}
	
	public void dijkstra(int s, int[][] c, int[] dist, int[] pred)
	{

	    int n = getNbSommets();
	    
	    dist = new int[n];
	    
	    boolean[] InS = new boolean[n];

        pred = new int[n];


        for(int i = 0; i < n; ++i)
        {
            pred[0] = -1;
            dist[i] = Integer.MAX_VALUE;
            InS[i] = false;
        }

        dist[s] = 0;

        for(int count = 0; count < n - 1; ++count)
        {
        	int u = dmin(dist, InS);
        	
        	InS[u] = true;

            for(int v = 0; v < n; ++v)
            {
            	if(!InS[v] && (c[u][v] != 0) && dist[u] + c[u][v] < dist[v])
                {
                    pred[v] = u;
                    dist[v] = dist[u] + c[u][v];
                }
            }
        }
	}
	
}