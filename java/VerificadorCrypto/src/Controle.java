import java.util.ArrayList;

public class Controle {

	public void testar(String listaLetras, String listaNumero) {
		ArrayList<Letra> listaCompilada = new ArrayList<Letra>();
		// ====================================================
		// Essa parte le a string e instancia as letras,
		// se a letra ja estiver instanciada, procura ela e adiciona na lista de
		// letras ja copiadas
		for (int i = 0; i < listaLetras.length(); i++) {
			// System.out.println("testar:ele atual:" + listaLetras.charAt(i));
			if (existeEm(listaCompilada, listaLetras.charAt(i)) == -1) {
				if (listaLetras.charAt(i) != '+'
						&& listaLetras.charAt(i) != '=') {
					/*
					 * System.out.println("testar:n axou: " +
					 * listaNumero.charAt(i));
					 */
					int nur = Integer.parseInt(listaNumero.charAt(i) + "");
					// System.out.println("testar: nur "+nur);
					Letra letra = new Letra(listaLetras.charAt(i), nur);
					listaCompilada.add(letra);
				} else {
					Letra letra = new Letra(listaLetras.charAt(i));
					listaCompilada.add(letra);
				}
			} else {
				// System.out.println("testar:axou:" + listaLetras.charAt(i));
				int ind = existeEm(listaCompilada, listaLetras.charAt(i));
				Letra letra = listaCompilada.get(ind);
				listaCompilada.add(letra);
			}
		}
		// codigo para printar e ve se ta tudo certo
		// ======================================================
		/*
		 * for (Letra letra : listaCompilada) {
		 * System.out.println(letra.valorLetra); }
		 */
		// ==========================================
		String teste = "";
		// esta funcao le da lista de letras ja compiladas, e vai somando os
		// valores
		int te = 0, antesIgual = 0;
		for (Letra letra : listaCompilada) {
			if (letra.valorLetra != '+' && letra.valorLetra != '=') {

				teste += letra.getValorNumero();
				// System.out.println("teste:"+letra.getValorNumero());
				// par+=letra.getValorNumero();
				// System.out.println("teste:"+teste);
			} else {
				if (letra.valorLetra == '+') {
					te += Integer.parseInt(teste);
					// System.out.println("te:"+te);
					teste = "";
				}
				if (letra.valorLetra == '=') {
					// System.out.println("=");
					te += Integer.parseInt(teste);
					teste = "";
					antesIgual = te;
					// System.out.println("antesIgual="+antesIgual);
					te = 0;
				}
			}
		}
		// checa se os valores estão corretos ou não
		te += Integer.parseInt(teste);
		// System.out.println("te:"+te);
		// System.out.println("antesIgual:"+antesIgual);
		if (antesIgual == te) {
			System.out.println("TA CERTO");
		} else {
			System.out.println("TA ERRADO");
		}

	}

	private int existeEm(ArrayList<Letra> listaCompilada, char c) {
		// System.out.println("existeEm: " + c);
		for (int i = 0; i < listaCompilada.size(); i++) {
			if (listaCompilada.get(i).getValorLetra() == c) {
				// System.out.println("existeEm:encontrou");
				return i;
			}
		}
		// System.out.println("existeEm:nao encontrou");
		return -1;
	}
}
