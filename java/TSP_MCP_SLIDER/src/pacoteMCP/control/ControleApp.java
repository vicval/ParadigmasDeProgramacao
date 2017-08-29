package pacoteMCP.control;

import java.io.IOException;

import pacoteMCP.view.App;

public class ControleApp {
	public static void main(String[] args) {
		try {
			new App("MCP.DATA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
