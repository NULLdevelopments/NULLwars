package buttons;

import pack.NULLwars.main.MainGamePanel;
import textures.TextureHandler;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;

public class Button {
	private boolean pressed, hide;
	private TextureHandler sprite;
	private int x, y, xSize, ySize, padding;
	private Vibrator vibrator;
	
	public Button(TextureHandler newSprite, int x, int y, boolean center, Context context){
		//basic button
		this.x=x;
		this.y=y;
		sprite = new TextureHandler(newSprite.getBitmap(), 
				newSprite.getHFrames(), newSprite.getVFrames(), newSprite.getRate());
		xSize = sprite.getBitWidth();
		ySize = sprite.getBitHeight();
		if (center) sprite.center();
		sprite.update(x, y);
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE) ;
		padding = 12; //adjust to player's fingers
	}
	public void draw(Canvas canvas){	
		if (!hide) canvas.drawBitmap(sprite.getBitmap(), sprite.getSpriteRect(), sprite.getDestRect(), null);
	}
	public void draw(int fade, Canvas canvas){	
		Paint paint = new Paint();
		paint.setARGB(fade, 255, 255, 255);
		if (!hide) canvas.drawBitmap(sprite.getBitmap(), sprite.getSpriteRect(), sprite.getDestRect(), paint);
	}
	public void resize(int newWidth, int newHeight){
		xSize=newWidth;
		ySize=newHeight;
		sprite.resize(newWidth, newHeight);
		sprite.update(x, y);
	}
	public boolean down(int x1, int y1){
		if (!hide){
			if (Math.abs(x1-x) < ((xSize/2)+padding) && Math.abs(y1-y) < ((ySize/2)+padding)){
				if (!pressed) vibrator.vibrate(25);
				pressed = true;
				sprite.animate(1);
			}
			else{
				pressed = false;
				sprite.animate(0);
			}
		}
		return pressed;
	}
	public boolean move(int x, int y){
		return false;
	}
	public boolean up(int x, int y){ 
		if (pressed){
			sprite.animate(0);
			pressed = false;
			return true;
		}
		else return false; 
	}
	public void setPadding(int padding){ this.padding=padding; }
	public void hide(){ hide = true; }
	public void reveal(){ hide = false; }
	public void update(int x, int y){ 
		this.x=x;
		this.y=y;
		sprite.update(x, y); 
	}
	public boolean isPressed(){ return pressed; }
	public void centerX(){ 
		if (x != MainGamePanel.getCanvasWidth()){
			x = MainGamePanel.getCanvasWidth(); 
			sprite.update(x,y);
		}
	}
	public void centerY(){ 
		if (y != MainGamePanel.getCanvasHeight()){
			y = MainGamePanel.getCanvasHeight(); 
			sprite.update(x,y);
		}
	}
}
