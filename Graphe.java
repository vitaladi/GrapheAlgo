package graphe;

import java.util.ArrayList;
import java.util.Arrays;

import arete.Arete;

public class Graphe {
	
	//attributs
	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;

	
	//constructeurs
	public Graphe(int nbSommets,  Arete [] aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
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
	

	public  Arete [] getAretes() {
		return aretes;
	}

	public int [][] getMatriceAdjacence() {
		return matriceAdjacence;
	}

	public int getNbArcs() {
		return aretes.length;
	}
	
	//Setters
	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}

	public void setAretes(Arete [] aretes) {
		if(aretes.length<this.aretes.length) {
			Arrays.fill(this.aretes, null );
			this.aretes=new Arete[aretes.length];
		} 
		else if(aretes.length>this.aretes.length) {
			this.aretes=new Arete[aretes.length];
		}
		
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
	
	
	public void traverse (int s, int[] fs, int[] aps, int p, int nb, int[] num, int[] mu, int[] prem, int[] pilch, int[] cfc, int[] tarj, boolean[] entarj) {
		p++;
		num[s]=p;
		mu[s]=p;
		tarj=new int[tarj.length+1];
		tarj[tarj.length-1]=s;
		entarj[s]=true;
		int t;
		
		for (int k=aps[s];(t=fs[k])!=0;k++) {
			if (num[t]==0) {
				traverse(t, fs, aps, p, nb, num, mu, prem, pilch, cfc, tarj, entarj);
				if (mu[t]<mu[s]) {
					mu[s]=mu[t];
				}
			}
			else {
				if (entarj[t]==true && num[t]<mu[s]) {
					mu[s]=num[t];
				}
			}
		}
		
		if (mu[s]==num[s]) {
			nb++;
			int x=tarj[tarj.length-1];
			tarj=new int[tarj.length-1];
			entarj[x]=false;
			prem=new int[prem.length+1];
			prem[prem.length-1]=x;
			int i=x;
			cfc[x]=nb;
			while(num[x]>num[s]) {
				
				x=tarj[tarj.length-1];
				if (num[x]>=num[s]) {
					tarj=new int[tarj.length-1];
				}
				entarj[x]=false;
				pilch[i]=x;
				i=x;
				cfc[x]=nb;
				if (x==s) {
					break;
				}
			}
		}
		
	}
	
	//t à mettre à 0 au début 
	public void tarjan (int s, int[] fs, int[] aps, int[] num, int[] mu, int[] prem, int[] pilch, int[] cfc, int[] tarj, boolean[] entarj, int t) {
		int taille=aps.length;
		num=new int[taille];
		pilch=new int[taille];
		cfc=new int[taille];
		entarj=new boolean[taille];
		mu=new int[taille];
		t++;
		for (int p: prem) {
			p=(Integer) null;
		}
		prem=new int[t];
		prem[t-1]=0;
		int nb=0;
		int p=0;
		traverse(6,fs,aps,p,nb,num,mu,prem,pilch,cfc,tarj,entarj);
		num[0]=num.length-1;
		mu[0]=mu.length-1;
	}
	
	
	public void codagePrufer(int[][]adj, int[] C) {
		int n=adj[0][0];
		C=new int[n-1];
		C[0]=n-2;
		
		for (int k=1;k<=n-2;k++) {
			int i=1;
			while(adj[i][0]!=1) {
				i++;
			}
			int j=1;
			while(adj[i][j]!=1) {
				j++;
			}
			C[k]=j;
			adj[i][j]=0;
			adj[j][i]=0;
			adj[i][0]=0;
			adj[j][0]--;
		}
	}
	
	public void decodagePrufer(int[] C, int[] I, int[] b) {
		int n=C[0]+2;
		I=new int[n+1];
		b=new int[n+1];
		for (int i=0;i<n;i++) {
			I[i]=1;
			b[i]=0;
		}
		for (int i=1;i<n-1;i++) {
			int j=1;
			System.out.print("("+C[i]+",");
			while(I[j]==-1 || b[j]!=0) {
				j++;
			}
			System.out.print(j+")");
			b[C[i]]--;
			I[j]=-1;
		}
		int i=1;
		while(I[i]!=1) {
			i++;
		}
		System.out.print("("+i+",");
		i++;
		while(I[i]!=1) {
			i++;
		}
		System.out.println(i+")");
	}
}
