package edu.mst.tours.asynctasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import edu.mst.tours.model.Building;

public class GetBuildingImageTask extends AsyncTask<Void, Void, Bitmap> {

	private ImageView iv;
	private Building building;
	
	public GetBuildingImageTask(ImageView iv, Building building) {
		this.iv = iv;
		this.building = building;
	}
	
	@Override
	protected void onPreExecute() {
		this.iv.setVisibility(View.GONE);
		super.onPreExecute();
	}
	
	@Override
	protected Bitmap doInBackground(Void... params) {
		try {
			String url = building.getImageURL();
			if(building.getImage() != null) return building.getImage();
			
			Bitmap bm = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
			building.setImage(bm);
			return bm;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if(result != null){
			this.iv.setImageBitmap(result);
			this.iv.setVisibility(View.VISIBLE);
		}else this.iv.setVisibility(View.GONE);
		super.onPostExecute(result);
	}

}
