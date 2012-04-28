package net.voiceter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BoxAdapter extends BaseAdapter {
  Context ctx;
  LayoutInflater lInflater;
  ArrayList<VoiceRecords> objects;
  View view;
private ImageView imView;

  BoxAdapter(Context context, ArrayList<VoiceRecords> products) {
    ctx = context;
    objects = products;
    lInflater = (LayoutInflater) ctx
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  // ���-�� ���������
  @Override
  public int getCount() {
    return objects.size();
  }

  // ������� �� �������
  @Override
  public Object getItem(int position) {
    return objects.get(position);
  }

  // id �� �������
  @Override
  public long getItemId(int position) {
    return position;
  }

  // ����� ������
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // ���������� ���������, �� �� ������������ view
    view = convertView;
    if (view == null) {
      view = lInflater.inflate(R.layout.item, parent, false);
    }

    VoiceRecords p = getProduct(position);

    // ��������� View � ������ ������ ������� �� �������: ������������, ����
    // � ��������
    ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
    ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
    ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);
//    ((ImageView)view.findViewById(R.id.ivImage)).setOnClickListener(new View.OnClickListener() {
//		
//  		@Override
//  		public void onClick(View v) {
//  			// TODO Auto-generated method stub
//  			Log.v("FUCK","A");
//  		}
//  	});
    
    CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
    // ����������� �������� ����������
    cbBuy.setOnCheckedChangeListener(myCheckChangList);
    // ����� �������
    cbBuy.setTag(position);
    // ��������� ������� �� �������: � ������� ��� ���
    cbBuy.setChecked(p.box);
   

    
    return view;
  }
  OnItemClickListener clickListener = new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if(arg1 ==   ((ImageView)view.findViewById(R.id.ivImage))){
			
		}
		
	}
};

  // ����� �� �������
  VoiceRecords getProduct(int position) {
    return ((VoiceRecords) getItem(position));
  }
  
  // ���������� �������
  ArrayList<VoiceRecords> getBox() {
    ArrayList<VoiceRecords> box = new ArrayList<VoiceRecords>();
    for (VoiceRecords p : objects) {
      // ���� � �������
      if (p.box)
        box.add(p);
    }
    return box;
  }

  // ���������� ��� ���������
  OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
    public void onCheckedChanged(CompoundButton buttonView,
        boolean isChecked) {
      // ������ ������ ������ (� ������� ��� ���)
      getProduct((Integer) buttonView.getTag()).box = isChecked;
    }
  };
  OnItemClickListener itemClickListener = new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Log.v("SADASDA","AFUOAHSFOHSAFI");
		
	}
};
  
}
