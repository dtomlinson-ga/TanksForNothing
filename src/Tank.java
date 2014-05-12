import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Tank extends InputActor {
	
	private int turnNumber;
	private int speed;
	
	public Tank() {
		this(new Color((int) Math.random() * 255, (int) Math.random() * 255, (int) Math.random() * 255), false, 3);
	}
	
	/**
	 * Tank Constructor
	 * 
	 * @param color - The color to use
	 * @param isPlayer - If the tank is to use player input or ai
	 * @param speed - Sets how many turns the tank will wait before moving
	 * 
	 */
	public Tank(Color color, boolean isPlayer, int speed) {
		super(isPlayer, KeyEvent.VK_F);
		setColor(color);
		
		this.isPlayer = isPlayer;
		
		this.turnNumber = 0;
		this.speed = speed;
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
	
	@Override
	public void act() {
		if(turnNumber == speed) {
			if(canMove()) {
				move();
			}
			turnNumber = 0;
		} else {
			turnNumber++;
		}
		
		if(isPlayer) {
			if(actionButtonPressed) {
				fire();
				actionButtonPressed = false;
			}
		} else {
			processAi();
		}

	}
	
	private void move() {
        if (getGrid() == null) {
            return;
        }    
           
        Location next = getLocation().getAdjacentLocation(getDirection());
        
        if (getGrid().isValid(next)) {
            moveTo(next);
        } else {
        	die();
        }
	}
	 
	/**
	 * Fires a bullet in the direction the tank is facing 
	 */
	private void fire() {
		int direction = getDirection();
			
		if (getGrid().isValid(getLocation().getAdjacentLocation(direction))) {
			Bullet bullet = new Bullet(direction);
			bullet.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(direction));
		}
	}
		
	private void processAi() {
		
	}
	
//	public int getXOffset() {
//			if (getDirection() == 0 || getDirection() == 180 )return 0;
//			else if (getDirection() == 45 || getDirection() == 90 || getDirection() == 135) return 1;
//			else return -1;
//	}
//		
//	public int getYOffset() {
//		if (getDirection() == 90 || getDirection() == 270 )return 0;
//		else if (getDirection() == 315 || getDirection() == 0 || getDirection() == 45) return -1;
//		else return 1;
//	}
	
	private boolean canMove() {
		Location loc = getLocation().getAdjacentLocation(getDirection());
		
		if(getGrid().isValid(loc) && getGrid().get(loc) == null) {
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void die() {
		super.onDeath();
		removeSelfFromGrid();
	}	 	
}
