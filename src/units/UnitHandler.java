package units;

import java.util.LinkedList;
import android.graphics.Canvas;
import android.graphics.Paint;
// import android.util.Log;
import android.util.Log;


public class UnitHandler {
	LinkedList<Unit> unitList;
	UnitStats stats;
	private int selectedUnitIndex;
	private Unit selectedUnit;
	//private UnitOptions options;
	public UnitHandler(){
		//options = new UnitOptions();
		unitList = new LinkedList<Unit>();
		stats = new UnitStats();

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
		//options.draw(canvas, x, y);
		//unit stats
		if(selectedUnit != null){
			stats.draw(canvas, alias);
		}
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

	public int getUnitIndex(int x, int y){
		selectedUnitIndex = 0;
		for(int i = 0; i<unitList.size(); i++){ //loops through unitList
			if(unitList.get(i).getX() == x && unitList.get(i).getY() == y){ //Checks if unit is at Selector
				selectedUnitIndex = i; //records unit index in unitList
			}
		}
		return selectedUnitIndex;
	}
	

	public void setSelectedUnit(int x, int y){
		selectedUnit = unitList.get(getUnitIndex(x, y)); //copies unit at the Selector into selected unit
		stats.setUnit(selectedUnit); //creates stats for the selected unit
		Log.d("TEST", "UNIT SELECTED");
	}
	
	public void clearSelectedUnit(){
		selectedUnit = null; //empties the selected unit
		stats.setUnit(null); //empties stats of the selected unit
		Log.d("TEST", "UNIT DESELECTED");
	}
	
	public void moveSelectedUnit(int x, int y){

		//will check unit path
		selectedUnit.setCoord(x, y); //moves to coords x and y
		unitList.get(selectedUnitIndex).setHasMoved(true); //flags moved
		Log.d("TEST", "UNIT MOVED");
	}
	
	public boolean checkForMove(int x, int y){
		boolean moving = false;
		if(checkForUnit(x,y) == false && selectedUnit != null && !selectedUnit.getHasMoved()){ //check for nothing at Selector, something is selected
			moving = true;																	   //and what is selected hasn't moved
		}
		return moving;
	}
	public boolean checkForUnit(int x,int y){
		boolean unitExists = false;
		for(int i = 0; i < unitList.size(); i++){ //Loops through unitList
			if(unitList.get(i).getX() == x && unitList.get(i).getY() == y){ //Check if unit is at Selector
				unitExists = true;
				break;
			}
		}
		return unitExists;
	}
	
	public Unit getAt(int i){
		return unitList.get(i);
	}
	
	public UnitStats getUnitStats(){ return stats; }
}
