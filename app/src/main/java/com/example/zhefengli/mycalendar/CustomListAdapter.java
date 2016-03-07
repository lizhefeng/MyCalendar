package com.example.zhefengli.mycalendar;

import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
/**
 * Created by zhefengli on 3/7/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Reminder> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<Reminder> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.memTitle);
            holder.memoView = (TextView) convertView.findViewById(R.id.memo);
            holder.dateView = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleView.setText(listData.get(position).getTitle());
        holder.memoView.setText(listData.get(position).getMemo());
        holder.dateView.setText(listData.get(position).getDate());
        return convertView;
    }

    static class ViewHolder {
        TextView titleView;
        TextView memoView;
        TextView dateView;
    }
}
