package nrainhas;

import javax.swing.JOptionPane;


public class NRainhas {

	// Numero de rainhas
	protected int N;
	// Numero de solucoes encontradas
	protected int contador =0 ;
	
	protected String print="";
	
	public NRainhas(){
	}
	
	public void solucionar() {
		boolean[][] tabuleiro = new boolean[N][N];
		solucionar(tabuleiro, 0);
	}

	// A funcao recursiva do metodo solucionar e responsavel por posiciona  uma rainhas na linha em todas as colunas possiveis

	
	private void solucionar(boolean[][] tabuleiro, int row){

		final int size = tabuleiro.length;

		for(int col=0; col < size; col++) {

			// Coloca rainha na posicao [row][col]
			tabuleiro[row][col] = true;

			// Se for possivel posiciona-la...
			if( ataca(tabuleiro,row,col) ) {
				if( row < size-1 )
					solucionar(tabuleiro, row+1);
				else{
					print = print +" Solucao numero "+ (contador+1) + ":" + System.getProperty("line.separator");
					print(tabuleiro);
					this.contador++;
				}
			}

			// Se nao for possivel, remove a rainha desta posicao
			tabuleiro[row][col] = false;
		}
	}

	//Funcao que verifica se a rainha estaria em ataque nessa posicao
	
	private static boolean ataca(boolean[][] tabuleiro, int row, int col)
	{
		final int N = tabuleiro.length;
		
		//testa se as posicoes sao regioes que estao sendo atacadas
		for(int k=1; k <= row; k++) {
			if( tabuleiro[row-k][col]                     || 
					((col-k >= 0) && tabuleiro[row-k][col-k]) ||
					((col+k <  N) && tabuleiro[row-k][col+k]) )
				return false;
		}

		return true;
	}
	// Imprime o tabuleiro da solucao do problema das Rainhas
	
	private void print(boolean[][] tabuleiro) {

		final int N = tabuleiro.length;

		String sepLine = " +";
		for(int i=0; i < N; i++)
			sepLine += " --- +";
		for(int r=0; r < N; r++) {
			print = print + sepLine + System.getProperty("line.separator");
			print = print + " |";
			for(int c=0; c < N; c++) {
				String q = tabuleiro[r][c] ? "R" : " ";
				print = print + "   " + q + "   |";
			}
			print = print + System.getProperty("line.separator");
		}
		print = print + sepLine + "\n\n" + System.getProperty("line.separator");
	}
}