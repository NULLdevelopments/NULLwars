package pack.NULLwars.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;

public class Record {
	Context context;
	AlertDialog.Builder dlgAlert;
	
	public Record(Context context){this.context = context;}
	public void saveHighScore(){
    	SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
    	Editor editor = prefs.edit();
    	//editor.putBoolean("highScore", MainGamePanel.player.getHighScore());
    	editor.commit();
	}
	public void loadHighScore(){
		/*SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
		if (prefs.getInt("highScore", 0) <= 0) {
			displayMessage("Hello Programmer","Look at your Record.java class to change this message");
		}
		else{
			//MainGamePanel.player.setHighScore(prefs.getInt("highScore"));
		}*/
	}
	public void displayMessage(String title, String message){
		dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("Continue", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create();
        dlgAlert.show();
        dlgAlert.setPositiveButton("Continue",
        new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {Looper.prepare();}});
	}
}