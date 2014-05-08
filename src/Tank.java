import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;


public class Tank extends Bug {
	
	private String tankName;
	private boolean aimAcquired;
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
	
	public void move() {

	}
	
	public void aim() {
		int range = 0;
		int checkDirection = 0;
		
		while (checkDirection != 360) {
			while (range < 5) {
				Location loc = new Location(getLocation().getRow(), getLocation().getCol());
				if (getGrid().isValid(getLocation().getAdjacentLocation(checkDirection))) {
	//				if ()
					range++;
				}
			}
			
			checkDirection += 45;
		}
	}
	
	public void fire() {
		int bulletDirection = getDirection();
		
		if (getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
			Bullet bullet = new Bullet(bulletDirection);
			bullet.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(getDirection()));
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

	public void act() {
		if (turnNumber == 6) {
			if (canMove()) {
				super.move();
			}
			else turn();
			turnNumber = 0;
		}
//		aim();
//		if (aimAcquired) fire();
//		else turn();
		turnNumber++;
		
		if(turnNumber != 6) fire();
		
		//System.out.println(getLocation().getRow() + ", " + getLocation().getCol());
	}
	
}