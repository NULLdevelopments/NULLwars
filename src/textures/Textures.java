package textures;
import pack.NULLwars.main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;

public class Textures extends View {
	//public Bitmap player; //player texture
	public TextureHandler start, moontextures, advancewars, basicinfantry, rocketinfantry, selector;
	public Textures(Context context){
		super(context);
		//textures
		moontextures = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.moontextures), 4, 4, 0.0);	
		advancewars = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.advancewars), 4, 4, 0.0);	
		//button
		start = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.start), 1, 2, 0.0);
		//units
		basicinfantry = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.basicinfantry), 4, 1, 0.15);	
		rocketinfantry = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.rocketinfantry), 4, 1, 0.05);	
		//gui
		selector = new TextureHandler(BitmapFactory.decodeResource(getResources(), R.drawable.selector), 4, 1, 0.15);	
	}
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		//use once, it's a heavier process
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
	public Bitmap crop(Bitmap bitmapOrg, int x1, int y1, int x2, int y2){
		return Bitmap.createBitmap(bitmapOrg, x1, y1, x2-x1, y2-y1);
	}
}
