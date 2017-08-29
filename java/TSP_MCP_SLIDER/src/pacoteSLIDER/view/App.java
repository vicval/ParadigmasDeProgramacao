package pacoteSLIDER.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pacoteSLIDER.control.ControleTabuleiro;
import pacoteSLIDER.model.Tabuleiro;
import pacoteSLIDER.model.Tile;

public class App {
	
	private ControleTabuleiro ctrTabuleiro = new ControleTabuleiro();
	
	public App(String fileName) throws FileNotFoundException, IOException{
		
		// faz a leitura do arquivo S_MOVES>DATA
		ctrTabuleiro.leArquivo(fileName);
		
		// mostra o início do tabuleiro
		System.out.println("Tabuleiro Inicial: \n"+ctrTabuleiro.printTabuleiro());
		
		// realiza os movimentos registrados no arquivo
		ctrTabuleiro.fazMovimentos();
		
		// verifica a solução ou não do estado final
		System.out.println(ctrTabuleiro.isSolved()?"Parabéns! Você conseguiu!":"Tente outra vez...");
	}
}
