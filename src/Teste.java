import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws FileNotFoundException {
		
		Random r = new Random();
		FileReader arquivo = new FileReader("src/IAfileMovies2.txt");
		FileReader arquivoUsers = new FileReader("src/IAfileUsers.txt");
		Kmeans var = new Kmeans();
		Scanner sc = new Scanner(arquivo).useDelimiter("\\n|,"); // lerá o arquivo com os filmes
		Scanner scan = new Scanner(arquivoUsers).useDelimiter("\\n|,"); //lerá os arquivos com os usuários e avaliações
		Scanner scanner = new Scanner(System.in);
		Pontos[] verifica = new Pontos[var.getK()];
		boolean loop = true;
		int j = 0;
		int contador = 0;
		
		while(sc.hasNext()){ 
			
			String id = sc.next();
			String nome = sc.next();
			String gen1 = sc.next();
			String gen2 = sc.next();
			
			Pontos p = new Pontos(Double.valueOf(gen1),Double.valueOf(gen2), nome, Integer.valueOf(id));
			var.addPoint(p);
			
			System.out.println(p);
			
		}
		
		System.out.print("\n\n");
		for(int i = 1;  i <= var.getK(); i++){
			
			Cluster c = new Cluster(i,var.getPontos(r.nextInt(500))); // instância de K Clusters
			var.addCluster(c);
			//System.out.println(c.getCentro());
		}
		
		while(loop){  // Dentro desse laço serão realizadas as operações do K-Means
			
			var.limpaCluster(); // limpa os clusters antes de recomeçar os agrupamentos 
			j++;
			System.out.println("Interação " + j);
		
			for(int i = 0;  i < var.getK(); i++){					//Exibe os centros dos clusters
				System.out.println(var.getClusters(i).getCentro()); //a cada interação
				verifica[i] = var.getClusters(i).getCentro();
			} 
			
			System.out.println("\n");
		
			var.clustering();
		
			for(int i = 0; i< var.getK(); i++){                       //exibe os pontos presentes em cada cluster
				System.out.println("Cluster " + var.getClusters(i).getNumber() );
				System.out.println("Centro: " + var.getClusters(i).getCentro());
				for(Pontos p: var.getClusters(i).getPontos()){
					System.out.println(p);
				}
				System.out.println();
			
			}
		
			var.newCenter();
		
			for (int i = 0; i< var.getK();i++) {    //Após realizado o calculo dos novos centros
			//	var.getClusters(i).getPontos().clear(); // limpa-se os clusters para novo agrupamento
				if(verifica[i].equals(var.getClusters(i).getCentro())) 
						contador ++;
				if(contador == var.getK()){ //A cada interação os centros são salvos em um array 
					loop = false;
					break;
				}
			//	else
				//	var.limpaCluster();
				//var.getClusters(i).getPontos().clear();
			}
		//	if(contador == var.getK()) //A cada interação os centros são salvos em um array 
			//	loop = false;			// Após o calculo dos novos centros é vericado se houve alteração
			contador = 0;				// Caso negativo, "loop" é setada pra False, interrompendo o laço
		}
		
		// A partir deste ponto são realizadas as recomendações
		System.out.println("Digite seu id: ");
		int k = scanner.nextInt();
		Usuario user = new Usuario(k);
		
		while(scan.hasNext()){
			
			
			String id = scan.next();
			String idFilme = scan.next();
			String nota = scan.next();
			
			//System.out.print(id + "-");
			//System.out.print(idFilme + "-");
			//System.out.println(nota);
			if(Integer.valueOf(id) > k)
				break;
			if(Integer.valueOf(id)== k){
				user.avaliacoes(Integer.valueOf(idFilme), Double.valueOf(nota));
		//		System.out.println(Double.valueOf(nota));
			}
		}
		
	//	System.out.println("Demarca");
	/*	
		for(int i = 0; i< var.getK(); i++){                       //exibe os pontos presentes em cada cluster
			System.out.println("Cluster " + var.getClusters(i).getNumber() );
			System.out.println("Centro: " + var.getClusters(i).getCentro());
			for(Pontos p: var.getClusters(i).getPontos()){
				System.out.println(p);
			}
			System.out.println();
		
		}  */ 
		
		System.out.println(user.recomenda(var));
	
	//	System.out.println("Demarca");
	//	var.bss();
		var.sse();
		
		scan.close();
		sc.close();
		scanner.close();
	}
}
