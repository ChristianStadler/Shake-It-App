package de.dhbw.shake_it_app;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MainScreenCustomListAdapter extends BaseAdapter {
 
	    @SuppressWarnings("rawtypes")
		private ArrayList listData;
	    private LayoutInflater layoutInflater;
	 
	    public MainScreenCustomListAdapter(Context context, ArrayList<?> listData) {
	        this.listData = listData;
	        layoutInflater = LayoutInflater.from(context);
	    }
	 
	    public int getCount() {
	        return listData.size();
	    }
	 
	    public Object getItem(int position) {
	        return listData.get(position);
	    }
	 
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        if (convertView == null) {
	            convertView = layoutInflater.inflate(R.layout.main_screen_club_item, null);
	            holder = new ViewHolder();
	            holder.textViewClub = (TextView) convertView.findViewById(R.id.textViewClub);
	            holder.textViewClubShakeIndize = (TextView) convertView.findViewById(R.id.textViewClubShakeIndize);
	            /*
	            holder.imageButtonWeiterClub = (ImageButton) convertView.findViewById(R.id.imageButtonWeiterClub);
	            holder.imageButtonWeiterClub.setFocusable(false);
	            holder.imageButtonWeiterClub.setFocusableInTouchMode(false);
	            holder.imageButtonWeiterClub.setActivated(false);
	            holder.imageButtonWeiterClub.setClickable(false);
	            */
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        
	        MainScreen_Club_Item club_Item = (MainScreen_Club_Item) listData.get(position);
	 
	        holder.textViewClub.setText(club_Item.getClubName());
	        holder.textViewClubShakeIndize.setText("Aktuell " + club_Item.getAktClubIndex() + " Pkt./ Durchschn. " + club_Item.getAvgClubIndex() + " Pkt.");
	        
	 	
	        return convertView;
	    }
	 
	    static class ViewHolder {
	        TextView textViewClub;
	        TextView textViewClubShakeIndize;

	    }
	 
	}
