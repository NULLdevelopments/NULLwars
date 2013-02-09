package pack.NULLwars.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MyGameActivity extends Activity {
	static MainGamePanel game;
    public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
        		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        game = new MainGamePanel(this);
        MainGamePanel.record.loadHighScore();
        setContentView(game);
    }
    public void onPause(){
    	//pause or kills game
    	Counter.reset();
    	//MainGamePanel.adjustScreen(0);
    	MainGamePanel.record.saveHighScore();
    	MainGamePanel.pause = false;
    	MainGamePanel.audio.stopAll();
    	MainGamePanel.thread.setRunning(false);
    	android.os.Process.killProcess(android.os.Process.myPid()); //not recommended
    	finish();
    	super.onPause();
    }
    public void onResume(){
    	super.onResume();
    }
    public void onStop() {
    	finish();
		super.onStop();
	}
	public void onDestroy() {
		finish();
		super.onDestroy();
	}
}