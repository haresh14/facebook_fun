package net.voiceter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MyList extends SherlockListActivity implements ActionBar.TabListener{
	public static List<Model> list;
	private DatabaseHandler db;
	private List<Contact> contacts;
	private int lastID;
	

	@Override 	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
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
		// Create an array of Strings, that will be put to our ListActivity
				ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(this,
						getModel());
				setListAdapter(adapter);
				
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
 	

	private List<Model> getModel() {
		 list.clear();
		Log.v("ASDASD", db + "");
		contacts = db.getAllContacts(); 
		for (Contact cn : contacts) {
	        lastID = cn.get_id();
	        Log.e("OLAST ID", lastID + "");
	        String log = "Id: "+cn.get_id()+" ,Name: " + cn.get_name() + " ,Tags: " + cn.get_tags() + ", Voice: " + cn.get_voice();
	       
	        list.add(get(cn.get_id()+"",cn.get_name(),cn.get_tags()));
	        // Writing Contacts to log
	Log.d("Name: ", log);}
	
		return list;
	}

	private Model get(String s, String name, String tags ) {
		return new Model(s,name,tags);
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		getSupportActionBar().getTabAt(0).setText("Search");
//		if(tab.getTag().equals(1)){
//			tab.setText("Feed");
//		}
//		if(tab.getTag().equals(2)){
//			tab.setText("Search");
//		}
	
		if(tab.getText() == "Feed"){
		Log.e("FUCK",tab.getTag().equals("1")+ "");
		}
		if(tab.getText() == "Search"){
			Log.e("FUCK",tab.getTag().equals("1")+ "");
			Intent intent = new Intent(this, SearchActivity.class);
	        startActivity(intent);
			}
		if(tab.getTag() == "2"){
		Log.v("SEX","SAD");
		Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right, R.anim.left);
		}
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
		
	}


}