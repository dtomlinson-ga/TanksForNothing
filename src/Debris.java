import info.gridworld.actor.Actor;

import java.awt.Color;


public class Debris extends Actor {
	
	public Debris() {
		setColor(Color.RED);
	}
	
	@Override
	public void act() {
		if (getColor().getRed() > 5) {
			setColor(new Color(getColor().getRed()-5, getColor().getGreen(), getColor().getBlue()));
		}
		
		if (getColor().getRed() == 0 || getColor().getAlpha() > 0) {
			setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), getColor().getAlpha()-5));
		}
		
		if (getColor().getAlpha() == 0)
			removeSelfFromGrid();
	}

}
