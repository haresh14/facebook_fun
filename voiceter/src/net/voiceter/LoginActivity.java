package net.voiceter;

import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;

public class LoginActivity extends SherlockActivity {
	EditText ed1;
	EditText ed2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 	
		setContentView(R.layout.login);
		ed1 = (EditText)findViewById(R.id.editText1);
		ed2 = (EditText)findViewById(R.id.editText2);
	}
	
	public void login(View v) throws IOException{
		
		Log.e("form info",ed1.getText().toString() + " " + ed2.getText().toString() );
		if(ed1.getText().toString().equalsIgnoreCase("Alex") && ed2.getText().toString().equalsIgnoreCase("qwerty")){
			startActivity(new Intent(LoginActivity.this,MyList.class));
			
		}
	}
}
