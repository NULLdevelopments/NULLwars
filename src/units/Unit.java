package units;

import android.graphics.Bitmap;
import pack.NULLwars.main.MainGamePanel;
import textures.TextureHandler;

public class Unit {
	/*
	* #yoloswagforjesus
	*
	* Currently implemented unit types...
	* 10 = Infantry, 11 = Rocket Infantry, 12 = Sniper 13 = Heavy infantry; 
	* 20 = Vehicle base,21= Heavy gunner 22 = mech walker;
	* 30 = base air, 31 = super fighter;
	*  
	*  To implement a new type copy and paste the code from an already defined class
	*  and give it the values you want. Follow the current pattern when redefining the 
	*  switch case, ie if its a new troop type give it the int 14... and so on. Then,
	*  update typeToString
	*/
	private int type; // what type of unit it is, see implemented 
	private int team; // whose team is it on, 0 or 1.
	private int xCoord;
	private int yCoord;
	private int cost; // the cost to the player
	private int hp;  // the hp of an individual unit
	private int totalHp;// hp of the squad as a whole   
	private int intialHp;// the hp of the squad at intialization
	private int armor;// the armor of the squad used to reduce damage in batl
	private int nUnits; // how many units are  in the squad
	private int vision; // how far can it see
	private int movement;// how far can it move
	private boolean hasMoved = false;
	private int movCost;// how much fuel does movement cost per block 
	private int attack; // how much damage does the squad do per unit
	private int attMin; // the minimum attack range
	private int attMax; // the max attack range
	private int fuel; // how much fuel can it hold
	private int rank;// its rank, used to buff there states
	private boolean alive; // is it alive?
	private TextureHandler sprite;
	
