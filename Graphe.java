package graphe;

import java.lang.reflect.Array;

import java.util.Arrays;

import arete.Arete;

public class Graphe {
	
	//attributs
	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;
	private int nbAretes;
	
	
	//constructeurs
	public Graphe(int nbSommets,  Arete [] aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
		//this.nbAretes=this.aretes.length-1;
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
	
	//probleme sur les resultats attendus 
	public void traverse (int s,int p,int n,int[]fs,int[]aps,int[]tarj,int[]num,int[]ro,int[]cfc,int[]pilch,int[]prem,boolean[]entarj) {
		p++;
		num[s]=p;
		ro[s]=p;
		
		empiler(s,tarj);
		entarj[s]=true;
		int t;
		
		for (int k=aps[s];(t=fs[k])!=0;k++) {
			if (num[t]==0) {
				traverse(t,p,n,fs,aps,tarj,num,ro,cfc,pilch,prem,entarj);
				if (ro[t]<ro[s]) {
					ro[s]=ro[t];
				}
			}
			else {
				if (entarj[t]==true && num[t]<ro[s]) {
					ro[s]=num[t];
				}
			}
		}
		
		if (ro[s]==num[s]) {
			n++;
			int u;
			do {
				
				u=depiler(tarj);
				entarj[u]=false;
				
				empiler(u,pilch);
				cfc[u]=n;
				
			} while (u!=s);
			prem[n]=pilch[0];
			pilch[0]=0;
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
