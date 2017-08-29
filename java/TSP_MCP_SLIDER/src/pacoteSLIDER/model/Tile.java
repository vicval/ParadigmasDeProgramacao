package pacoteSLIDER.model;

public class Tile {
	private int numero;
	private int [] posAtual = {0,0};

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int[] getPosAtual() {
		return posAtual;
	}

	public void setPosAtual(int[] posAtual) {
		this.posAtual = posAtual;
	}
	
	public String toString(){
		String retorno = "";
		
		// return completo
		// retorno = "("+this.getNumero()+"/["+this.getPosAtual()[0]+"-"+this.getPosAtual()[1]+"])";
		retorno = "("+this.getNumero()+")";
		
		return retorno;
	}
	
}