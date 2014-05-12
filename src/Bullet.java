import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Bullet extends Actor {

	public Bullet() {
		this(0);
	}
	
	public Bullet(int direction) {
		setDirection(direction);
		setColor(Color.GRAY);
	}
	
	@Override
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
	}
	
	private boolean canMove() {
		Location loc = getLocation().getAdjacentLocation(getDirection());
		
		if(getGrid().isValid(loc) && getGrid().get(loc) == null) {
			return true;
		}
		
		return false;
	}
	
	private void move() {
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
