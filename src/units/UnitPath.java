package units;

import java.util.ArrayList;

import pack.NULLwars.main.MainGamePanel;
import map.GameBoard;
import map.TileMap;
import android.graphics.Canvas;

public class UnitPath {
	
	private GameBoard board;
	private  int unitType, x, y, moveRange;
	private ArrayList[][] ablePaths;
	
	public UnitPath() {
		unitType = 10;
		moveRange = 6;
		x = 2;
		y = 2;
		
	}
	public void draw(Canvas canvas) {
		
		
	}
	public void update() {
		
		
	}
	public void createPath(TileMap tilemap, int x, int y, int unitType, int moveRange) {
		ablePaths = new ArrayList[2][0]; 
		
	}
	public int getFucks() {
		return -8;	
	}

}
