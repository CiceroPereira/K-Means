import java.util.ArrayList;

public class Cluster {
	
	private int number ; //identificador do cluster
	private Pontos centro;
	private ArrayList<Pontos> clusterPontos;
	
	public Cluster(int number, Pontos centro){
		
		this.number = number;
		this.centro = centro;
		this.clusterPontos = new ArrayList<Pontos>();
		
	}
	
	public int getNumber(){
		return number;
	}

	public Pontos getCentro() {
		return centro;
	}
	
	public void setCentro(Pontos p){
		
		this.centro = p;
	}
	
	public void addPontos(Pontos pontos){
		this.clusterPontos.add(pontos);
	}
	
	public ArrayList<Pontos> getPontos() {
		return clusterPontos;
	} 
	 

}
