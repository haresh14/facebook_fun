package net.voiceter;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;


public class MainVoiceterActivity extends SherlockActivity  {
	
	private static final String TAG = MainVoiceterActivity.class.getSimpleName();
	private static final String PATH = "/voiceter/voice1";
	
	AudioRecorder recorder;
	PlaySound playSound;
	private MediaPlayer mp;
	private SoundManager mSoundManager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 	
		setContentView(R.layout.main);
		mSoundManager = new SoundManager();
        mSoundManager.initSounds(getBaseContext());
        mSoundManager.addSound(1,"/mnt/sdcard/" + PATH + ".3gp");
		
//		PlaySound playSound = new PlaySound(getBaseContext(), this, PATH);
	}
	public void startRec(View v) throws IOException{
		recorder = new AudioRecorder(PATH);
		recorder.start();
	}
	public void stopRec(View v) throws IOException{
		recorder.stop();
	}
	public void playRec(View V){
//		startActivity(new Intent(this,Tutorial3.class));
//		playSound = new PlaySound(getApplicationContext(), this, PATH);
//		playSound.Play();

}
}
