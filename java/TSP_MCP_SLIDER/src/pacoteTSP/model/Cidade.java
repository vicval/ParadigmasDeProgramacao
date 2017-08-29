package pacoteTSP.model;

public class Cidade {
	private String nomeCidade;

	public Cidade(String nome){
		this.nomeCidade = nome;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
}