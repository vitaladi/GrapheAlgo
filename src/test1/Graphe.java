package test1;



public class Graphe {

	//attributs
	
	private int nbSommets;
	private Arete [] aretes = new Arete[100];
	private int [][] matriceAdjacence;
	private String [] nomsSommets;
	private int nbAretes =0;
	
	private int[] fs;
	private int[] aps;
	private int[] fp;
	private int[] app;

	
	//attributs pour tarjan
	int[] num;
	int[] pilch;
	int[] cfc;
	int[] tarj;
	boolean[] entarj;
	int[] prem;
	int nb;
	int p;
	
	
	//constructeurs
	
	public Graphe(int nbSommets,  Arete [] aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
	}
	
	public Graphe(int nbSommets) {
		this(nbSommets,null);
	}
	
	public Graphe(int fs[], int aps[])
	{
		this.fs=fs;
		this.aps=aps;
		this.nbSommets = aps[0];
	}

	public Graphe() {
		this(0, null);
	}
	
	public Graphe(int[][] matriceAdjacence) // mon constructeur utile
	{
		this.matriceAdjacence = matriceAdjacence;
		
		this.nbSommets = matriceAdjacence[0][0];
		
		for(int i = 1; i <= nbSommets; i++)
		{
			for(int j = 1; j <= nbSommets; j++)		
			{
				if(matriceAdjacence[i][j] != 0)
				{
					aretes[nbAretes] = new Arete(i,j,matriceAdjacence[i][j]);
					nbAretes++;
					
				}
			}
		}
		
		matAdjVersFsAps();
	}
	
	
	
	//getters
	
	public String [] getNomsSommets() {
		return nomsSommets;
	}
	public int getNbSommets() {
		return nbSommets;
	}
	public int getNbAretes() {
		return nbAretes;
	}

	public  Arete [] getAretes() {
		return aretes;
	}

	public int [][] getMatriceAdjacence() {
		return matriceAdjacence;
	}
	
	
	public int getNbArcs() {
		return aretes.length;
	}
	
	public Arete getIndexArete(int index) {
		return this.aretes[index];
	}
	
	public int[] getFs() {
		return fs;
	}
	
	public int[] getAps() {
		return aps;
	}
	
	
	
	
	//Setters
	

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
	public void setFs(int[] fs) {
		this.fs=fs;
	}
	
	public void setAps(int[] aps) {
		this.aps=aps;
	}
	
	
	public void setIndexArete(Arete ar,int index) {
		this.aretes[index]=ar;
	}
	
	public void setNbAretes(int nbAretes) {
		this.nbAretes=nbAretes;
	}

	

	public void setNbArcs(int nbArcs) {
		this.aretes=new Arete[nbArcs];
	}
	
	
	
	
	
	
	
	//fonctions/algorithmes
		
	
	
	
	
	
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
				if(r[i] < 0)
				{
					System.out.print(r[i]+"|");
				}
				else
				{
					System.out.print("+"+r[i]+"|");
				}
				
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
	
	
	public void empiler (int s, int [] pilch) {
		pilch[s] = pilch[0];
		pilch[0] = s;
		
	}
	
	
	
	public int depiler (int [] pilch) {
		int var = pilch[0];
		pilch[0] = pilch[var];
		return var;
	}
	
