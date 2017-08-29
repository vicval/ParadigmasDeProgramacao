package pacoteTSP.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pacoteTSP.model.Cidade;

public class ControleCaminho {
	
	
	private static final int MAXCIDADES = 100;
	private int qtdCidades;
	private List<Cidade> cidades = new LinkedList<Cidade>();
	private List<List<Integer>> matrizPesos = new LinkedList<List<Integer>>();
	private List<Integer> listaAux = new LinkedList<Integer>();
	private int cidadesVisitadas[];
	private int custoViagem = 0;
	private FileReader fr;
	private BufferedReader br;
	
	public int leArquivo(String fileName) throws NumberFormatException, IOException{
		
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		
		// verifica se a quantidade de cidades entradas é menor do que
		// o máx de cidades permitidas
		if((qtdCidades = Integer.parseInt(br.readLine())) > MAXCIDADES){
			System.out.println("Quantidade de cidades maior do que o permitido!");
			System.exit(0);
		}
		
		return qtdCidades;
	}
	
	public List<Cidade> lerCidades() throws IOException{
		for (String s : br.readLine().split(" ")) {
			cidades.add(new Cidade(s));
		}
		// vetor de cidades já visitadas
		cidadesVisitadas = new int[qtdCidades];
		return cidades;
	}
	
	public String matrizDePesos() throws IOException{
		// string aux para leitura das cidades
		String auxS[], retorno = "";
		
		// leitura da matriz de pesos
		retorno += ("\nMatriz de custos: \n");
		for(int i = 0 ; i < qtdCidades ; i++){
			auxS = br.readLine().split("\\s+");
			listaAux = new LinkedList<Integer>();
			for(int j = 0 ; j < qtdCidades ; j++ ){
				listaAux.add(Integer.parseInt(auxS[j]));
				retorno += ("["+auxS[j]+"]");
			}
			retorno += "\n";
			matrizPesos.add(listaAux);
			cidadesVisitadas[i]=0;
		}
		
		return retorno;
	}
	
	public void menorCusto(int cidadeI){
		int cidadeN;
		// indica que a cidade foi visitada j��
		cidadesVisitadas[cidadeI]=1;
		System.out.printf(" [%s] -> ",cidades.get(cidadeI).getNomeCidade());
		// pego o menor caminho de I at�� N
		cidadeN = menorCaminho(cidadeI);
		
		if(cidadeN == 999){
			cidadeN = 0;
			System.out.printf(" [%s] ",cidades.get(cidadeN).getNomeCidade());
			custoViagem += matrizPesos.get(cidadeI).get(cidadeN);
		}
		else{
			menorCusto(cidadeN);
		}
	}
	
	public int menorCaminho(int cidade){
		int i, nc = 999, min = 999 , kmin = 0 ;
		for(i=0 ; i < qtdCidades ; i++){
			// verifica a cidade enviada e a atual
		    if((matrizPesos.get(cidade).get(i) != 0) && (cidadesVisitadas[i] == 0))
		      if(matrizPesos.get(cidade).get(i) < min){
		        min = matrizPesos.get(cidade).get(0) + matrizPesos.get(cidade).get(i);
		        kmin = matrizPesos.get(cidade).get(i);
		        nc=i;
		      }
		    }
		  if(min!=999)
		    custoViagem += kmin;
		  return nc;
	}
	
	public int getCustoViagem(){
		return this.custoViagem;
	}
}
