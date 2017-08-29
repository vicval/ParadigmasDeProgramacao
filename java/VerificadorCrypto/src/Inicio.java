import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) {
		Controle controle = new Controle();
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite a palavra: Ex:send+more=money");
		String letras = entrada.nextLine();
		System.out.println("Digite o valor:Ex:9567+1085=10652");
		String numero = entrada.nextLine();
		entrada.close();
		// =====================
		//letras = "send+more=money";
		//numero = "9567+1085=10652";
		// ====================
		controle.testar(letras, numero);
	}

}
