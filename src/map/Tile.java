package map;

public class Tile {
	private int x, y, type;
	private boolean occupied;
	
	public Tile(int type){
		this.type=type;
	}
	public void setX(int x){ this.x=x; }
	public void setY(int y){ this.y=y; }
	public void setType(int type){ this.type=type; }
	public void setOccupied(boolean occupied){ this.occupied=occupied; }
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getType(){ return type; }
	public boolean isOccupied(){ return occupied; }
}
