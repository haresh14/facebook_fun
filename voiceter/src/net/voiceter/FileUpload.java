package net.voiceter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.appengine.repackaged.org.apache.commons.httpclient.HttpStatus;
import com.google.appengine.repackaged.org.apache.commons.httpclient.StatusLine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class FileUpload extends Activity {
	Bitmap bm;
	private String genUrl;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			// bm = BitmapFactory.decodeResource(getResources(),
			// R.drawable.forest);
			bm = BitmapFactory.decodeFile("/sdcard/DCIM/Camera/1.jpg");
			executeMultipartPost();
		} catch (Exception e) {
			Log.e(e.getClass().getName(), e.getMessage());
		}
	}

	public void executeMultipartPost() throws Exception {
		try {
			HttpClient httpClient = new DefaultHttpClient();  
			String url = "http://atroush-server.appspot.com/gen-url";
			HttpGet httpGet = new HttpGet(url);
			try {
			    HttpResponse response = httpClient.execute(httpGet);
			    org.apache.http.StatusLine statusLine = response.getStatusLine();
			    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			        HttpEntity entity = response.getEntity();
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
			        entity.writeTo(out);
			        out.close();
			        String responseStr = out.toString();
			        
			        int start = responseStr.indexOf("http://atroush-server.appspot.com/_ah/upload/");
			        int end = responseStr.lastIndexOf("/\"");
			        genUrl = responseStr.substring(start, end+1);
			        Log.e("SAD", genUrl);
			        // do something with response 
			    } else {
			        // handle bad response
			    }
			} catch (ClientProtocolException e) {
			    // handle exception
			} catch (IOException e) {
			    // handle exception
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bm.compress(CompressFormat.JPEG, 75, bos);
			byte[] data = bos.toByteArray(); 
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					genUrl);
			ByteArrayBody bab = new ByteArrayBody(data, "1.jpg");
			// File file= new File("/mnt/sdcard/forest.png");
			// FileBody bin = new FileBody(file);
			MultipartEntity reqEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("uploaded", bab);
			reqEntity.addPart("photoCaption", new StringBody("sfsdfsdf"));
			postRequest.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "UTF-8"));
			String sResponse;
			StringBuilder s = new StringBuilder();

			while ((sResponse = reader.readLine()) != null) {
				s = s.append(sResponse);
			}
			Log.v("ATG","Response: " + s);
		} catch (Exception e) {
			// handle exception here
			Log.e(e.getClass().getName(), e.getMessage());
		}
	}
}