import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;


public class Bullet extends Bug{

	public Bullet() {
		setDirection(0);
		setColor(Color.GRAY);
	}
	
	public Bullet(int direction) {
		setDirection(direction);
		setColor(Color.GRAY);
	}
	
	public void act() {
		if (!canMove()) {
			Location loc = getLocation().getAdjacentLocation(getDirection());
			
			if(getGrid().isValid(loc) && getGrid().get(loc) != null) {
				Actor target = getGrid().get(loc);
				target.removeSelfFromGrid();
			}
			removeSelfFromGrid();
		} else {
			move();
		}
		
//			ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
//			
//			for (Actor a : neighbors) {
//				a.removeSelfFromGrid();
//			}
//			removeSelfFromGrid();
	}
	
//	@Override
//	public void act() {
//		while (getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
//			move();
//		}
//		
//		if (!getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
//			ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
//
//			for (Actor a : neighbors) {
//				a.removeSelfFromGrid();
//			}
//		
//			removeSelfFromGrid();
//			System.out.println("removed");
//		}
//		
//	}
	
	public void move() {
        if (getGrid() == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (getGrid().isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
	}
	

}
