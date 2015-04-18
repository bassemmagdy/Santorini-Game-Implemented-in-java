package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	private Location location;
	private boolean justMoved;
	private boolean justPlaced;

	public Piece() {

	}

	public Piece(Location location) {

		this.location = location;
		justMoved = false;
		justPlaced = false;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> locations = new ArrayList<Location>();
		int x = this.getLocation().getx();
		int y = this.getLocation().gety();
		if (x <= 4 && y <= 4) {
			if (y == 0) {
				addUpperRow(locations, y, x);
				return locations;
			}
			if (y == 4) {
				if (x == 0) {
					locations.add(new Location(y - 1, x));
					locations.add(new Location(y, x + 1));
					locations.add(new Location(y - 1, x + 1));
					return locations;
				}
				if (x == 4) {
					locations.add(new Location(y - 1, x));
					locations.add(new Location(y, x - 1));
					locations.add(new Location(y - 1, x - 1));
					return locations;
				}
				locations.add(new Location(y - 1, x));
				locations.add(new Location(y, x + 1));
				locations.add(new Location(y, x - 1));
				locations.add(new Location(y - 1, x + 1));
				locations.add(new Location(y - 1, x - 1));
				return locations;
			}
			if (x == 0) {
				locations.add(new Location(y - 1, x));
				locations.add(new Location(y + 1, x));
				locations.add(new Location(y, x + 1));
				locations.add(new Location(y - 1, x + 1));
				locations.add(new Location(y + 1, x + 1));
				return locations;
			}
			if (x == 4) {
				locations.add(new Location(y - 1, x));
				locations.add(new Location(y + 1, x));
				locations.add(new Location(y, x - 1));
				locations.add(new Location(y - 1, x - 1));
				locations.add(new Location(y + 1, x - 1));
				return locations;
			}
			addCenter(locations, y, x);
			return locations;
		}
		return locations;
	}

	// (addCenters ,addUpperRow) are Helper Methods to avoid checkStyle Problems
	// :(
	public void addCenter(ArrayList<Location> locations, int y, int x) {

		locations.add(new Location(y - 1, x));
		locations.add(new Location(y + 1, x));
		locations.add(new Location(y, x + 1));
		locations.add(new Location(y, x - 1));
		locations.add(new Location(y - 1, x - 1));
		locations.add(new Location(y - 1, x + 1));
		locations.add(new Location(y + 1, x - 1));
		locations.add(new Location(y + 1, x + 1));

	}

	public void addUpperRow(ArrayList<Location> locations, int y, int x) {
		if (x == 0) {
			locations.add(new Location(y + 1, x));
			locations.add(new Location(y, x + 1));
			locations.add(new Location(y + 1, x + 1));
		}
		if (x == 4) {
			locations.add(new Location(y + 1, x));
			locations.add(new Location(y, x - 1));
			locations.add(new Location(y + 1, x - 1));
		} else {
			locations.add(new Location(y + 1, x));
			locations.add(new Location(y, x + 1));
			locations.add(new Location(y, x - 1));
			locations.add(new Location(y + 1, x - 1));
			locations.add(new Location(y + 1, x + 1));
		}

	}

	public abstract ArrayList<Location> possibleMoves();

	public boolean isJustMoved() {
		return justMoved;
	}

	public void setJustMoved(boolean justMoved) {
		this.justMoved = justMoved;
	}

	public boolean isJustPlaced() {
		return justPlaced;
	}

	public void setJustPlaced(boolean justPlaced) {
		this.justPlaced = justPlaced;
	}

}
