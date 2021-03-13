package graphe;

import java.util.ArrayList;

import arete.Arete;

public class Graphe {

	private int nbSommets;
	private ArrayList<Arete> aretes;
	private ArrayList<ArrayList<Integer>> matriceAdjacence;
	private ArrayList<String> nomsSommets;

	public Graphe(int nbSommets, ArrayList<Arete> aretes) {
		this.nbSommets = nbSommets;
		this.aretes = aretes;
	}

	public Graphe() {
		this(0, null);
	}
public ArrayList<String> getNomsSommets() {
		return nomsSommets;
	}
	public int getNbSommets() {
		return nbSommets;
	}
	

	public ArrayList<Arete> getAretes() {
		return aretes;
	}

	public ArrayList<ArrayList<Integer>> getMatriceAdjacence() {
		return matriceAdjacence;
	}

	

	public void setNbSommets(int nbSommets) {
		this.nbSommets = nbSommets;
	}

	public void setAretes(ArrayList<Arete> aretes) {
		this.aretes = aretes;
	}

	public void setMatriceAdjacence(ArrayList<ArrayList<Integer>> matriceAdjacence) {
		this.matriceAdjacence = matriceAdjacence;
	}

	public void setNomsSommets(ArrayList<String> nomsSommets) {
		this.nomsSommets = nomsSommets;
	}
	
	
	
	

}
