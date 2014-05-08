import info.gridworld.grid.Location;

import java.awt.Color;


public class Tank extends InputActor {
	
	private String tankName;
//	private boolean aimAcquired;
	private int turnNumber = 0;
	
	public Tank() {
		super();
		setColor(Color.GRAY);
		tankName = "NPC";
		
	}
	
	public Tank(Color color, String name) {
		super();
		setColor(color);
		tankName = name;

	}
	
	public String getName() {
		return tankName;
	}
	
	
	/*public void aim() {
		int range = 0;
		int checkDirection = 0;
		
		while (checkDirection != 360) {
			while (range < 5) {
				Location loc = getLocation();
				if (getGrid().isValid(getLocation().getAdjacentLocation(checkDirection))) {
					range++;
				}
			}
			
			checkDirection += 45;
		}
	}*/
	
	
	public void fire() {
		int direction = getDirection();
		
		if (getGrid().isValid(getLocation().getAdjacentLocation(direction))) {
			Bullet bullet = new Bullet(direction);
			bullet.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(direction));
		}
	}
	
	public int getXOffset() {
		if (getDirection() == 0 || getDirection() == 180 )return 0;
		else if (getDirection() == 45 || getDirection() == 90 || getDirection() == 135) return 1;
		else return -1;
	}
	
	public int getYOffset() {
		if (getDirection() == 90 || getDirection() == 270 )return 0;
		else if (getDirection() == 315 || getDirection() == 0 || getDirection() == 45) return -1;
		else return 1;
	}

	@Override
	public void act() {
		if (turnNumber == 5) {
				move();
		}
		
		if (turnNumber == 9) {
			fire();
			turnNumber = 0;
		}
//		aim();
//		if (aimAcquired) fire();
//		else turn();
		turnNumber++;
	}
	
	 public void move() {
        if (getGrid() == null)
            return;
        Location next = getLocation().getAdjacentLocation(getDirection());
        if (getGrid().isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }
	
}
