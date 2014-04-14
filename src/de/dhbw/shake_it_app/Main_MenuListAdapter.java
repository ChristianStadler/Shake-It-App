package de.dhbw.shake_it_app;

import de.dhbw.shake_it_app.R.color;
import de.dhbw.shake_it_app.data.KeyValue;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Main_MenuListAdapter extends BaseAdapter {
    private Context context;
    private String[] mTitle;
    private String[] mSubTitle;
    private int[] mIcon;
    private LayoutInflater inflater;

 
    public Main_MenuListAdapter(Context pContext, String[] pTitle, int[] pIcon) {
        context = pContext;
        mTitle = pTitle;
        mIcon = pIcon;
    }
 

	public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);
 
        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);
        System.out.println("Hallo ich bin im Adapter :)");
 
        txtTitle.setText(mTitle[position]);
        imgIcon.setImageResource(mIcon[position]);
        if(position==1&&KeyValue.getInstance().getAmShaken()==false){
        	txtTitle.setTextColor(Color.parseColor("#7F7F7F"));
        }
        else {
			txtTitle.setTextColor(Color.parseColor("#FFFFFF"));
		}
        return itemView;
    }
 
    public int getCount() {
        return mTitle.length;
    }
 
    public Object getItem(int position) {
        return mTitle[position];
    }
 
    public long getItemId(int position) {
        return position;
    }

    
}
	

