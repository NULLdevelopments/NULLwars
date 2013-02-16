package pack.NULLwars.main;


/*
 * 
 * COMMENT YOUR FUCKING CODE
 * 
 */
import textures.TextureHandler;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Selector {
	private int x, y;
	private TextureHandler sprite;
	private boolean hide;
	
	public Selector(){
		sprite = MainGamePanel.texture.selector;
		hide = true;
	}
	
	public void draw(Canvas canvas, int x1, int y1, int blockSize, boolean alias){
		Paint paint = new Paint();
		paint.setAntiAlias(alias);
		paint.setFilterBitmap(alias);
		paint.setDither(alias);
		
		if (!hide){
			sprite.animate();
			sprite.update((x1+(x*blockSize)), (y1+(y*blockSize)), blockSize, blockSize);
			canvas.drawBitmap(sprite.getBitmap(), sprite.getSpriteRect(), sprite.getDestRect(), paint);
		}
	}
	
	// The action of touching the screen initially
	public void down(int x1, int y1){
		
	}
	
	// Drag finger on screen
	public void move(int x1, int y1){
		
	}
	
	// Already touching the screen, finger leaves screen
	public void up(int x1, int y1, int blockSize){
		//adjust selector
		if (x1/blockSize < 0) x = 0;
		else if (x1/blockSize > MainGamePanel.game.getCols()-1) x = MainGamePanel.game.getCols()-1;
		else x = (x1/blockSize);
		if (y1/blockSize < 0) y = 0;
		else if (y1/blockSize > MainGamePanel.game.getRows()-1) y = MainGamePanel.game.getRows()-1;
		else y = (y1/blockSize);
		hide = false;
		//moves units with unlimited range

		if (MainGamePanel.game.getUnits().checkForMove(x, y) == true){
			MainGamePanel.game.getUnits().moveSelectedUnit(x, y); //moves the selected unit
		}		
		if (MainGamePanel.game.getUnits().checkForUnit(x, y) == true){ //checks for unit at selector
			MainGamePanel.game.getUnits().setSelectedUnit(x, y); //sets the selected unit and generates it's stats menu
		}
		else {
			MainGamePanel.game.getUnits().clearSelectedUnit(); //clears the selected unit and it''s stats menu

		}
	}
	
	public void setX(int x){ this.x=x; }
	public void setY(int y){ this.y=y; }
	
	public int getX(){ return x; }
	public int getY(){ return y; }
}
