package map;

import textures.TextureHandler;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RenderTileMap {
	public static Bitmap render(Tile[][] gameBoard, TextureHandler t){
		//set up paint/canvas properties
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 0, 0, 0);
		Canvas canvas = new Canvas();
		//get the columns and rows of the 2d array
		int rows = gameBoard.length;
		int cols = gameBoard[0].length;
		//draw the textures to the bitmap from the 2d array
		Bitmap bitM = Bitmap.createBitmap(cols*t.getSpriteWidth(),rows*t.getSpriteHeight(), Bitmap.Config.ARGB_8888);
		canvas.setBitmap(bitM);
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				t.animate(gameBoard[row][col].getType());
				t.update(col*t.getSpriteWidth(), row*t.getSpriteHeight(),
					t.getSpriteWidth(), t.getSpriteHeight());
				canvas.drawBitmap(t.getBitmap(), t.getSpriteRect(), t.getDestRect(), paint);
			}
		}
		return bitM;
	}
}