	//makes a new unit based on input from the game engine
	public Unit(int t, int player, int x, int y){
	//type team rank and alive are will all be the same reguardless of type
	// so they are handled first.
		type = t;
		team = player;
		xCoord = x;
		yCoord = y;
		rank = 0;
		alive = true;
		setup(); // does all of the work by setting each statistic to its appropriate value
		totalHp= hp*nUnits; // calculates the total hp based on the squad after its setup
		intialHp = totalHp;//gets us the intialHp for later use.
	}
	//
	private void setup(){
		reset();
		switch (type) {
			case 10: // Basic Infantry 
				cost = 100;
				hp = 10;
				armor = 10;
				nUnits = 5;
				vision = 5;
				movement = 5;
				movCost = 1;
				attack = 10;
				attMin = 1;
				attMax = 1;
				fuel = 30;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 11: // Rocket Infantry
				cost = 150;
				hp = 25;
				armor = 10;
				nUnits = 2;
				vision = 5;
				movement = 5;
				movCost = 1;
				attack = 35;
				attMin = 1;
				attMax = 1;
				fuel = 30;	
				sprite = MainGamePanel.texture.rocketinfantry;
				break;
			case 12: // Sniper
				cost = 150;
				hp = 15;
				armor = 10;
				nUnits = 1;
				vision = 5;
				movement = 5;
				movCost = 1;
				attack = 25;
				attMin = 3;
				attMax = 3;
				fuel = 30;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 13: // Heavy Infantry 
				cost = 200;
				hp = 35;
				armor = 20;
				nUnits = 2;
				vision = 5;
				movement = 5;
				movCost = 1;
				attack = 35;
				attMin = 3;
				attMax = 3;
				fuel = 30;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 20:// Base vehicle
				cost = 500;
				hp = 150;
				armor = 25;
				nUnits = 1;
				vision = 5;
				movement = 3;
				movCost = 10;
				attack = 40;
				attMin = 1;
				attMax = 1;
				fuel = 150;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 21:// Heavy Gunner
				cost = 550;
				hp = 175;
				armor = 30;
				nUnits = 1;
				vision = 5;
				movement = 2;
				movCost = 10;
				attack = 45;
				attMin = 1;
				attMax = 1;
				fuel = 150;		
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 22:// walker
				cost = 600;
				hp = 200;
				armor = 35;
				nUnits = 1;
				vision = 5;
				movement = 2;
				movCost = 10;
				attack = 50;
				attMin = 1;
				attMax = 1;
				fuel = 150;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 30://base fighter
				cost = 200;
				hp = 50;
				armor = 15;
				nUnits = 2;
				vision = 10;
				movement = 10;
				movCost = 10;
				attack = 30;
				attMin = 1;
				attMax = 1;
				fuel = 250;		
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			case 31:// super fighters
				cost = 500;
				hp = 200;
				armor = 30;
				nUnits = 1;
				vision = 10;
				movement = 15;
				movCost = 10;
				attack = 35;
				attMin = 1;
				attMax = 1;
				fuel = 300;
				sprite = MainGamePanel.texture.basicinfantry;
				break;
			default: 
				type = 10;
				setup();
				//System.err.print("YOU FAIL");
				break;
		}
	}
	// getters
	public int getTeam(){
		return team;
	}
	public int getType(){
		return type;
	}
	public int getX(){
		return xCoord;
	}
	public int getY(){
		return yCoord;
	}
	public int getCost(){
		return cost;
	}
	public int getArmor(){
		return armor;
	}
	public int getUnits(){
		return nUnits;
	}
	public int getVision(){
		return vision;
	}
	public int getMovement(){
		return movement;
	}
	public int getMovCost(){
		return movCost;
	}
	public int getAttack(){
		return attack;
	}
	public int getAttMin(){
		return attMin;
	}
	public int getAttMax(){
		return attMax;
	}
	public int getFuel(){
		return fuel;
	}
	public int getRank(){
		return rank;
	}
	public boolean isAlive(){
		return alive;
	}
	public int getHealth(){
		return totalHp;
	}
	public boolean getHasMoved(){
		return hasMoved;
	}
	public int getGeneralType(){
		return (type/10)*10;
	}
	public void updateRank(){
		if(rank <= 4){
			rank++;
			totalHp = (int)(totalHp + (intialHp * .1));
			armor = (int)(armor + (armor * .1));
			attack = (int)(attack + (attack * .1));
		}else{
			return;
		}
    }
	private int calcUnits(){
		if(totalHp > 0){
			nUnits = (int)((totalHp/intialHp) + 1);
			return nUnits;
		}
		else if(totalHp <= 0) {
			alive = false;
			nUnits = 0;
			return 0;
		}
			return nUnits;
	}
	public int baseAttack(){
		return (attack * nUnits);
	}
	public void setHealth(int h){
		totalHp = h;
		calcUnits();
		
	}
	public void setFuel(int f){
		fuel = f;
		
	}
	public void setCoord(int x, int y){
		xCoord = x;
		yCoord = y;
		
	}
	public void setHasMoved(boolean foo){
		hasMoved = foo; 
	}
	public void reset(){
		cost = 500;
		hp = 200;
		armor = 30;
		nUnits = 1;
		vision = 10;
		movement = 15;
		movCost = 10;
		attack = 35;
		attMin = 1;
		attMax = 1;
		fuel = 300;
	}
	public Bitmap getBitmap(){ return sprite.getBitmap(); }
	public TextureHandler getSprite(){ return sprite; }
	public void setSprite(TextureHandler sprite){ this.sprite=sprite; }
	public String typeToString(){
		String t = "";
		switch (type) {
		case 10: // Basic Infantry 
			t = "Basic Infantry ";
			break;
		case 11: // Rocket Infantry	
			t = "Rocket Infantry";
			break;
		case 12: // Sniper
			t = "Sniper";
			break;
		case 13: // Heavy Infantry 
			t = "Heavy Infantry";
			break;
		case 20:// Base vehicle
			t = "Base Vehicle";
			break;
		case 21:// Heavy Gunner
			t = "Heavy Gunner";
			break;
		case 22:// walker
			t = "Walker";
			break;
		case 30://base fighter
			t = "Base Fighter";
			break;
		case 31:// super fighters
			t = "Super Fighters";
			break;
		default: 
			System.err.print("YOU FAIL");
			break;
	}
		return t;
		
		
	}
}