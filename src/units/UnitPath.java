package units;

import java.util.ArrayList;

import pack.NULLwars.main.MainGamePanel;
import map.GameBoard;
import map.TileMap;
import android.graphics.Canvas;
import android.util.Log;

public class UnitPath {
	
	private GameBoard board;
	private  int unitType, x, y, moveRange;
	private int[][] ablePaths;
	
	public UnitPath() {
		unitType = 10;
		moveRange = 6;
		x = 2;
		y = 2;
		
	}
	public void draw(Canvas canvas, int x, int y, int blockSize, boolean alias) {

		
	}
	public void update() {
		
		
	}
	public void createPath(int x, int y, int unitType, int moveRange) {
		int tempRange = 0;
		int maxBlocks = (2 * (moveRange * moveRange)) + (2 * moveRange) + 1;
		ablePaths = new int[maxBlocks][2]; 
		
		//fill in a diamond formation
		int counter = 0;
		for (int row = -moveRange; row <= moveRange; row++){
			for (int col = -moveRange; col <= moveRange; col++){
				if (col == 0){
					for (int i = -(tempRange); i <= tempRange; i++){
						ablePaths[counter][0] = col+i;
						ablePaths[counter][1] = row;
						//Log.d("x: ", ablePaths[0][0]+"");
						//Log.d("y: ", ablePaths[0][1]+"");
						counter++;
					}
					if (row < 0) tempRange++;
					else if (row >= 0) tempRange--;
					//Log.d("counter: ", counter+"");
				}
			}
		}
		
	}
	public int[][] getPath() {
		return ablePaths;
	}

}
