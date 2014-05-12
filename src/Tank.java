import info.gridworld.grid.Location;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank extends InputActor {
	
	private int turnNumber;
	private int speed;
	
	private static Random random = new Random();

	public Tank() {
		this(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)), false, 3, 0);
		setDirection(random.nextInt(4) * 90);	
	}
	
	/**
	 * Tank Constructor
	 * 
	 * @param color - The color to use
	 * @param isPlayer - If the tank player or ai controlled
	 * @param speed - Sets how many turns the tank will wait before moving
	 * 
	 */
	public Tank(Color color, boolean isPlayer, int speed, int direction) {
		super(isPlayer, KeyEvent.VK_F);
		setColor(color);
		
		this.isPlayer = isPlayer;
		
		this.turnNumber = 0;
		this.speed = speed;
	}

	
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
			updateAi();
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
	
	private boolean canMove() {
		Location loc = getLocation().getAdjacentLocation(getDirection());
		if(getGrid().isValid(loc) && getGrid().get(loc) == null) {
			return true;
		}
		
		return false;
	}		

	private Location target;
	private void updateAi() {
		Location newTarget = getLocationToFireAt();
		if(newTarget != null && !newTarget.equals(target)) {
			target = newTarget;
			fire();
		}
		
		turnRandomly();

	}
	
	private Location getLocationToFireAt() {
		Location defaultLocation = getLocation();
		Location loc = getLocation();
		
		switch(getDirection()) {
			case(Location.NORTH):
				for(int i = 1; getGrid().isValid(loc); ++i) {
					loc = new Location(defaultLocation.getRow() - i, defaultLocation.getCol());
					if(getGrid().isValid(loc) && getGrid().get(loc) instanceof Tank) {
						return loc;
					}
				}
				return null;
			case(Location.EAST):
				for(int i = 1; getGrid().isValid(loc); ++i) {
					loc = new Location(defaultLocation.getRow(), defaultLocation.getCol() + i);
					if(getGrid().isValid(loc) && getGrid().get(loc) instanceof Tank) {
						return loc;
					}
				}
				return null;
			case(Location.SOUTH):
				for(int i = 1; getGrid().isValid(loc); ++i) {
					loc = new Location(defaultLocation.getRow() + i, defaultLocation.getCol());
					if(getGrid().isValid(loc) && getGrid().get(loc) instanceof Tank) {
						return loc;
					}
				}
				return null;
			case(Location.WEST):
				for(int i = 1; getGrid().isValid(loc); ++i) {
					loc = new Location(defaultLocation.getRow(), defaultLocation.getCol() - i);
					if(getGrid().isValid(loc) && getGrid().get(loc) instanceof Tank) {
						return loc;
					}
				}
				return null;
			default:
				return null;
		}		
	}
	
	private final int chanceToTurn = 5;
	private void turnRandomly() {
		if(random.nextInt(100) < chanceToTurn) {
			int newDirection = random.nextInt(4);
			setDirection(newDirection * 90);
		}
	}
	
	/**
	 * Fires a bullet in the direction the tank is facing 
	 */
	private void fire() {
		int direction = getDirection();
		Location adjacentLocation = getLocation().getAdjacentLocation(direction);
			
		if (getGrid().isValid(adjacentLocation)) {
			Bullet bullet = new Bullet(direction);
			bullet.putSelfInGrid(getGrid(), adjacentLocation);
		}
	}
	
	@Override
	protected void die() {
		super.onDeath();
		removeSelfFromGrid();
		TankWorld.decreaseTanks();
	}	 	
}
