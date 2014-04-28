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
	private SharedPreferences sharedPreferences;

 
    public Main_MenuListAdapter(Context pContext, String[] pTitle, int[] pIcon) {
        context = pContext;
        mTitle = pTitle;
        mIcon = pIcon;
		sharedPreferences =pContext. getSharedPreferences("de.dhbw.shake_it_app", Context.MODE_PRIVATE);
    }
 

	public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);
       
        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        if(position==1|position==2) txtTitle.setEnabled(false);
        else txtTitle.setEnabled(true);
        
//        if(position==1&&KeyValue.getInstance().getAmShaken()==false){
//            System.out.println("AmShaken in Main_MenuListAdapter: " + KeyValue.getInstance().getAmShaken());
//        	txtTitle.setTextColor(Color.parseColor("#7F7F7F"));
//        	mIcon [1] = R.drawable.ic_action_locate_grey;
//        }
//        else if (position==1&&KeyValue.getInstance().getAmShaken()==true) {
//            System.out.println("AmShaken in Main_MenuListAdapter: " + KeyValue.getInstance().getAmShaken());
//			txtTitle.setTextColor(Color.parseColor("#FFFFFF"));
//			mIcon [1] = R.drawable.ic_action_locate;
//		}
 

        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);
 
        txtTitle.setText(mTitle[position]);
        imgIcon.setImageResource(mIcon[position]);
       
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
	

