package de.dhbw.shake_it_app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RanglisteCustomListAdapter extends BaseAdapter {
	 
    private ArrayList listData;

 
    private LayoutInflater layoutInflater;
 
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
 
        holder.textSpitzname.setText(((Rangliste_Item) listData.get(position)).getUsername());
        holder.textPunkte.setText(((Rangliste_Item) listData.get(position)).getAvgIndexUser() +"Pkt.");
 
        return convertView;
    }
 
    static class ViewHolder {
    	private TextView textSpitzname, textPunkte;
    	private ImageView imageViewUser;
    }
 
}