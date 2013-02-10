package pack.NULLwars.main;
import units.UnitHandler;
import map.GameBoard;
import android.content.Context;
import android.graphics.Canvas;

public class Game {
	private GameBoard map;
	private UnitHandler units;
	private double x, y, downX, downY, zoomX, zoomY,
		dragX, dragY, startDistance, endDistance,
		blockSize, maxBlockSize, minBlockSize,
		preferredBlockSize, zoomSpeed;
	private int rows, cols, maxResizeTimer, resizeTimer, selectCorrection;
	private Selector selector;
	private GUI gui;
	public Context context;
	boolean gameOver, zoom;
	
	public Game(Context context){
		this.context=context;
		//set up first map size
		rows = 128;
		cols = 128;
		blockSize = preferredBlockSize = 32f;
		maxBlockSize = 128f;
		minBlockSize = 4f;
		maxResizeTimer = 100;
		zoomSpeed = 2f; //smoother if even number
		selectCorrection = 5; //removes jitter when selecting
		//set up map
		map = new GameBoard(rows, cols);
		units = new UnitHandler();
		selector = new Selector();
		gui = new GUI();
	}
	public void draw(Canvas canvas){
		map.draw(canvas, (int)(x-(downX-dragX)), (int)(y-(downY-dragY)), (int)(blockSize), false);
		selector.draw(canvas, (int)(x-(downX-dragX)), (int)(y-(downY-dragY)), (int)(blockSize), false);
		units.draw(canvas, (int)(x-(downX-dragX)), (int)(y-(downY-dragY)), (int)(blockSize), false);
		gui.draw(canvas);
	}
	public void update(){
		map.update();
		units.update();
		//readjust map size after timer
		if (resizeTimer > 0) resizeTimer--;
		else{
			if (blockSize > preferredBlockSize){
				blockSize -= zoomSpeed/2;
				x += (zoomX - x)/(((blockSize+(zoomSpeed/2)))/(zoomSpeed/2));
				y += (zoomY - y)/(((blockSize+(zoomSpeed/2)))/(zoomSpeed/2));
			}
			else if (blockSize < preferredBlockSize){
				blockSize += zoomSpeed/2;
				x -= (zoomX - x)/(((blockSize-(zoomSpeed/2)))/(zoomSpeed/2));
				y -= (zoomY - y)/(((blockSize-(zoomSpeed/2)))/(zoomSpeed/2));
			}
			else resizeTimer = maxResizeTimer;
		}
	}
	public void down(int x1, int y1){
		downX = dragX = x1;
		downY = dragY = y1;
	}
	public void move(int x1, int y1, int x2, int y2){
		resizeTimer = maxResizeTimer;
		//handle pinch to zoom
		if (x2 > 0 && y2 > 0){
			if (!zoom){
				startDistance = (int)(Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)));
				//startBlockSize = blockSize;
				zoom = true;
			}
			else{
				//find midpoint
				zoomX = (x1+x2)/2;
				zoomY = (y1+y2)/2;
				//adjust blockSize
				endDistance = (int)(Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)));
				
				//zoom into direction
				if (Math.abs(startDistance-endDistance) > 5){ //stops it from being too touchy
					if (endDistance > startDistance){
						if (blockSize + zoomSpeed < maxBlockSize){
							blockSize += zoomSpeed;
							x -= (zoomX - x)/(((blockSize-zoomSpeed))/zoomSpeed);
							y -= (zoomY - y)/(((blockSize-zoomSpeed))/zoomSpeed);
						}
					}
					else{
						if (blockSize - zoomSpeed > minBlockSize){
							blockSize -= zoomSpeed;
							x += (zoomX - x)/(((blockSize+zoomSpeed))/zoomSpeed);
							y += (zoomY - y)/(((blockSize+zoomSpeed))/zoomSpeed);
						}
					}
				}
			}
		}
		else{
			if (!zoom){
				//remove jitter for select
				if ((Math.sqrt(Math.pow(x1-downX, 2)+Math.pow(y1-downY, 2))) >= selectCorrection){
					dragX = x1;
					dragY = y1;
				}
			}
		}
	}
	public void up(int x1, int y1){
		x -= (downX-dragX);
		y -= (downY-dragY);
		//avoid selecting constantly
		if (!zoom){
			if ((Math.sqrt(Math.pow(x1-downX, 2)+Math.pow(y1-downY, 2))) < selectCorrection){
				selector.up((int)((x1-x)), (int)((y1-y)), (int)blockSize);
			}
		}
		else zoom = false;
		downX = downY = dragX = dragY = 0;
	}
	public void reset(){
		gameOver = false;
	}
	public void setX(int x){ this.x=x; }
	public void setY(int y){ this.y=y; }
	public int getX(){ return (int)(x-(downX-dragX)); }
	public int getY(){ return (int)(y-(downY-dragY)); }
	public int getRows(){ return rows; }
	public int getCols(){ return cols; }
	public int getBlockSize(){ return (int)blockSize; }

	public UnitHandler getUnits(){ return units; }
	
	public GameBoard getMap() {
		return map;
		
	}
	

}
