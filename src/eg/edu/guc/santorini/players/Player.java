package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;

public class Player {
	private String name;
	private Piece t1;
	private Piece t2;
	private int type;

	public Player(String n, int x) {

		name = n;

		type = x;
		if (type == 1) {
			t1 = new Cube();
			t2 = new Cube();
		
		
		}
		if (type == 2) {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = this.name;
	}

	public Piece getT1() {
		return t1;
	}

	public void setT1(Piece t1) {
		this.t1 = t1;
	}

	public Piece getT2() {
		return t2;
	}

	public void setT2(Piece t2) {
		this.t2 = t2;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
