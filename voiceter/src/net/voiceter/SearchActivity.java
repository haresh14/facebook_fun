package net.voiceter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SearchActivity extends SherlockActivity implements ActionBar.TabListener {
	private DatabaseHandler db;
	private static ArrayList<Model> list;
	private List<Contact> contacts;
	private int lastID;
	private String searchWord;
	private EditText ed;

	@Override 	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.search);
		ed = (EditText)findViewById(R.id.editText1);
		String[] tabNames = new String[2];
		tabNames[0] = "Feed";
		tabNames[1] = "Search";
		list = new ArrayList<Model>();
		db = new DatabaseHandler(this);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 0; i <= 1; i++) {
            ActionBar.Tab tab = getSupportActionBar().newTab();
            tab.setTag(i);
            tab.setText(tabNames[i]);
            tab.setTabListener(this);
            getSupportActionBar().addTab(tab);
        }
		
		
		
       
        
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
				
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "Add voiceter");
        menu.add("Save")
        .setIcon(R.drawable.plus)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(this, CreateVoiceter.class));
                return true;
        }
        return false;
    }
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getText() == "Search"){
			startActivity(new Intent(this,MyList.class));
		}
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getText() == "Search"){
			startActivity(new Intent(this,SearchActivity.class));
		}
		
	}
 	
	public void search(View v){
		 String log = null;
		searchWord = ed.getText().toString();
		Log.v("ASDASD", db + "");
		contacts = db.getAllContacts(); 
		for (Contact cn : contacts) {
	        lastID = cn.get_id();
			if(cn.get_tags().indexOf(searchWord) != -1){
	        	log = cn.get_id()+" "+cn.get_name() + " " +cn.get_tags();
	        	list.add(get(cn.get_id()+"",cn.get_name(),cn.get_tags()));
	        }
		}
		if(list != null){
		startActivity(new Intent(this,SearchResoults.class));}
	        // Writing Contacts to log
	
	}
	private Model get(String s, String name, String tags ) {
		return new Model(s,name,tags);
	}
	public static ArrayList<Model> showList(){
		return list;
		
	}
}
