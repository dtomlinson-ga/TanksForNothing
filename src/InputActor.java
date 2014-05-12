import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class InputActor extends Actor implements KeyEventDispatcher {
	
	private int actionKey;
	protected boolean actionButtonPressed;
	protected boolean isPlayer;
	
	public InputActor(boolean isPlayer, int actionKey) {
		super();
		this.actionButtonPressed = false;
		this.isPlayer = isPlayer;
		this.actionKey = actionKey;
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent key) {
		if(isPlayer) {
			int keyPressed = key.getKeyCode();
			
			if(keyPressed == actionKey) {
				actionButtonPressed = true;
			} else {
				actionButtonPressed = false;
			}
				
			switch(keyPressed) {
				case(KeyEvent.VK_W):
					setDirection(Location.NORTH);
					return true;
				case(KeyEvent.VK_S):
					setDirection(Location.SOUTH);
					return true;
				case(KeyEvent.VK_A):
					setDirection(Location.LEFT);
					return true;
				case(KeyEvent.VK_D):
					setDirection(Location.RIGHT);				
					return true;
				default:
					return false;
			}	
		}

		return false;
	}
	
	
	protected void die() {
		onDeath();
	}
	
	protected void onDeath() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
		
	}
}
