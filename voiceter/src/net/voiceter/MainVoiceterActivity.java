package net.voiceter;

import java.io.IOException;

import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;


public class MainVoiceterActivity extends SherlockActivity  {
	
	private static final String TAG = MainVoiceterActivity.class.getSimpleName();
	private static final String PATH = "/voiceter/voice1";
	
	AudioRecorder recorder;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
//		PlaySound playSound = new PlaySound(getBaseContext(), this, PATH);
	}
	public void startRec(View v) throws IOException{
		recorder = new AudioRecorder(PATH);
		recorder.start();
	}
	public void stopRec(View v) throws IOException{
		recorder.stop();
	}
	
}
