public class Letra {
	char valorLetra;
	int valorNumero;

	public Letra(char charAt, int nur) {
		valorLetra= charAt;
		//System.out.println("Letra:nur"+nur);
		if(nur>=0 &&nur<=10){
			//System.out.println("Letra:set numero"+nur);
			valorNumero=nur;
		}
	}
	public Letra(char charAt) {
		valorLetra= charAt;
	}

	public char getValorLetra() {
		return valorLetra;
	}

	public void setValorLetra(char valorLetra) {
		this.valorLetra = valorLetra;
	}

	public int getValorNumero() {
		return valorNumero;
	}

	public void setValorNumero(int valorNumero) {
		this.valorNumero = valorNumero;
	}
	
}
