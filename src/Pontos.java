/*               Listagem numérica dos gêneros cinematográficos
 * 
 * 1- Musical
 * 2- Infantil
 * 3- Comédia
 * 4- Animação
 * 5- Fantasia
 * 6- Ficção Científica
 * 7- Aventura
 * 8- Ação
 * 9- Western
 * 10- Guerra
 * 11- Documentário
 * 12- Romance
 * 13- Drama
 * 14- Crime
 * 15- Film-Noir
 * 16- Mistério
 * 17- Suspense
 * 16- Terror
 */
public class Pontos {
	
	private String nome; // nome do filme
	private int id;      // código do filme
	private double x;
	private double y;
	
	
	public Pontos(double x, double y, String nome, int id){
		this.x = x;
		if(y == 0)          //	As linhas de arquivo nas quais o filme possuia apenas um
			this.y = x;    // 	gênero listado foram preenchidas com 0. Como essa estratégia
		else               //	poderia afetar os resultados no calculo das distâncias, 
			this.y = y;     //	estes pontos que no arquivos estão setados em 0
		this.nome = nome;  //	são igualados ao primeiro ponto no construtor.
		this.id = id;
		
	}
	
	public Pontos(double x, double y){
		this.x = x;
		this.y = y;
		this.id = 0;
		this.nome = "Cluster Center";
	}
	
	
	public void setX(double x) {
		this.x = x;
	}

	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getId(){
		return id;
	}


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	// distancia euclidiana
	public static double euclidianDistance(Pontos ponto, Pontos centro){
		
		return Math.sqrt(Math.pow((centro.getX() - ponto.getX()), 2) + Math.pow((centro.getY() - ponto.getY()) , 2));
		
	}
	
	// distancia de manhattan
	public static double manhattanDistance(Pontos ponto, Pontos centro){
		
		return (centro.getX() - ponto.getX()) + (centro.getY() - centro.getY());
	}
	
	//Minkowisk
	public static double Minkowisk(Pontos ponto, Pontos centro, double r){
		
		double soma = 0;
	
		soma = Math.pow( ponto.getX() - centro.getX(), r);
		soma += Math.pow(ponto.getY() - centro.getY(), r);
			
		return Math.pow(soma , 1/r);
		
	}
	
	public boolean equals(Pontos p){
		
		if((this.x == p.getX()) && (this.y == p.getY()))
			return true;
		else
			return false;
		
	}
	
	public String toString(){ 
		
		
		String formated = String.format("%d: %s (%.1f, %.1f )", this.id, this.nome, this.x, this.y);
		return formated;
		
	}
}
