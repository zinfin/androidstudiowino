package sandie.wino.tasks;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import sandie.wino.WinoApp;

public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
	
	private ImageView imageView;
	private WinoApp _app;
	
	public ImageDownloadTask(Application application, ImageView imageView){
		_app = (WinoApp) application;
		this.imageView = imageView;
	}
	@Override
	protected Bitmap doInBackground(String... params) {
		return _app.retrieveBitmap(params[0]);
	}
	@Override 
	protected void onPostExecute(Bitmap bitmap){
		if (bitmap != null){
			imageView.setImageBitmap(bitmap);
			_app.getImageCache().put((Double) imageView.getTag(), bitmap);
	        imageView.setTag(null);
		}
	}

}
