package pacoteMCP.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pacoteMCP.model.Estado;

public class ControleEstado {
	
	private int qtdPessoas = 0;
	private Estado estado = new Estado();
	private BufferedReader br;
	private FileReader fr;
	
	public int leArquivo(String fileName) throws FileNotFoundException, IOException{
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		
		// quantidade de pessoas de cada grupo
		qtdPessoas = Integer.parseInt(br.readLine());
		// inicializa as margens
		estado.setQtdCanibaisEsquerda(qtdPessoas);
		estado.setQtdMissionariosEsquerda(qtdPessoas);
		
		return qtdPessoas;
	}
	
	public void fazMovimentos() throws FileNotFoundException, IOException{
		String move = "", barco[];
		while((move=br.readLine())!=null){
			// barco receve a lista de pessoas que estarão nele
			System.out.println("Movimento: "+move);
			barco = move.split(" ");
			// se o barco estiver na esquerda
			if(estado.getPosBarco().equals("E")){
				for(int i = 0 ; i < barco.length ; i++){
					if(barco[i].equals("M")){
						estado.modificaMissionariosEsquerda(-1);
						estado.modificaMissioanariosDireita(1);
					}
					else if(barco[i].equals("C")){
						estado.modificaCanibaisEsquerda(-1);
						estado.modificaCanibaisDireita(1);
					}
					else{
					}
				}
				estado.setPosBarco("D");
			}
			// se o barco estiver na direita
			else{
				for(int j = 0 ; j < barco.length ; j++){
					if(barco[j].equals("M")){
						estado.modificaMissionariosEsquerda(1);
						estado.modificaMissioanariosDireita(-1);
					}
					else if(barco[j].equals("C")){
						estado.modificaCanibaisEsquerda(1);
						estado.modificaCanibaisDireita(-1);
					}
					else{	
					}
				}
				estado.setPosBarco("E");
			}
			if(!estado.isValido()) System.exit(0);
			System.out.println("\n"+estado.toString(qtdPessoas)+"\n");
		}
		
	}
	
	public boolean isSolved(){
		return estado.isResolvido(qtdPessoas);
	}
}
