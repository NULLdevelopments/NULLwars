package pack.NULLwars.main;

import textures.Textures;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import audio.AudioHandler;

@TargetApi(5)
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
	static boolean pause;
	private static int width;
	private static int height;
	private static int screen;
	public static MainThread thread;
	public static Context context;
	public static Record record;
	public static Textures texture;
	public static Game game;
	public static Menu menu;
	public static AudioHandler audio;

	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		//initiate objects
		thread = new MainThread(getHolder(), this);
		audio = new AudioHandler(context);
		//audio.playMusic(R.raw.theme2); //play music
		texture = new Textures(context);
		record = new Record(context);
		game = new Game(context);
		menu = new Menu(context);
		setFocusable(true);
	}
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		//adjust universal width
		if (width != canvas.getWidth() || height != canvas.getHeight()){
			width = canvas.getWidth();
			height = canvas.getHeight();
		}
		//draws the other objects with the SAME 'canvas'
		switch (screen){
			case (0): 
				menu.draw(canvas);
			break;
			case (1):
				game.draw(canvas);
			break;
		}
	}
	public void update(){
		switch (screen){
			case (0):
				menu.update();
			break;
			case (1):
				game.update();
			break;
		}
	}
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
		case (MotionEvent.ACTION_DOWN):
			//do stuff when the screen is FIRST touched
			switch (screen) {
				case (0): menu.down((int)(event.getX()), (int)(event.getY()));
				break;
				case (1): game.down((int)(event.getX()), (int)(event.getY()));
				break;
			}
			try {MainThread.sleep(5);} 
			catch (InterruptedException e) {e.printStackTrace();}
		break;
		case (MotionEvent.ACTION_UP):
			//do stuff when the finger is REMOVED off the screen
			switch (screen) {
				case (0): menu.up((int)(event.getX()), (int)(event.getY()));
				break;
				case (1): game.up((int)(event.getX()), (int)(event.getY()));
				break;
			}
			try {MainThread.sleep(5);} 
			catch (InterruptedException e) {e.printStackTrace();}
		break;
		case (MotionEvent.ACTION_MOVE):
			int num = event.getPointerCount();
			int x[] = new int[2];
			int y[] = new int[2];
			
		    for (int i = 0; i < num; i++) {
		      x[i] = (int) event.getX(event.getPointerId(i));
		      y[i] = (int) event.getY(event.getPointerId(i));
		    }
		    if (x.length < 2){
		    	x[1] = 0;
		    	y[1] = 0;
		    }
			//do stuff WHILE the screen is touched
			switch (screen) {
				case (0): menu.move((int)(event.getX()), (int)(event.getY()));
				break;
				case (1): game.move(x[0], y[0], x[1], y[1]);
				break;
			}
			try {MainThread.sleep(5);} 
			catch (InterruptedException e) {e.printStackTrace();}
		break;
		}
		return true;
	}
	public void setPause(boolean pause){ MainGamePanel.pause=pause; }
	public static boolean isPaused(){ return pause; }
	public static int getCanvasWidth(){ return width; }
	public static int getCanvasHeight(){ return height; }
	public static void adjustScreen(int newScreen){ screen=newScreen; }
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {}
	public void surfaceCreated(SurfaceHolder holder) { thread.setRunning(true);thread.start(); }
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {thread.join();	retry = false;
			} catch (InterruptedException e) {}
		}
	}
	
}
