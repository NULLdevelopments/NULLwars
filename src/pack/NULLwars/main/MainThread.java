package pack.NULLwars.main;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

	static SurfaceHolder surfaceHolder;
	static MainGamePanel gamePanel;
	static Canvas canvas;
	// flag to hold game state
	private boolean running;

	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		MainThread.surfaceHolder = surfaceHolder;
		MainThread.gamePanel = gamePanel;
	}
	public void run() {
		while (running) {	
			try {
				synchronized (surfaceHolder) {
					Counter.StartFPSCounter(); //start counter
					canvas = MainThread.surfaceHolder.lockCanvas();
					gamePanel.draw(canvas);
					gamePanel.update();
					//saves battery life, lowers fps
					try {Thread.sleep(5);} //0 does nothing, try 15 to slow the fps
					catch (InterruptedException e) {e.printStackTrace();}
					Counter.StopAndPostFPS(); //calculate time
				}
			}
			finally {
				if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	public void setRunning(boolean running) {this.running = running;}
}
