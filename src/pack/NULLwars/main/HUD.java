package pack.NULLwars.main;

import java.util.ListIterator;

import units.Unit;

public class HUD {
	private int attack, hp, movement;
	private Unit unit;
	
	public HUD() {
		this.attack = unit.getAttack();
		this.hp = unit.getHealth();
		this.movement = unit.getMovement();
	}
	
	
	// Update stats before we display them to the screen
	//  by iterating through
	//public void updateStats() {
		//unit = Game.
	//}
}
