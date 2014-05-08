import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public  class InputActor extends Actor implements KeyEventDispatcher {
	
	public InputActor() {
		super();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent key) {
		
		int keyPressed = key.getKeyCode();
		switch(keyPressed) {
			case(KeyEvent.VK_W):
				setDirection(Location.NORTH);
				return true;
			case(KeyEvent.VK_S):
				setDirection(Location.SOUTH);
				System.out.println("S");
				return true;
			case(KeyEvent.VK_A):
				setDirection(Location.LEFT);
				System.out.println("L");
				return true;
			case(KeyEvent.VK_D):
				setDirection(Location.RIGHT);				
				System.out.println("R");
				return true;
			default:
				return false;
		}		
		
	}


		
}
