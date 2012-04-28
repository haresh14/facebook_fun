package net.voiceter;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class UserList extends SherlockActivity {

  ArrayList<VoiceRecords> products = new ArrayList<VoiceRecords>();
  BoxAdapter boxAdapter;

  /** Called when the activity is first created. */
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_list);
  
    // создаем адаптер
    fillData();
    boxAdapter = new BoxAdapter(this, products);

    // настраиваем список
    ListView lvMain = (ListView) findViewById(R.id.lvMain);
    lvMain.setAdapter(boxAdapter);
    lvMain.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.e("DASDASD",arg1.toString()+" "+arg2);
			 
		}
	});
  }

  // генерируем данные для адаптера
  void fillData() {
    for (int i = 1; i <= 5; i++) {
      products.add(new VoiceRecords("Product " + i, i * 1000 * 1000,
          R.drawable.play, false));
    }
    
  }

  // выводим информацию о корзине
  public void showResult(View v) {
    String result = "Товары в корзине:";
    for (VoiceRecords p : boxAdapter.getBox()) {
      if (p.box)
        result += "\n" + p.name;
    }
    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
  }
}