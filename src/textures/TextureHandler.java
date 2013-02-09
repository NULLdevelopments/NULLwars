package textures;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class TextureHandler {
	private int bitWidth, bitHeight, spriteWidth, spriteHeight, hFrames, vFrames, frames;
	private double currentFrame, rate;
	private boolean finished, centered;
	private Rect spriteRect, destRect;
	private Bitmap bitmap;
	
	public TextureHandler(Bitmap bitmap, int hFrames, int vFrames, double rate){
		this.hFrames=hFrames;
		this.vFrames=vFrames;
		this.bitmap=bitmap;
		this.rate=rate;
		spriteWidth=bitWidth=bitmap.getWidth()/this.hFrames;
		spriteHeight=bitHeight=bitmap.getHeight()/this.vFrames;
		
		frames = (int)(hFrames*vFrames); //good time to start
		spriteRect = new Rect(0,0,bitWidth,bitHeight);
		destRect = new Rect();
	}
	public void animate(){
		if (currentFrame+rate < frames) currentFrame+=rate;
		else{
			finished = true;
			currentFrame = 0;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	public void animate(int frame){
		//adjust sprite location
		spriteRect.top = (((int)frame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)frame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	public void animate(int start, int end){
		end++;
		//animates between a certain frame
		if (currentFrame < start) currentFrame = start;
		if (currentFrame+rate < end) currentFrame+=rate;
		else{
			finished = true;
			currentFrame = start;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	public void update(double x, double y){
		//texture placement
		if (centered){
			destRect.top = (int)(y-(bitHeight/2));
			destRect.bottom = destRect.top + bitHeight;
			destRect.left = (int)(x-(bitWidth/2));
			destRect.right = destRect.left + bitWidth;
		}
		else{
			destRect.top = (int)y;
			destRect.bottom = destRect.top + bitHeight;
			destRect.left = (int)x;
			destRect.right = destRect.left + bitWidth;
		}
	}
	public void update(double x, double y, int xSize, int ySize){
		//texture placement
		if (centered){
			destRect.top = (int)(y-(ySize/2));
			destRect.bottom = destRect.top + ySize;
			destRect.left = (int)(x-(xSize/2));
			destRect.right = destRect.left + xSize;
		}
		else{
			destRect.top = (int)y;
			destRect.bottom = destRect.top + ySize;
			destRect.left = (int)x;
			destRect.right = destRect.left + xSize;
		}
	}
	public void reflect(){
		int oldLeft = spriteRect.left;
		spriteRect.left = spriteRect.right;
		spriteRect.right = oldLeft;
	}
	public void flip(){
		int oldTop = spriteRect.top;
		spriteRect.top = spriteRect.bottom;
		spriteRect.bottom = oldTop;
	}
	public void resize(int bitWidth, int bitHeight){
		this.bitWidth=bitWidth;
		this.bitHeight=bitHeight;
	}
	public void reset(){
		currentFrame=0;
		bitWidth=bitmap.getWidth()/hFrames;
		bitHeight=bitmap.getHeight()/vFrames;
	}
	public Bitmap getBitmap(){ return bitmap; }
	public Rect getDestRect(){ return destRect; }
	public Rect getSpriteRect(){ return spriteRect; }
	public int getBitWidth(){ return bitWidth; }
	public int getBitHeight(){ return bitHeight; }
	public int getHFrames(){ return hFrames; }
	public int getVFrames(){ return vFrames; }
	public int getSpriteWidth(){ return spriteWidth; }
	public int getSpriteHeight(){ return spriteHeight; }
	public double getRate(){ return rate; }
	public void center(){ centered = true; }
	public boolean isCentered(){ return centered; }
	public boolean isFinished(){ return finished; }
	public boolean isAnimating(){ return currentFrame > 0; }
}