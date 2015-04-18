package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece implements PieceInterface {

	public Cube() {
		super();
	}

	public Cube(Location location) {

		super(location);

	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> locations = new ArrayList<Location>();
		int y = getLocation().gety();
		int x = getLocation().getx();
		if (x <= 4 && y <= 4) {
			if (y == 0) {
				addUpperRowCube(locations, y, x);
				return locations;
			}
			if (y == 4) {
				if (x == 0) {
					locations.add(new Location(3, 0));
					locations.add(new Location(4, 1));
				}
				if (x == 4) {
					locations.add(new Location(4, 3));
					locations.add(new Location(3, 4));
				}
				if (x != 0 && x != 4) {
					locations.add(new Location(y - 1, x));
					locations.add(new Location(y, x + 1));
					locations.add(new Location(y, x - 1));
				}
				return locations;
			}
			if (x == 0) {
				locations.add(new Location(y, x + 1));
				locations.add(new Location(y - 1, x));
				locations.add(new Location(y + 1, x));
				return locations;
			}
			if (x == 4) {
				locations.add(new Location(y, x - 1));
				locations.add(new Location(y + 1, x));
				locations.add(new Location(y - 1, x));
				return locations;
			}
			locations.add(new Location(y, x + 1));
			locations.add(new Location(y, x - 1));
			locations.add(new Location(y + 1, x));
			locations.add(new Location(y - 1, x));
		}
		return locations;
	}

//helper method to avoid the check styles errors .. max num. of lines in the method is 50 line
	

	void addUpperRowCube(ArrayList<Location> location, int y, int x) {
		if (x == 0) {
			location.add(new Location(0, 1));
			location.add(new Location(1, 0));
		}
		if (x == 4) {
			location.add(new Location(0, 3));
			location.add(new Location(1, 4));
		}
		if (x != 0 && x != 4) {
			location.add(new Location(y + 1, x));
			location.add(new Location(y, x + 1));
			location.add(new Location(y, x - 1));
		}
	}
}
