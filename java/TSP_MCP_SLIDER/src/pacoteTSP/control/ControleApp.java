package pacoteTSP.control;

import java.io.FileNotFoundException;
import java.io.IOException;

import pacoteTSP.view.App;

public class ControleApp {
	public static void main(String[] args) {
		try {
			new App("CIDADES.DATA");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

