package net.voiceter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;


public class Auth {
	InputStream instream;
	String line;
	public Auth(Context context) throws IOException{
		InputStream inputStream = context.getResources().openRawResource(R.raw.users);
//		while (line != null) {Log.v("TAG",line);}
		// Get the object of DataInputStream
		  DataInputStream in = new DataInputStream(inputStream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  Log.v("FAFSF",strLine);
		  // Print the content on the console
		  System.out.println (strLine);
		  }
		  //Close the input stream
		  in.close();
}
}
