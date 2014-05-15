import info.gridworld.actor.Actor;

import java.awt.Color;


public class Debris extends Actor {
	
	private int countDown;
	
	public Debris() {
		setColor(Color.RED);
		countDown = 10;
	}
	
	@Override
	public void act() {
		if(countDown > 0) {
			countDown--;
			setColor(new Color(getColor().getRed(), getColor().getGreen()+25, getColor().getBlue()+25));
		} else {
			removeSelfFromGrid();
		}
	}
}
