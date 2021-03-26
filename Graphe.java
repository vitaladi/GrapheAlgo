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
	
	
	
	

}
