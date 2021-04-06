package graphe;

import arete.Arete;


public class Graphe {

	private int nbSommets;
	private Arete [] aretes;
	private int [][] matriceAdjacence;
	private String [] nomsSommets;


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
	
	
	public void empiler (int s, int [] pilch)
	{
		pilch[s] = pilch[0];
		pilch[0] = s;
		
	}
	
	public int depiler (int [] pilch)
	{
		int var = pilch[0];
		pilch[0] = pilch[var];
		return var;
	}
	
	public void ordonnancement(int fp[], int app[], int d[], int lc[], int fpc[], int appc[] ) {
		// fs_aps2fp_app(); 
		int n= app[0]; 
		lc[0]=n; 
		appc[0]=n;
		int kc, t, longueur; 
		lc[1]=0; 
		fpc[1]=0; 
		appc[1]=1; 
		kc=1; 
		for(int s=2; s<=n; s++ ) 
		{
			lc[s]=0;
			appc[s]=kc+1; 
			for(int k=app[s]; (t=fp[k])!=0;k++) 
			{
				longueur=lc[t]+d[t];
				if(longueur>=lc[s]) {
					if(longueur ==lc[s])
					{
						kc++; 
						fpc[kc]=t; 
					}
					else
					{
						lc[s]=longueur; 
						kc=appc[s];
						fpc[kc]=t; 
					}
				}
			}
			kc++;
			fpc[kc]=0;
		}
		fpc[0]=kc;
	}
	
}