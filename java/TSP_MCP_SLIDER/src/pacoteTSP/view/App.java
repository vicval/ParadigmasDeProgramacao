package pacoteTSP.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import pacoteTSP.control.ControleCaminho;
import pacoteTSP.model.Cidade;

public class App {
	
	private ControleCaminho ctrCaminho = new ControleCaminho();
	
	public App(String fileName) throws FileNotFoundException, IOException{
		
		// ler vetor de cidades
		System.out.println("Quantidade de cidades a serem analisadas: "+
				ctrCaminho.leArquivo(fileName));
		
		// cria o vetor de cidades
		System.out.println("\nCidades: ");
		for (Cidade c : ctrCaminho.lerCidades()) {
			System.out.printf(" -%S- ", c.getNomeCidade());
		}
		
		// gera e apresenta a matriz de pesos
		System.out.println(ctrCaminho.matrizDePesos());
		
		// pega o caminho de menor custo
		System.out.println("Menor custo: ");
		ctrCaminho.menorCusto(0);
		
		System.out.println("\nMenor custo: " + ctrCaminho.getCustoViagem());
	}
}
