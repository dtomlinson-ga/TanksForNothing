import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.awt.Color;


public class TankWorld {

	public static void main (String[] args) {
		
		ActorWorld world = new ActorWorld();
		world.setGrid(new BoundedGrid<Actor>(10, 10));
		
		world.add(new Location(0, 0), new Tank(Color.GRAY, true, 15));

		world.show();
	}
}
