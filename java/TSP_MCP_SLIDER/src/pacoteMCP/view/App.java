package pacoteMCP.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import pacoteMCP.control.ControleEstado;
import pacoteMCP.model.Estado;

public class App {
	
	private int qtdPessoas = 0;
	private Estado estado = new Estado();
	private ControleEstado ctrEstado = new ControleEstado();
	
	public App(String fileName) throws FileNotFoundException, IOException{
		
		// leitura inicial do arquivo
		ctrEstado.leArquivo(fileName);
		
		// mostra o estado inicial
		System.out.println("Estado inicial: \n"+estado.toString(qtdPessoas));
		
		// verifica os movimentos registrados
		ctrEstado.fazMovimentos();
		
		// da o resultado final
		System.out.println(ctrEstado.isSolved()?"Parabénns! Você solucionou!":"Que pena! Deu errado! Tente novamente!");
		
	}
}
