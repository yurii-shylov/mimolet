package com.mimolet.android.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mimolet.android.R;

public class RegistrationTask extends AsyncTask<String, Void, RegistrationTask.ExecutionResult> {
	private static final String TAG = "RegistrationTask";
	private final ProgressDialog dialog;
	Activity parent;
	
	
	public RegistrationTask(Activity parent) {
		this.parent = parent;
		dialog = new ProgressDialog(this.parent);
	}
	
	@Override
	protected ExecutionResult doInBackground(String... params) {
		try {
			final HttpClient httpClient = new DefaultHttpClient();
			final HttpPost httpPost = new HttpPost(params[2]);
			final MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("email", new StringBody(params[0]));
			reqEntity.addPart("password", new StringBody(params[1]));
			Log.i(TAG, "Request rdy");
			httpPost.setEntity(reqEntity);
			final HttpResponse response = httpClient.execute(httpPost);
			Log.i(TAG, "Get responce");
			final BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			final String line = rd.readLine();
			Log.v(TAG, "Server answer line value = " + line);
			if (line.equals("true")) {
				return ExecutionResult.SUCCESS;
			} else if (line.equals("wronglogin")) {
				return ExecutionResult.WRONG_LOGIN;
			} else {
				return ExecutionResult.FAIL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ExecutionResult.FAIL;
		}
	}
	
	@Override
	protected void onPreExecute() {
		this.dialog.setMessage("Logging in...");
		this.dialog.setCancelable(false);
		this.dialog.show();
	}
	
	@Override
	protected void onPostExecute(RegistrationTask.ExecutionResult result) {
		if (this.dialog.isShowing()) {
			this.dialog.dismiss();
		}
		switch (result) {
		case SUCCESS:
			break;
		case WRONG_LOGIN:
			break;
		case FAIL:
			Toast.makeText(parent.getApplicationContext(),
					R.string.unidentified_error, Toast.LENGTH_LONG).show();
			break;
		}
	}
	
	public enum ExecutionResult {
		SUCCESS, FAIL, WRONG_LOGIN;
	}
}
