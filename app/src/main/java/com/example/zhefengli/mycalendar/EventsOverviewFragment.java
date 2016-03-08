package com.example.zhefengli.mycalendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by zhefengli on 3/7/16.
 */
public class EventsOverviewFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        final ArrayList<Reminder> allReminders = db.getAllReminders();
        if(allReminders.isEmpty())
            return inflater.inflate(R.layout.fragment_noevents, container, false);

        else{
            View view = inflater.inflate(R.layout.fragment_events, container, false);
            final ListView lv1 = (ListView) view.findViewById(R.id.custom_list);
            final CustomListAdapter customListAdapter = new CustomListAdapter(getActivity(), allReminders);
            final DatabaseHandler databaseHandler = db;
            lv1.setAdapter(customListAdapter);
            lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Delete");
                    builder.setMessage("Do you want to delete this memo?");
                    final int listPos = position;
                    builder.setNegativeButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            databaseHandler.deleteReminder(allReminders.get(listPos));
                            allReminders.remove(listPos);
                            customListAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setPositiveButton("No", null);
                    builder.show();
                    return true;

                }
            });
            return view;
        }
    }
}
