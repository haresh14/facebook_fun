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

public class SearchResoults extends SherlockListActivity implements ActionBar.TabListener{
//	public static List<Model> list;
	private DatabaseHandler db;
	private List<Contact> contacts;
	private int lastID;
	private ArrayList<Model> list;
	

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
		
		getSupportActionBar().selectTab(getSupportActionBar().getTabAt(getSupportActionBar().getTabCount()-1)); 
        
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
		list = SearchActivity.showList();
	
		return list;
	}

	private Model get(String s, String name, String tags ) {
		return new Model(s,name,tags);
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

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


}
