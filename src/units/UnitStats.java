package units;

import pack.NULLwars.main.MainGamePanel;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class UnitStats {
	Unit unit;
	public UnitStats(){
		
	}
	public void draw(Canvas canvas, boolean alias){
		Paint paint = new Paint();
		Paint paint1 = new Paint();
		paint1.setColor(Color.rgb(175, 160, 160));
		paint.setAntiAlias(alias);
		paint.setFilterBitmap(alias);
		paint.setDither(alias);
		
		if (unit != null){
			canvas.drawRect(MainGamePanel.getCanvasWidth()-135, 0, MainGamePanel.getCanvasWidth(), 65, paint1);
			//canvas.drawText("Team: " + unit.getTeam(), MainGamePanel.getCanvasWidth()-120, 72, paint);
			canvas.drawText("HP: "+unit.getHealth(), MainGamePanel.getCanvasWidth()-120, 12, paint);
			canvas.drawText("Attack: "+unit.getAttack(), MainGamePanel.getCanvasWidth()-120, 24, paint);
			canvas.drawText("Armor: "+unit.getArmor(), MainGamePanel.getCanvasWidth()-120, 36, paint);
			canvas.drawText("Move: "+unit.getMovement(), MainGamePanel.getCanvasWidth()-120, 48, paint);
			canvas.drawText("Fuel: "+unit.getFuel(), MainGamePanel.getCanvasWidth()-120, 60, paint);
			
		}
	}
	public void setUnit(Unit unit){
		this.unit = unit;
	}
}
