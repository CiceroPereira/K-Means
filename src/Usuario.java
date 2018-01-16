import java.util.ArrayList;
import java.util.Random;

public class Usuario {
	
	private int id;
	private ArrayList<Integer> preferences; // recebe o id dos filmes bem avaliados
	private ArrayList<Integer> idFilmes; // recebe o id de todos os filmes avaliados
	
	
	public Usuario(int id){
		
		this.id = id;
		this.preferences = new ArrayList<Integer>();
		this.idFilmes = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getPreferences() {
		return preferences;
	}
	public void setPreferences(ArrayList<Integer> preferences) {
		this.preferences = preferences;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Integer> getIdFilmes() {
		return idFilmes;
	}
	public void setIdFilmes(ArrayList<Integer> idFilmes) {
		this.idFilmes = idFilmes;
	}
	
	public void avaliacoes(int id, double nota){

			this.idFilmes.add(id);
			
			if(nota >= 3.5)
				this.preferences.add(id);
		
	}
	
	public boolean contem(int num){
		
		for(Integer i: this.idFilmes){
			if(i == num)
				return true;
		}
		
		return false;
	}
	
	
	public String recomenda(Kmeans means){
		
		Random r = new Random();
		int movie = this.preferences.get(r.nextInt(this.preferences.size())); //escolhe aleatóriamente um dos filmes bem avaliados pelo usuário
		int cNum = 0; //cluster onde o filme sorteado na linha acima se encontra
		int count = 0;
		String retorno = "";
		String filmeAssistido = "";
		
		for(int i = 0; i < means.getK(); i++){
			for(Pontos p : means.getClusters(i).getPontos()){
			//	System.out.println(p);
				if(movie == p.getId()){
					filmeAssistido = p.toString();
					cNum = i;
					break;
				}
			}
		}
		Cluster c = means.getClusters(cNum);
		
	//	System.out.println(movie);
	//	System.out.println(cNum);
		
		System.out.println("Porque você viu: " + filmeAssistido + "\nRecomendamos\n" );
		while(count<3){
			Pontos point = c.getPontos().get(r.nextInt(c.getPontos().size()));
		//	System.out.println(point);
		//	System.out.println(point.getId());
			if(!(this.contem(point.getId()))){
				retorno = retorno.concat(point.toString() + "\n");
				count++;
			}
	//		else{
		//		retorno = retorno.concat(point.toString());
		//	}
			
		}
		
		
		
		return retorno;
		
	}
	
	

}
