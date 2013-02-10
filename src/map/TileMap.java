package map;

import pack.NULLwars.main.MainGamePanel;
import textures.TextureHandler;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TileMap {
	private int rows, cols;
	private TextureHandler terrain;
	private Tile[][] board;
	
	public TileMap(int r, int c){
		rows = r;
		cols = c;
		
		board = new Tile[r][c];
		createRandomMap();
		terrain = new TextureHandler(RenderTileMap.render(board,
			MainGamePanel.texture.advancewars), 1, 1, 0.0);
	}
	public void draw(Canvas canvas, int x, int y, int blockSize, boolean alias){
		Paint paint = new Paint();
		paint.setAntiAlias(alias);
		paint.setFilterBitmap(alias);
		paint.setDither(alias);
		//blockSize++;
		terrain.update(x,y,cols*blockSize,rows*blockSize);
		canvas.drawBitmap(terrain.getBitmap(), terrain.getSpriteRect(), terrain.getDestRect(), paint);
	}
	public Tile getTile(int x, int y) {
		return board[x][y];
		
	}
	public void setRows(int rows){ this.rows=rows; }
	public void setCols(int cols){ this.cols=cols; }
	
	public int getRows(){ return rows; }
	public int getCols(){ return cols; }
	
	public void createRandomMap(){
		//initiate each block type
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				board[row][col] = new Tile(0);
			}
		}
		//set random value
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				board[row][col] = new Tile((int)(Math.random()*2));
			}
		}
	}
}
