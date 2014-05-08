import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;

import java.awt.Color;
import java.util.ArrayList;


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
		super.act();
		if (!canMove()) {
			ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
			for (Actor a : neighbors) {
				a.removeSelfFromGrid();
			}
			removeSelfFromGrid();
		}
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
	
//	public void move() {
//		getLocation();
//		getGrid().put(getLocation().getAdjacentLocation(getDirection()), new Bullet(getDirection()));
//		removeSelfFromGrid();
//	}
	

}
