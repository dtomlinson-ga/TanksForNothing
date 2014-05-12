import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;


public class TankWorld {

	public static int tankCount = 0;
	private static int trueTankCount = 0;

	private static ActorWorld world = new ActorWorld();

	public static void main (String[] args) {
		
		//world.setGrid(new BoundedGrid<Actor>(70, 150));
		world.setGrid(new BoundedGrid<Actor>(10,10));
		
		//world.add(new Location(0, 0), new Tank(Color.GRAY, true, 5));
		
		for (int i = 0; i < 100; i++) {
			world.add(world.getRandomEmptyLocation(), new Tank());
		}
		

		
		for (int x = 0; x < world.getGrid().getNumRows(); x++) {
			for (int y = 0; y < world.getGrid().getNumRows(); y++) {
				if (world.getGrid().get(new Location(x, y)) instanceof Tank) trueTankCount++;
			}
		}
		
		world.show();
		
		world.setMessage("Estimated tank count: " + Math.abs(tankCount) + "\nActual tank count at start: " + trueTankCount);
	}
	
	public static void increaseTanks() {
		tankCount++;
		world.setMessage("Estimated tank count: " + Math.abs(tankCount) + "\nActual tank count at start: " + trueTankCount);

	}
	
	public static void decreaseTanks() {
		tankCount--;
		world.setMessage("Estimated tank count: " + Math.abs(tankCount) + "\nActual tank count at start: " + trueTankCount);

	}
}
