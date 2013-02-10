package units;

import java.util.LinkedList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


public class UnitHandler {
	LinkedList<Unit> unitList;
	//UnitStats stats;
	private int currentIndex;
	
	public UnitHandler(){

		unitList = new LinkedList<Unit>();
		//stats = new UnitStats();
		addRandomUnits();
	}

	public void draw(Canvas canvas, int x, int y, int blockSize, boolean alias) {
		Paint paint = new Paint();
		paint.setAntiAlias(alias);
		paint.setFilterBitmap(alias);
		paint.setDither(alias);

		// draw every unit and animate idle stance
		for (int i = 0; i < unitList.size(); i++) {
			unitList.get(i).getSprite().animate(0, 3); // animate first 4 frames
			unitList.get(i)
					.getSprite()
					.update(x + (unitList.get(i).getX() * blockSize),
							y + (unitList.get(i).getY() * blockSize),
							blockSize, blockSize);
			canvas.drawBitmap(unitList.get(i).getBitmap(), unitList.get(i)
					.getSprite().getSpriteRect(), unitList.get(i).getSprite()
					.getDestRect(), paint);
		}
		//unit stats
		//stats.draw(canvas);
	}

	public void update() {

	}

	public void add(int type, int team, int x, int y) {
		unitList.add(new Unit(type, team, x, y));
	}

	public void remove(int i) {
		unitList.remove(i);
	}

	public void removeAll() {
		int size = unitList.size();
		for (int i = 0; i < size; i++) {
			unitList.remove(0);
		}
	}

	public void addRandomUnits() {
		for (int i = 0; i < 5; i++) {
			add(10 + (int) (Math.random() * 2), 0, (int) (Math.random() * 128),
					(int) (Math.random() * 128));
		}
	}

	public int getUnitType(int x, int y){
		int type = -1;
		for(int i = 0; i<unitList.size(); i++ ){
			 if(unitList.get(i).getX() == x && unitList.get(i).getY() == y){
				 type = unitList.get(i).getType();
				 break;
			 }
		}
		return type;
	}
	public int getUnitIndex(int x, int y){
		int index = -1;
		for(int i = 0; i<unitList.size(); i++){
			if(unitList.get(i).getX() == x && unitList.get(i).getY() == y){
				index = i;
			}
		}
		return index;
	}
	public void moveUnit(int index, int x, int y){
		//will check unit path
		unitList.get(index).setCoord(x, y);
	}
}
