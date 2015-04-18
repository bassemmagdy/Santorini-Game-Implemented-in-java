package eg.edu.guc.santorini;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {
	private Player p1;
	private Player p2;
	private Location[][] board;
	private Player turn;

	public Board() {

	}

	public Board(Player p1, Player p2) {
		board = new Location[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[j][i] = new Location(j, i);
			}
		}

		this.p1 = p1;
		this.p2 = p2;
		if (p1.getType() == 1) {
			this.p1.setT1(new Cube(board[0][0]));
			this.p1.setT2(new Cube(board[4][1]));

		}

		if (p1.getType() == 2) {
			this.p1.setT1(new Pyramid(board[0][0]));
			this.p1.setT2(new Pyramid(board[4][1]));
		}
		if (p2.getType() == 1) {
			this.p2.setT1(new Cube(board[0][3]));
			this.p2.setT2(new Cube(board[4][4]));
		}

		if (p2.getType() == 2) {
			this.p2.setT1(new Pyramid(board[0][3]));
			this.p2.setT2(new Pyramid(board[4][4]));
		}
		turn = p1;
		DrawGUI();
	}

	public void DrawGUI() {
		String[][] a = this.display();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (a[i][j].equals("0C1")) {
					board[i][j].setIcon(new ImageIcon("Assets/level0Cube.png"));
				}
				if (a[i][j].equals("0C2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level0Cubehighlighted.png"));
				}
				if (a[i][j].equals("1C1")) {
					board[i][j].setIcon(new ImageIcon("Assets/level1Cube.png"));
				}
				if (a[i][j].equals("1C2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level1Cubehighlighted.png"));
				}
				if (a[i][j].equals("2C1")) {
					board[i][j].setIcon(new ImageIcon("Assets/level2Cube.png"));
				}
				if (a[i][j].equals("2C2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level2Cubehighlighted.png"));
				}
				if (a[i][j].equals("3C1")) {
					board[i][j].setIcon(new ImageIcon("Assets/level3Cube.png"));
				}
				if (a[i][j].equals("3C2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level3Cubehighlighted.png"));
				}
				if (a[i][j].equals("0P1")) {
					board[i][j]
							.setIcon(new ImageIcon("Assets/level0Prism.png"));
				}
				if (a[i][j].equals("0P2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level0Prismhighlighted.png"));
				}
				if (a[i][j].equals("1P1")) {
					board[i][j]
							.setIcon(new ImageIcon("Assets/level1Prism.png"));
				}
				if (a[i][j].equals("1P2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level1Prismhighlighted.png"));
				}
				if (a[i][j].equals("2P1")) {
					board[i][j]
							.setIcon(new ImageIcon("Assets/level2Prism.png"));
				}
				if (a[i][j].equals("2P2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level2Prismhighlighted.png"));
				}
				if (a[i][j].equals("3P1")) {
					board[i][j]
							.setIcon(new ImageIcon("Assets/level3Prism.png"));
				}
				if (a[i][j].equals("3P2")) {
					board[i][j].setIcon(new ImageIcon(
							"Assets/level3Prismhighlighted.png"));
				}
				if (a[i][j].equals("0")) {
					board[i][j].setIcon(new ImageIcon("Assets/level0.png"));
				}
				if (a[i][j].equals("1")) {
					board[i][j].setIcon(new ImageIcon("Assets/level1.png"));
				}
				if (a[i][j].equals("2")) {
					board[i][j].setIcon(new ImageIcon("Assets/level2.png"));
				}
				if (a[i][j].equals("3")) {
					board[i][j].setIcon(new ImageIcon("Assets/level3.png"));
				}
				if (a[i][j].equals("4")) {
					board[i][j].setIcon(new ImageIcon("Assets/dome.png"));
				}

			}
		}

	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Location[][] getBoard() {
		return board;
	}

	public void setBoard(Location[][] board) {
		this.board = board;
	}

	public void setTurn(Player turn) {
		this.turn = turn;
	}

	public void move(Piece piece, Location newLocation)
			throws InvalidMoveException {
		if (isGameOver()) {
			throw new InvalidMoveException();
		}
		if ((piece.equals(p1.getT1()) || piece.equals(p1.getT2()))
				&& turn != p1) {
			throw new InvalidMoveException();
		}

		if ((piece.equals(p2.getT1()) || piece.equals(p2.getT2()))
				&& turn != p2) {
			throw new InvalidMoveException();
		}

		if (piece.isJustMoved()) {
			throw new InvalidMoveException();
		}

		if (!canMove(piece, newLocation)) {
			throw new InvalidMoveException();

		}

		piece.setLocation(board[newLocation.gety()][newLocation.getx()]);
		piece.setJustMoved(true);
		piece.setJustPlaced(false);

	}

	public boolean canMove(Piece piece, Location location) {
		if (board[location.gety()][location.getx()].equals(p1.getT1()
				.getLocation())) {
			return false;
		}
		if (board[location.gety()][location.getx()].equals(p1.getT2()
				.getLocation())) {
			return false;
		}
		if (board[location.gety()][location.getx()].equals(p2.getT1()
				.getLocation())) {
			return false;
		}

		if (board[location.gety()][location.getx()].equals(p2.getT2()
				.getLocation())) {
			return false;
		}

		if (piece.getLocation().equals(board[location.gety()][location.getx()])) {
			return false;
		}
		if (!piece.possibleMoves().contains(location)) {
			return false;
		}
		if (piece.getLocation().getLevel()
				- board[location.gety()][location.getx()].getLevel() < -1) {
			return false;
		}

		if (board[location.gety()][location.getx()].getLevel() > 3) {
			return false;
		}
		return true;

	}

	public void place(Piece piece, Location newLocation)
			throws InvalidPlacementException {
		if (isGameOver()) {
			throw new InvalidPlacementException();
		}
		if (board[newLocation.gety()][newLocation.getx()].equals(p1.getT1()
				.getLocation())) {
			throw new InvalidPlacementException();

		}
		if (board[newLocation.gety()][newLocation.getx()].equals(p1.getT2()
				.getLocation())) {
			throw new InvalidPlacementException();

		}
		if (board[newLocation.gety()][newLocation.getx()].equals(p2.getT1()
				.getLocation())) {
			throw new InvalidPlacementException();
		}
		if (board[newLocation.gety()][newLocation.getx()].equals(p2.getT2()
				.getLocation())) {
			throw new InvalidPlacementException();
		}

		if (!piece.isJustMoved()) {
			throw new InvalidPlacementException();
		}
		if (board[newLocation.gety()][newLocation.getx()].getLevel() > 3) {
			throw new InvalidPlacementException();
		}

		if (canPlace(piece, newLocation) && !piece.isJustPlaced()) {
			board[newLocation.gety()][newLocation.getx()]
					.setLevel(board[newLocation.gety()][newLocation.getx()]
							.getLevel() + 1);
			piece.setJustPlaced(true);
			piece.setJustMoved(false);

			if (piece.equals(p1.getT1()) || piece.equals(p1.getT2())) {
				turn = p2;

			} else {
				turn = p1;
			}

		} else {
			throw new InvalidPlacementException();
		}

	}

	@Override
	public boolean isGameOver() {
		if (turn == p1 && hasNoMoves(p2)) {
			return false;

		}
		if (turn == p2 && hasNoMoves(p1)) {
			return false;
		}

		if (hasNoMoves(p1) && turn == p1) {
			return true;
		}

		if (hasNoMoves(p2) && turn == p2) {
			return true;
		}
		if (isWinner(p1)) {
			return true;

		}
		if (isWinner(p2)) {
			return true;
		}
		return false;
	}

	public boolean isWinner(Player player) {
		if (player.getT1().getLocation().getLevel() == 3
				|| player.getT2().getLocation().getLevel() == 3) {
			return true;
		}
		if (player.getName() == p1.getName()) {
			return hasNoMoves(p2);
		}
		if (player.getName() == p2.getName()) {
			return hasNoMoves(p1);
		}
		return false;

	}

	public boolean hasNoMoves(Player player) {
		ArrayList<Location> possiblPT1 = player.getT1().possibleMoves();
		ArrayList<Location> possiblPT2 = player.getT2().possibleMoves();
		boolean playerCanMove = false;

		for (int i = 0; i < possiblPT1.size(); i++) {
			if (canMove(player.getT1(), possiblPT1.get(i))) {
				playerCanMove = true;
				break;
			}
		}

		for (int i = 0; i < possiblPT2.size(); i++) {
			if (canMove(player.getT2(), possiblPT2.get(i))) {
				playerCanMove = true;
				break;
			}

		}
		return !playerCanMove;

	}

	@Override
	public Player getWinner() {
		if (isWinner(p1)) {
			return p1;
		} else {
			return p2;
		}
	}

	@Override
	public boolean canPlace(Piece piece, Location location) {
		if (board[location.gety()][location.getx()].equals(p1.getT1()
				.getLocation())) {
			return false;
		}
		if (board[location.gety()][location.getx()].equals(p1.getT2()
				.getLocation())) {
			return false;
		}
		if (board[location.gety()][location.getx()].equals(p2.getT1()
				.getLocation())) {
			return false;
		}

		if (board[location.gety()][location.getx()].equals(p2.getT2()
				.getLocation())) {
			return false;
		}

		if (piece.getLocation().equals(board[location.gety()][location.getx()])) {
			return false;
		}

		if (piece.getLocation().equals(location)) {
			return false;
		}
		if (!piece.possiblePlacements().contains(location)) {
			return false;
		}
		if (location.getLevel() == 4) {
			return false;
		}
		return true;

	}

	@Override
	public Player getTurn() {
		return turn;
	}

	public String[][] display() {
		String[][] a = new String[5][5];
		for (int i = 0; i < BoardInterface.SIDE; i++) {
			for (int j = 0; j < BoardInterface.SIDE; j++) {
				a[j][i] = "" + board[j][i].getLevel();

				if (board[j][i].equals(p1.getT1().getLocation())
						|| board[j][i].equals(p1.getT2().getLocation())) {
					if (p1.getType() == 1) {
						a[j][i] = a[j][i] + "C1";
					}
					if (p1.getType() == 2) {
						a[j][i] = a[j][i] + "P1";
					}
				}
				if (board[j][i].equals(p2.getT1().getLocation())
						|| board[j][i].equals(p2.getT2().getLocation())) {
					if (p2.getType() == 1) {
						a[j][i] = a[j][i] + "C2";
					}
					if (p2.getType() == 2) {
						a[j][i] = a[j][i] + "P2";
					}

				}

			}

		}
		return a;
	}

	public void setboardcell(int y, int x, Location l) {
		board[y][x] = l;
	}

}
