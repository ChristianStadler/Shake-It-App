package de.dhbw.shake_it_app;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RanglisteCustomListAdapter extends BaseAdapter {
	 
    private ArrayList<Rangliste_Item> listData;
    private LayoutInflater layoutInflater;
    private int position = 0;
 
    public RanglisteCustomListAdapter(Context context, ArrayList<Rangliste_Item> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }
 
    @Override
    public int getCount() {
        return listData.size();
    }
 
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.rangliste_list_item, null);
            holder = new ViewHolder();

            holder.textSpitzname = (TextView)convertView.findViewById(R.id.textSpitzname);
            holder.textPunkte = (TextView)convertView.findViewById(R.id.textPunkte);
            holder.imageViewUser = (ImageView)convertView.findViewById(R.id.imageViewUser);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        this.position = position;
        Log.e("position",  ": " + position);
        Rangliste_Item rangliste_Item = (Rangliste_Item) listData.get(position);
        
        holder.textSpitzname.setText(rangliste_Item.getUsername());
        holder.textPunkte.setText("mit einem Shake-Index von " + rangliste_Item.getAvgIndexUser() +"Pkt.");
        
        //Hinweis für Hannah
        //Button mit einbauen: http://javatechig.com/android/asynchronous-image-loader-in-android-listview
 
        return convertView;
    }
 
    static class ViewHolder {
    	private TextView textSpitzname, textPunkte;
    	private ImageView imageViewUser;
    }
 
}