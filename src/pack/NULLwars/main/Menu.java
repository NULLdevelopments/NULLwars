package pack.NULLwars.main;

import buttons.Button;
import android.content.Context;
import android.graphics.Canvas;

public class Menu {
	Button start;
	Context context;
	public Menu(Context context){ this.context=context; }
	public void draw(Canvas canvas){
		if (start == null){
			start = new Button(MainGamePanel.texture.start,canvas.getWidth()/2,canvas.getHeight()/2,true, context);
			start.resize(256, 128);
		}
		start.draw(canvas);
	}
	public void update(){
		
	}
	public void down(int x, int y){
		start.down(x, y);
	}
	public void move(int x, int y){
		start.down(x, y);
	}
	public void up(int x, int y){
		if (start.up(x, y)) MainGamePanel.adjustScreen(1);
	}
	public void reset(){
		start.reveal();
	}
}
