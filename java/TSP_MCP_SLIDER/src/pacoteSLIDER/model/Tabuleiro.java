package pacoteSLIDER.model;

import java.util.LinkedList;
import java.util.List;

public class Tabuleiro {
	List<List<Tile>> tiles = new LinkedList<List<Tile>>();

	public List<List<Tile>> getTiles() {
		return tiles;
	}

	public void setTiles(List<List<Tile>> tiles) {
		this.tiles = tiles;
	}
}
