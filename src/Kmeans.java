import java.util.ArrayList;

public class Kmeans {
	
	private int k = 15; // número de clusters
	private ArrayList<Pontos> pontos;
	private ArrayList<Cluster> clusters;
	
	
	public Kmeans(){
		this.pontos = new ArrayList<Pontos>();
		this.clusters = new ArrayList<Cluster>();
	}
	
	
	
	public int getK() {
		return k;
	}
	

	public ArrayList<Pontos> getPontos() {
		return pontos;
	}


	public void addPoint(Pontos p){
		
		this.pontos.add(p);
	}

	
	public Pontos getPontos(int num){
		
		return this.pontos.get(num);
	}
	
	
	public Cluster getClusters(int num){
		
		return this.clusters.get(num);
		
		
	}
	
	public void addCluster(Cluster c){
		
		this.clusters.add(c);
	}
	
	public void limpaCluster(){
		
		for(int i = 0; i< this.k;i++){
			this.getClusters(i).getPontos().clear();
		}
		
		
	}
	
	public void clustering(){
		
		double distancia;
		double minDistancia = 0;
		int clusterId = 0;
		
		for(Pontos p :  pontos){
			
			for(int i = 0; i< this.getK(); i++){
				Cluster c = clusters.get(i);
				distancia = Pontos.euclidianDistance(p, c.getCentro());
				
				if(i == 0){
					minDistancia = distancia;
					clusterId = i ;
				}
				else if(distancia < minDistancia){
					minDistancia = distancia;
					clusterId = i ;
				}
			}
			clusters.get(clusterId).addPontos(p);
			
		}
		
		
	}
	
	public void newCenter(){
		
		for(Cluster c : clusters){
			
			double somaX = 0;
			double somaY = 0;
			ArrayList<Pontos> pontos = c.getPontos();
			
			for(Pontos p: pontos){
				somaX += p.getX();
				somaY += p.getY();
			}
			
			Pontos centroide = new Pontos(somaX/pontos.size(),somaY/pontos.size());
			if(pontos.size() != 0){
				c.setCentro(centroide);
			}
		}	
	}
	
	public Pontos centroDados(){
		
		double totalX = 0;
		double totalY = 0;
		
		for(Pontos p: this.getPontos()){
			
			totalX = totalX + p.getX();
			totalY = totalY + p.getY();
			
		}
		
		return new Pontos(totalX/this.getPontos().size() ,totalY/this.getPontos().size());
	}
	
	// Indices internos
	public double wss(){
		
		double valor = 0;
		
		for(int i = 0; i< this.getK();i++){
			for(Pontos p: this.getClusters(i).getPontos()){
				valor = valor + Math.pow(Pontos.euclidianDistance(p, this.getClusters(i).getCentro()), 2);
			}
		}
		
		System.out.println("WSS"); //coesão
		System.out.printf("%.1f \n",valor);
		
		return valor;
	}
	
	
	public double bss(){
		
		double valor = 0;
		
		for(int i = 0; i< this.getK(); i++){
			
			valor = valor + this.getClusters(i).getPontos().size() * Math.pow(Pontos.euclidianDistance(this.getClusters(i).getCentro(), this.centroDados()), 2);
		}
		
		System.out.println("BSS");// separação
		System.out.printf("%.1f \n",valor);
		
		return valor;
	}
	
	
	public void sse(){
		
		double var = (this.bss() + this.wss());
		System.out.printf("Total: %.1f", var );
		
	}
	
	
}
