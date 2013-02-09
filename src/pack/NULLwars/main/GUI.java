package pack.NULLwars.main;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GUI {
	private String debug;
	
	public GUI() {}
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		
		debug = "FPS: "+Counter.getFPS()+" ("+MainGamePanel.game.getX()+","+MainGamePanel.game.getY()+")";
		for (int i = 4; i < 7; i++){
			for (int j = 17; j < 21; j++){
				canvas.drawText(debug, i, j, paint);
			}
		}
		paint.setColor(Color.WHITE);
		canvas.drawText(debug, 5, 18, paint);
	}
	public void update(){
		
	}
}