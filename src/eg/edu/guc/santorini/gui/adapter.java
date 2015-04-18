package eg.edu.guc.santorini.gui;


import eg.edu.guc.santorini.Board;

import eg.edu.guc.santorini.players.Player;

public class adapter {
	Board a;

	public adapter() {

	}

	public adapter(Player p1, Player p2) {
		a = new Board(p1, p2);
	}

	public Player initPlayerp1(String n1, int t1) {
		return new Player(n1, t1);

	}

	public Player initPlayerp2(String n1, int t1) {
		return new Player(n1, t1);

	}

	public int getP2T1Y() {
		return this.a.getP2().getT1().getLocation().getY();
	}

	public int getP2T1X() {
		return this.a.getP2().getT1().getLocation().getX();
	}

	public int getP2T2Y() {
		return this.a.getP2().getT2().getLocation().getY();
	}

	public int getP2T2X() {
		return this.a.getP2().getT2().getLocation().getX();
	}

	public int getP1T1Y() {
		return this.a.getP1().getT1().getLocation().getY();
	}

	public int getP1T1X() {
		return this.a.getP1().getT1().getLocation().getX();
	}

	public int getP1T2Y() {
		return this.a.getP1().getT2().getLocation().getY();
	}

	public int getP1T2X() {
		return this.a.getP1().getT2().getLocation().getX();
	}

	public String[][] getBoard() {
		return a.display();
	}

	
	
}
