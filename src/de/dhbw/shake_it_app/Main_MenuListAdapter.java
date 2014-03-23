package de.dhbw.shake_it_app;

import android.content.Context;
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
	

