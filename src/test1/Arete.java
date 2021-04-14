package test1;

public class Arete {

	private int origine;
	private int destination;
	private int poids;

	public Arete(int origine, int destination, int poids) {
		this.origine = origine;
		this.destination = destination;
		this.poids = poids;
	}

	public Arete() {
		this(0, 0, 0);
	}
	
	public void setOrigine(int origine){
		this.origine = origine;
	}

	public void setDestination(int destination){
		this.destination = destination;
	}

	public void setPoids(int poids){
		this.poids = poids;
	}

	public int getOrigine() {
		return origine;
	}

	public int getPoids() {
		return poids;
	}

	public int getDestination() {
		return destination;
	}

}
