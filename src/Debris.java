import info.gridworld.actor.Actor;

import java.awt.Color;


public class Debris extends Actor {
	
	public Debris() {
		setColor(Color.RED);
	}
	
	@Override
	public void act() {
		if (getColor().getRed() > 20) {
			setColor(new Color(getColor().getRed()-4, getColor().getGreen(), getColor().getBlue()));
		}
	}

}
