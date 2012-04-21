package net.voiceter;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PlaySound {
	private SoundPool soundPool;
	private int soundID;
	boolean loaded = false;
	private Activity baseActivity;
	private Context baseContext;

	
/** Called when the activity is first created. */

	public PlaySound(Context context, Activity activity, int path) {
		baseContext = context;
		baseActivity = activity;
		// Set the hardware buttons to control the music
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});
//		soundID = soundPool.load(this, 0, 1);

	}

	public boolean Play() {
			// Getting the user sound settings
			AudioManager audioManager = (AudioManager) baseActivity.getSystemService(Context.AUDIO_SERVICE);
			float actualVolume = (float) audioManager
					.getStreamVolume(AudioManager.STREAM_MUSIC);
			float maxVolume = (float) audioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			float volume = actualVolume / maxVolume;
			// Is the sound loaded already?
			if (loaded) {
				soundPool.play(soundID, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
		
		return false;
	}
}
		