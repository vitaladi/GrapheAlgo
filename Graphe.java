package graphe;

import arete.Arete;



public class Graphe {

	private int nbSommets;
	private Arete[] aretes;
	private int[][] matriceAdjacence;
	private String[] nomsSommets;
	private int[] fs;
	private int[] aps;
	private static int[] fp;
	private static int[] app;
	int[] pilch;
	
	

	public Graphe(int nbSommets, Arete[] aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
	}

	public Graphe() {
		this(0, null);
	}

	public String[] getNomsSommets() {
		return nomsSommets;
	}

	public int getNbSommets() {
		return nbSommets;
	}

	public Arete[] getAretes() {
		return aretes;
	}

	public int[][] getMatriceAdjacence() {
		return matriceAdjacence;
	}

	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}

	public void setAretes(Arete[] aretes) {
		this.aretes = aretes;
	}

	public void setMatriceAdjacence(int[][] matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}

	public void setNomsSommets(String[] nomsSommets) {
		this.nomsSommets = nomsSommets;
	}

	public int[] getFs() {
		return fs;
	}

	public int[] getAps() {
		return aps;
	}
	
	

	public void empiler(int s, int[] pilch) {
		pilch[s] = pilch[0];
		pilch[0] = s;

	}

	public int depiler(int[] pilch) {
		int var = pilch[0];
		pilch[0] = pilch[var];
		return var;
	}



	
	 public void ordonnancement(int[] D) {
	    	int n = app[0];
	    	int m = fp[0];
	        int[] fpc = new int[m+1];
	        int[] appc = new int[n+1];
	        int[] lc = new int[n+1]; 
	        lc[0] = n;
	        
	        appc[0] = n;
	        int kc;
	        int t,longueur;
	        lc[1] = 0;
	        fpc[1] = 0;
	        appc[1] = 1;
	        kc = 1;
	        
	        for (int s = 2 ; s <= n; s++) {
	        	lc[s] = 0;
	        	appc[s] = kc+1;
	        	for (int k = app[s]; (t=fp[k]) != 0; k++) {
	        		longueur = lc[t] + D[t];
	        		if (longueur >= lc[s]) {
	        			if (longueur == lc[s]) {
	        				kc++;
	        				fpc[kc] = t;
	        			}
	        			else {
	        				lc[s] = longueur;
	        				kc = appc[s];
	        				fpc[kc] = t;
	        			}
	        		}
	        	}
	        	kc++;
	        	fpc[kc] = 0;
	        }
	        fpc[0] = kc;
	                      
	        int suiv;
	        System.out.println("\nLe chemin critique est \n");
	        for (int i = 0; i < appc[0] ; i++) {
	            suiv = appc[i];
	            System.out.print(fpc[suiv]+" ");
	        }
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
	
	public void affichage_fp_app() {
		matAdjVersFsAps();
		System.out.println("Tableau FP du graphe : ");
		for (int i=0; i < fp.length; i++) {
			System.out.print(fp[i] + " ");
		}
		System.out.println("");
		
		System.out.println("Tableau APP du graphe : ");
		for (int i=0; i < app.length; i++) {
			System.out.print(app[i] + " ");
		}
	}
	public void fs_aps2fp_app() {
		int[]aps = getAps(); 
		int[] fs = getFs(); 
		int n = aps[0]; 
		int m=fs[0];
		fp = new int[m+1];
		app = new int[nbSommets+1];
		fp[0]=m; 
		app[0]=n; 
		app[1]=1; 
		for(int i=1; i<n;i++) {
			app[i+1]=app[i]+getDDI()[i]+1; 
		}
		int k=1; 
		for(int i=1;i<=n;i++) {
			while(fs[k]!=0) {
				fp[app[fs[k]]]=i; 
				app[fs[k]]++;
				k++;
			}
			k++;
		}
		fs[app[n]]=0; 
		for(int i=n-1;i>=1;i--) {
			fp[app[i]]=0; 
			app[i+1]=app[i]+1; 
		}
		app[1]=1;  
	}
	
	
    public int[] getDDI() {
       int[] ddi = new int[aps[0]+1];
       int s;
	   for(int i=1; i <=aps[0] ; i++) ddi[i]=0;
	   for(int i=1; i <=fs[0] ; i++){
	  		s=fs[i];
	  		if (s >0) ddi[s]++;
	  	}
	   return ddi;
    }
	
	public static void run() {

		
		
		

	    Graphe g = new Graphe();
	    g.fs_aps2fp_app();
	    
	   
	    int[] D = {0,6,4,5,4,9,7,8,2,5,2,7,6,5,7,0};

	    g.ordonnancement(D);
		
		

	}

	
}