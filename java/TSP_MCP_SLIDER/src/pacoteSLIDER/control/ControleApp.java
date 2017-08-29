package pacoteSLIDER.control;

import java.io.IOException;

import pacoteSLIDER.view.App;

public class ControleApp {
	public static void main(String[] args) {
		try {
			new App("S_MOVES.DATA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
