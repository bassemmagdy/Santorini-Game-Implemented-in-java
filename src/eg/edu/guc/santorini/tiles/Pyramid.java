package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece implements PieceInterface {
	public Pyramid() {
		super();
	}

	public Pyramid(Location location) {
		super(location);

	}

	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> locations = new ArrayList<Location>();
		int y = getLocation().gety();
		int x = getLocation().getx();
		if (x <= 4 && y <= 4) {
			if (y == 0) {
				if (x == 0) {
					locations.add(new Location(y + 1, x + 1));
					return locations;
				}
				if (x == 4) {
					locations.add(new Location(1, 3));
					return locations;
				}
				locations.add(new Location(y + 1, x + 1));
				locations.add(new Location(y + 1, x - 1));
				return locations;
			}
			if (y == 4) {
				if (x == 0) {
					locations.add(new Location(3, 1));
					return locations;
				}
				if (x == 4) {
					locations.add(new Location(3, 3));
					return locations;
				}
				locations.add(new Location(3, x + 1));
				locations.add(new Location(3, x - 1));
				return locations;
			}
			if (x == 0) {
				locations.add(new Location(y + 1, 1));
				locations.add(new Location(y - 1, 1));
				return locations;
			}
			if (x == 4) {
				locations.add(new Location(y + 1, 3));
				locations.add(new Location(y - 1, 3));
				return locations;
			}
			locations.add(new Location(y + 1, x + 1));
			locations.add(new Location(y - 1, x + 1));
			locations.add(new Location(y + 1, x - 1));
			locations.add(new Location(y - 1, x - 1));
			return locations;
		}
		return locations;
	}
}
