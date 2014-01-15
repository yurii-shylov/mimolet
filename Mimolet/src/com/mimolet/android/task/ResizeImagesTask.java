package com.mimolet.android.task;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.mimolet.android.global.GlobalVariables;
import com.mimolet.android.global.ImageUtils;
import com.mimolet.android.multiphoto.MultiPhotoSelectActivity;

public class ResizeImagesTask extends AsyncTask<Void, Void, Void> {
	private final ProgressDialog dialog;
	private final ArrayList<String> selectedItems;
	private MultiPhotoSelectActivity activity;

	public ResizeImagesTask(MultiPhotoSelectActivity activity, ArrayList<String> selectedItems) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
		this.selectedItems = selectedItems;
	}
	
	@Override
	protected void onPreExecute() {
		this.dialog.setMessage("Converting...");
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
	    try {
		    for (int i = 0; i < selectedItems.size(); i++) {
		      saveImage(ImageUtils.decodeSampledBitmapFromFile(selectedItems.get(i), 1024, 1024, false), i);
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		if (this.dialog.isShowing()) {
			this.dialog.dismiss();
		}
		activity.end();
	}

	private void saveImage(Bitmap finalBitmap, int position) {
		File mimoletDir = new File(GlobalVariables.MIMOLET_FOLDER);
		mimoletDir.mkdirs();
		File myDir = new File(GlobalVariables.IMAGE_FOLDER);
		myDir.mkdirs();

		String fname = "Image-" + (position) + ".png";
		File file = new File(myDir, fname);
		if (file.exists()) {
			file.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
