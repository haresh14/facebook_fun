package net.voiceter;

import java.io.IOException;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateVoiceter extends SherlockActivity {
	
	private static final String PATH = "voiceter/";
	
	private EditText name;
	private EditText tags;
	private SoundManager mSoundManager;
	private AudioRecorder recorder;
	private Contact cn ;
	private DatabaseHandler db;

	private int lastID;

	private static List<Contact> contacts;

	@Override 	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.create_voiceter);
		name = (EditText)findViewById(R.id.editText1); 
		tags = (EditText)findViewById(R.id.editText2);
		
		mSoundManager = new SoundManager();
        mSoundManager.initSounds(getBaseContext());
        
//        mSoundManager.addSound(1,"/mnt/sdcard/" + PATH + ".3gp");
       
		
		db = new DatabaseHandler(this);
		
	}
	private void contactsIter() {
		contacts = db.getAllContacts();  
		for (Contact cn : contacts) {
        lastID = cn.get_id();
        Log.e("OLAST ID", lastID + "");
        String log = "Id: "+cn.get_id()+" ,Name: " + cn.get_name() + " ,Tags: " + cn.get_tags() + ", Voice: " + cn.get_voice();
        // Writing Contacts to log
Log.d("Name: ", log);
        }
	}
	public void record(View v) throws IOException{
		contactsIter();
		recorder = new AudioRecorder(PATH + "record" + lastID);
		recorder.start();
		
	}
	public void stop(View v) throws IOException{
		recorder.stop();
		 mSoundManager.addSound(1,"/mnt/sdcard/" + PATH + "record" + lastID + ".3gp");
		
	}
	public void replay(View v){
		
		mSoundManager.playSound(1);
		db.addContact(new Contact(1,name.getText().toString(),tags.getText().toString(),PATH + "record" + lastID));
		     
		contacts = db.getAllContacts();  
        for (Contact cn : contacts) {
            String log = "Id: "+cn.get_id()+" ,Name: " + cn.get_name() + " ,Tags: " + cn.get_tags() + ", Voice: " + cn.get_voice();
                // Writing Contacts to log
        Log.d("Name: ", log);}
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	public static List<Contact>  getContactsList(){
		return contacts;
	}
}
