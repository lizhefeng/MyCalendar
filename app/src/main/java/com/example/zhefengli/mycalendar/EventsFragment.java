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
 * Created by zhefengli on 3/6/16.
 */
public class EventsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String dateText = getArguments().getString("eventDate");
        DatabaseHandler db = new DatabaseHandler(getActivity());
        ArrayList<Reminder> currentReminders = db.getAllReminders();
        final ArrayList<Reminder> specifiedReminders = new ArrayList<>();
        for(int i = 0; i < currentReminders.size(); i++){
            if(currentReminders.get(i).getDate().equals(dateText))
                specifiedReminders.add(currentReminders.get(i));
        }

        if(specifiedReminders.isEmpty())
            return inflater.inflate(R.layout.fragment_noevents, container, false);
        else{
            View view = inflater.inflate(R.layout.fragment_events, container, false);
            final ListView listView = (ListView) view.findViewById(R.id.event_list);
            final EventListAdapter eventListAdapter = new EventListAdapter(getActivity(), specifiedReminders);
            final DatabaseHandler databaseHandler = db;
            listView.setAdapter(eventListAdapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Delete");
                    builder.setMessage("Do you want to delete this memo?");
                    final int listPos = position;
                    builder.setNegativeButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            databaseHandler.deleteReminder(specifiedReminders.get(listPos));
                            specifiedReminders.remove(listPos);
                            eventListAdapter.notifyDataSetChanged();
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

