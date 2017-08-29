package pacoteSLIDER.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pacoteSLIDER.model.Tabuleiro;
import pacoteSLIDER.model.Tile;

public class ControleTabuleiro {
	private Tabuleiro tabuleiro = new Tabuleiro();
	private int qtdTiles = 0;
	private FileReader fr;
	private BufferedReader bf;
	private Tile tempTile = new Tile();
	
	public void leArquivo(String fileName) throws NumberFormatException, IOException{
		fr = new FileReader(fileName);
		bf = new BufferedReader(fr);
		
		// calcula o total de quadrados
		qtdTiles = Integer.parseInt(bf.readLine());
		
		// variaveis de aux do jogo
		List<Tile> listaTiles = new LinkedList<Tile>();
		int count = qtdTiles*qtdTiles-1;

		// inicializa os objetos de tiles
		// e o tabuleiro
		for(int i = 0 ; i < qtdTiles ; i++){
			listaTiles = new LinkedList<Tile>();
			for(int j = 0 ; j < qtdTiles ; j++){
				tempTile = new Tile();
				if(i == qtdTiles-1 && j == (qtdTiles-1)){
					tempTile.setNumero(-1);
				}
				else{
					tempTile.setNumero(count);
				}
				tempTile.getPosAtual()[0] = i;
				tempTile.getPosAtual()[1] = j;
				listaTiles.add(tempTile);
				count--;
			}
			tabuleiro.getTiles().add(listaTiles);
		}
	}
	
	public void fazMovimentos() throws IOException{
		String mov;
		int xAntigo = 0, yAntigo = 0;
		while((mov = bf.readLine())!= null){
			if(moveIsPossible(tempTile.getPosAtual()[1], tempTile.getPosAtual()[0], mov)){
				System.out.println("Movimento: " + mov);
				yAntigo = tempTile.getPosAtual()[0];
				xAntigo = tempTile.getPosAtual()[1];
				
				// l��gica cartesiana para o movimento do 'tile'
				// o quadrante utilizado �� o 4o
				if(mov.equals("E")){
					tempTile.getPosAtual()[1] -= 1;
					tabuleiro.getTiles().get(yAntigo).get(xAntigo).setNumero(tabuleiro.getTiles().get(yAntigo).get(xAntigo-1).getNumero());
					tabuleiro.getTiles().get(yAntigo).get(xAntigo-1).setNumero(-1);
				}
				else if(mov.equals("D")){
					tempTile.getPosAtual()[1] += 1;
					tabuleiro.getTiles().get(yAntigo).get(xAntigo).setNumero(tabuleiro.getTiles().get(yAntigo).get(xAntigo+1).getNumero());
					tabuleiro.getTiles().get(yAntigo).get(xAntigo+1).setNumero(-1);
				}
				else if(mov.equals("C")){
					tempTile.getPosAtual()[0] -= 1;
					tabuleiro.getTiles().get(yAntigo).get(xAntigo).setNumero(tabuleiro.getTiles().get(yAntigo-1).get(xAntigo).getNumero());
					tabuleiro.getTiles().get(yAntigo-1).get(xAntigo).setNumero(-1);
				}
				else if(mov.equals("B")){
					tempTile.getPosAtual()[0] += 1;
					tabuleiro.getTiles().get(yAntigo).get(xAntigo).setNumero(tabuleiro.getTiles().get(yAntigo+1).get(xAntigo).getNumero());
					tabuleiro.getTiles().get(yAntigo+1).get(xAntigo).setNumero(-1);
				}
				
				System.out.println(printTabuleiro());
			}
		}
	}
	
	public String printTabuleiro(){
		String retorno = "";
		
		for(int i = 0 ; i < qtdTiles ; i++){
			for(int j = 0 ; j < qtdTiles ; j++){
				retorno += ""+tabuleiro.getTiles().get(i).get(j);
			}
			retorno += "\n";
		}
				
		return retorno;
	}
	
	public boolean moveIsPossible(int posX, int posY, String mov){
		if(mov.equals("E") && posX>0) return true;
		else if(mov.equals("D") && posX<qtdTiles-1) return true;
		else if(mov.equals("C") && posY>0) return true;
		else if(mov.equals("B") && posY<qtdTiles-1) return true;
		else return false;
	}
	
	public boolean isSolved(){
		int verificador = 1;
		
		for(int i = 0 ; i < qtdTiles ; i++)
			for(int j = 0 ; j < qtdTiles ; j++){
				if(tabuleiro.getTiles().get(i).get(j).getNumero()!=verificador && i!=qtdTiles-1 && j!=qtdTiles-1)
					return false;
				verificador++;
			}
		return true;
	}
	
}
