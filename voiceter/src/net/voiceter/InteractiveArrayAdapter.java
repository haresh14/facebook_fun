package net.voiceter;
import java.util.List;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class InteractiveArrayAdapter extends ArrayAdapter<Model>{

	private static final String PATH = "voiceter/";
	
	private final List<Model> list;
	private final Activity context;
	private SoundManager mSoundManager;

	public InteractiveArrayAdapter(Activity context, List<Model> list) {
		super(context, R.layout.rowbuttonlayout, list);
		this.context = context;
		this.list = list;
	
	}

	static class ViewHolder {
		protected TextView text;
		protected ImageView imageView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			mSoundManager = new SoundManager();
	        mSoundManager.initSounds(context);
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.rowbuttonlayout, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);
			viewHolder.imageView = (ImageView) view.findViewById(R.id.ivImage);
			viewHolder.imageView.setImageResource(R.drawable.play);
			
//			viewHolder.checkbox
//					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//						@Override
//						public void onCheckedChanged(CompoundButton buttonView,
//								boolean isChecked) {
//							Model element = (Model) viewHolder.checkbox
//									.getTag();
//							element.setSelected(buttonView.isChecked());
//
//						}
//					});
			view.setTag(viewHolder);
			viewHolder.imageView.setTag(position);
			viewHolder.imageView
			.setOnClickListener(new OnClickListener() {
				
				private String path = "/mnt/sdcard/voice";

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.v("SEX","/mnt/sdcard/" + PATH + "record" + v.getTag().toString() + ".3gp");
					mSoundManager.addSound(1,"/mnt/sdcard/" + PATH + "record" + v.getTag().toString() + ".3gp");
					Log.v("SEX","/mnt/sdcard/" + PATH + "record" + v.getTag().toString() + ".3gp");
					SystemClock.sleep(1000);
					mSoundManager.playSound(1);
					
				}
			});
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).imageView.setTag(list.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getInfo());
//		holder.imageView.setPressed((list.get(position).isSelected()));
		return view;
	}
	
}