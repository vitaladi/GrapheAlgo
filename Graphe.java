package graphe;

import arete.Arete;

public class Graphe {
	
	//attributs
	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;
	private int nbAretes;
	
	private int[] fs;
	private int[] aps;
	
	
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
	public Graphe() {
		this(0, null);
	}
	
	//Getters
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
	public void setIndexArete(Arete ar,int index) {
		this.aretes[index]=ar;
	}
	
	public void setNbAretes(int nbAretes) {
		this.nbAretes=nbAretes;
	}
	
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}

	public void setAretes(Arete [] aretes) {
		this.aretes=new Arete[aretes.length];
		for (int i=0;i<aretes.length;i++) {
			this.aretes[i]=aretes[i];
		}
	}

	public void setMatriceAdjacence(int [][] matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}

	public void setNomsSommets(String [] nomsSommets) {
		this.nomsSommets = nomsSommets;
	}	

	public void setNbArcs(int nbArcs) {
		this.aretes=new Arete[nbArcs];
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
	//tarjan
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
	
	
	
	public void codagePrufer(int[][]adj, int[] P) {
		int n=adj[0][0];
		P=new int[n-1];
		P[0]=n-2;
		
		int k=1;
		
		while (k<=n-2) {
			int i=1;
			for (; adj[i][0] != 1; i++);
			int j=1;
			for (; adj[i][j] != 1; j++);
			P[k++]=j;
			adj[i][j]=0;
			adj[j][i]=0;
			adj[i][0]=0;
			adj[j][0]--;
		}
		
		System.out.print("P={");
		for (int i=0;i<n-2;i++) {
			System.out.print(P[i]+",");
		}
		System.out.print(P[P.length-1]+"}");
	}
	
	public void decodagePrufer(int[] P, int[] I, int[] b) {
		int n=P[0]+2;
		//I[0] vaut 0
		//I[i] vaut 1 si le sommet i est présent
		I=new int[n+1];
		//tab d'occurence b pour savoir si un element de I est dans P et cmb de fois
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
	//Les tableaux prem, pilch et cfc sont des variables globales initialis�es dans le main
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
}
