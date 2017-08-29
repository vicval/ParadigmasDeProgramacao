package pacoteMCP.model;

public class Estado {
	
	private int qtdMissionariosEsquerda = 0;
	private int qtdCanibaisEsquerda = 0;
	
	private int qtdMissionariosDireita = 0;
	private int qtdCanibaisDireita = 0;
	
	private String posBarco = "E";
	
	public void modificaCanibaisEsquerda(int valor){
		this.qtdCanibaisEsquerda+=valor;
	}
	
	public void modificaCanibaisDireita(int valor){
		this.qtdCanibaisDireita+=valor;
	}

	public void modificaMissionariosEsquerda(int valor){
		this.qtdMissionariosEsquerda+=valor;
	}

	public void modificaMissioanariosDireita(int valor){
		this.qtdMissionariosDireita+=valor;
	}
	
	public int getQtdCanibaisEsquerda() {
		return qtdCanibaisEsquerda;
	}

	public void setQtdCanibaisEsquerda(int qtdCanibaisEsquerda) {
		this.qtdCanibaisEsquerda = qtdCanibaisEsquerda;
	}

	public int getQtdMissionariosDireita() {
		return qtdMissionariosDireita;
	}

	public void setQtdMissionariosDireita(int qtdMissionariosDireita) {
		this.qtdMissionariosDireita = qtdMissionariosDireita;
	}

	public int getQtdCanibaisDireita() {
		return qtdCanibaisDireita;
	}

	public void setQtdCanibaisDireita(int qtdCanibaisDireita) {
		this.qtdCanibaisDireita = qtdCanibaisDireita;
	}

	public String getPosBarco() {
		return posBarco;
	}

	public void setPosBarco(String posBarco) {
		this.posBarco = posBarco;
	}

	public int getQtdMissionariosEsquerda() {
		return qtdMissionariosEsquerda;
	}

	public void setQtdMissionariosEsquerda(int qtdMissionariosEsquerda) {
		this.qtdMissionariosEsquerda = qtdMissionariosEsquerda;
	}
	
	public String toString(int qtdGeral){
		String estado = "";
		int missionariosEsquerda = this.getQtdMissionariosEsquerda(), missionariosDireita = this.getQtdMissionariosDireita(), canibaisEsquerda = this.getQtdCanibaisEsquerda(), canibaisDireita = this.getQtdCanibaisDireita();
		for(int i = 0 ; i < qtdGeral ; i++){
			estado += missionariosEsquerda>0?"M ~~~ ":" ~~~ ";
			estado += missionariosDireita>0?"M\n":" \n";
			estado += canibaisEsquerda>0?"C ~~~ ":" ~~~ ";
			estado += canibaisDireita>0?"C\n":" \n";
			missionariosEsquerda--;
			missionariosDireita--;
			canibaisEsquerda--;
			canibaisDireita--;
		}
		
		estado += this.getPosBarco().equals("E")?"B ~~~ ":" ~~~ B\n";
		
		return estado;
	}
	
	public boolean isValido(){
		if(this.getQtdCanibaisDireita()<=this.getQtdMissionariosDireita() || this.getQtdCanibaisEsquerda()<=this.getQtdMissionariosEsquerda()) 
			return true;
		else
			return false;
	}
	
	public boolean isResolvido(int qtd){
		if(this.getQtdCanibaisDireita()==qtd && this.getQtdMissionariosDireita()==qtd && this.getQtdCanibaisEsquerda()==0 && this.getQtdMissionariosEsquerda()==0) 
			return true;
		else
			return false;
	}
}


