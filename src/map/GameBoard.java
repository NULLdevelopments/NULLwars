package map;

import android.graphics.Canvas;

public class GameBoard {
	private TileMap tileMap; //block type handler and renderer
	
	public GameBoard(int rows, int cols){
		//construct classes
		tileMap = new TileMap(rows, cols);
	}
	public void draw(Canvas canvas, int x, int y, int blockSize, boolean alias){
		tileMap.draw(canvas, x, y, blockSize, alias);
	}
	public void update(){
		
	}
	public void down(int x1, int y1){
		
	}
	public void move(int x1, int y1){
		
	}
	public void up(int x1, int y1){
		
	}
	public TileMap getTileMap(){ return tileMap; }
}