	public void tarjan() {
    	num = new int[nbSommets +1];
    	pilch = new int[nbSommets +1];
    	cfc = new int[nbSommets +1];
    	tarj = new int[nbSommets +1];
    	entarj = new boolean[nbSommets +1];
    	prem = new int[nbSommets+1];
    	
    	for (int i = 0; i < nbSommets+1; i++) {
    		num[i] = 0;
    		pilch[i] = 0;
    		cfc[i] = 0;
    		tarj[i] = 0;
    		entarj[i] = false;
    		prem[i] = 0;
    	}
    	
    	int n = aps[0];
    	p = 0;
    	nb = 0;
    	for (int s = 1; s <= n; s++) {
    		if (num[s] == 0) {
    			traverse(s);
    		}
    	}
    	prem[0] = nb;
    	
    	//Affichage tableaux pilch prem et cfc
    	System.out.println("");
    	System.out.println("");
    	System.out.println("Tarjan:");
    	System.out.println("");
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
	
	//pour tarjan
	public void traverse(int s) {
    	p++;
    	int[] ro = new int[nbSommets+1];
    	ro[s] = p;
    	num[s] = p;
    	empiler(s,tarj);
    	entarj[s] = true;
    	int t;
    	
    	for (int k = aps[s]; (t=fs[k]) != 0; k++) {
    		if (num[t] == 0) {
    			traverse(t);
    			if (ro[t] < ro[s]) {
    				ro[s] = ro[t];
    			}
    		}
    		else {
    			if ((entarj[t] == true) && (num[t] < ro[s])) {
    				ro[s] = num[s];
    			}
    		}
    	}
    	
    	if (ro[s] == num[s]) {
    		nb++;
    		int x = depiler(tarj);
    		entarj[x] = false;
    		prem[nb] = x;
    		cfc[x] = nb;
    		int ind = x;
    		while(x != s) {
    			x = depiler(tarj);
    			entarj[x] = false;
    			pilch[ind] = x;
    			ind = x;
    			cfc[x] = nb;
    		}
    		pilch[s] = 0;
    	}
    	
    }
	
	public void graphe_reduit() {
    	int j = 0;
    	int t;
    	
    	//tableau qui indique les sommets qui sont deja integres
    	boolean[] integre = new boolean[nbSommets+1];
    	
    	int[] nouveau_fs = new int[nbSommets+1];
    	int[] nouveau_aps = new int[nbSommets+1];
    	for (int i = 0; i < nbSommets+1; i++) {
    		integre[i] = false;
    		nouveau_fs[i] = 0;
    		nouveau_aps[i] = 0;
    	}
    	for (int l = 1; l <= prem[0]; l++) {
    		integre[l] = true;
    		int s = prem[l];
    		nouveau_aps[l] = j+1;
    		while(s !=0) {
    			for (int k = getAps()[s]; (t = getFs()[k]) != 0; k++) {
    				if (integre[cfc[t]] == false) {
    					j++;
    					nouveau_fs[j] = cfc[t];
    					integre[cfc[t]] = true;
    				}
    			}
    			s = pilch[s];
    		}
    		j++;
    		nouveau_fs[j] = 0;
    	}
    	nouveau_fs[0] = j;
    	
    	System.out.println("");
    	System.out.println("FS du graphe reduit :");
    	for (int i = 0; i < nouveau_fs.length; i++) {
    		System.out.print(nouveau_fs[i] + " ");
    	}
    	System.out.println();
    	
    	System.out.println("APS du graphe reduit :");
    	for (int i = 0; i < nouveau_aps.length; i++) {
    		System.out.print(nouveau_aps[i] + " ");
    	}
    }
	
	
	public void codagePrufer(int[] P) {
		
		determinerNombreSommetsRelieCodage(matriceAdjacence);
		
		int n= matriceAdjacence[0][0];
		P=new int[n-1];
		P[0]=n-2;
		
		int k=1;
		
		while (k<=n-2) {
			int i=1;
			for (;  matriceAdjacence[i][0] != 1; i++);
			int j=1;
			for (;  matriceAdjacence[i][j] != 1; j++);
			P[k++]=j;
			 matriceAdjacence[i][j]=0;
			 matriceAdjacence[j][i]=0;
			 matriceAdjacence[i][0]=0;
			 matriceAdjacence[j][0]--;
		}
		System.out.println("");
		System.out.println("");
		System.out.println("Prüfer :");
    	System.out.println("");
		System.out.print("P={");
		for (int i=0;i<n-2;i++) {
			System.out.print(P[i]+",");
		}
		System.out.print(P[P.length-1]+"}");
		
		
		int[]I= null;
		int[]b = null;
		decodagePrufer(P,I,b);
	}
	
	public void decodagePrufer(int[] P, int[] I, int[] b) {
		int n=P[0]+2;
		
		System.out.println("");
		System.out.println("");
		System.out.println("Décodage de Prüfer:");
		
		I=new int[n+1];
		b=new int[n+1];
		for (int i=0;i<n;i++) {
			I[i]=1;
			b[i]=0;
		}
		for (int i=1;i<n-1;i++) {
			++b[P[i]];
		}
		
		for (int i=1;i<n-1;i++) {
			int j=1;
			System.out.print("("+P[i]+",");
			while(I[j]==-1 || b[j]!=0) {
				j++;
			}
			System.out.print(j+")");
			b[P[i]]--;
			I[j]=-1;
		}
		int i=1;
		while(I[i]!=1) 
			i++;
		System.out.print("("+i+",");
		i++;
		while(I[i]==-1) 
			i++;
		System.out.println(i+")");
	}
	
	public void trier(Graphe g)
	{
		int p,o,d;
		for (int i = 0; i < g.nbAretes - 1; i++)
			for (int j = i + 1; j < g.nbAretes; j++)
				if ((g.aretes[j].getPoids() < g.aretes[i].getPoids()) || (g.aretes[j].getPoids() == g.aretes[i].getPoids() && g.aretes[j].getOrigine() < g.aretes[i].getDestination()) || (g.aretes[j].getPoids() == g.aretes[i].getPoids() && g.aretes[j].getDestination() < g.aretes[i].getDestination()))
				{
					p = g.aretes[j].getPoids();
					o=g.aretes[j].getOrigine();
					d=g.aretes[j].getDestination();
					
					g.aretes[j].setPoids(g.aretes[i].getPoids());
					g.aretes[j].setOrigine(g.aretes[i].getOrigine());
					g.aretes[j].setDestination(g.aretes[i].getDestination());
					
					g.aretes[i].setPoids(p);
					g.aretes[i].setOrigine(o);
					g.aretes[i].setDestination(d);
				}
	}
	
	public void fusionner(int i, int j, int []prem, int []pilch, int []cfc, int []NbElem)
	// i et j sont les numeros des composantes à fusionner
	// en une seule composante qui portera le numéro le plus 
	// petit des deux 
	{
		if (NbElem[i] < NbElem[j])
		{
			int aux = i;
			i = j;
			j = aux;
		}
		int s = prem[j];
		cfc[s] = i;
		while (pilch[s] != 0)
		{
			s = pilch[s];
			cfc[s] = i;
		}
		pilch[s] = prem[i];
		prem[i] = prem[j];
		NbElem[i] += NbElem[j];
	}
	
	//Graphe g est le graphe d'entrée et il est déja trié
	//Graphe t est le graphe de sortie
	public void kruskal(Graphe g, Graphe t,int []prem, int []pilch, int []cfc, int []NbElem)
	{
		t.aretes = new Arete[g.nbSommets-1];
		int x; 
		int y; 
		int i = 0, j = 0;
		while (j < g.getNbSommets()-1)
		{
			Arete ar = g.aretes[i];
			x = cfc[ar.getOrigine()];
			y = cfc[ar.getDestination()];
			if (x != y)
			{
				t.aretes[j++] = g.aretes[i];
				fusionner(x, y, prem, pilch, cfc, NbElem);
			}
			i++;
		}
		t.setNbSommets(g.getNbSommets());
		t.setNbAretes(g.getNbSommets()-1);
		
	}

	public void determinerNombreSommetsRelieCodage(int[][]adj) {
        int compteur=0;
        for (int i=1;i<=adj[0][0];i++) {
            for (int j=1;j<=adj[0][0];j++) {
                if (adj[i][j]==1) {
                    compteur++;
                }
            }
            adj[i][0]=compteur;
            compteur=0;
        }
   
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
	
	public int[] rang()
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



