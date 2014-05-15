import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;


public class TankWorld {

	private static final int TANKS_TO_MAKE = 10;

	public static int tankCount = TANKS_TO_MAKE;

	private static ActorWorld world = new ActorWorld();

	public static void main (String[] args) {
		
		world.setGrid(new BoundedGrid<Actor>(20,20));
		world.show();
		
		startWorld();

	}
	
	private static void startWorld() {
		
		for (int i = 0; i < TANKS_TO_MAKE; i++) {
			world.add(world.getRandomEmptyLocation(), new Tank());
		}
		
		
		printTanks();
	}
	
	public static void increaseTanks() {
		tankCount++;
		printTanks();

	}
	
	public static void decreaseTanks() {
		tankCount--;
		printTanks();
	}
	
	private static void printTanks() {
		world.setMessage("Tanks at start: " + TANKS_TO_MAKE + "\nCurrent tanks: " +tankCount);

	}
	
	public static void gameOver() {
		for (Location loc : world.getGrid().getOccupiedLocations()) {
			Actor actor = world.getGrid().get(loc);
			if (actor instanceof Tank) {
				((Tank) actor).die();
			}
		}
	}
}
