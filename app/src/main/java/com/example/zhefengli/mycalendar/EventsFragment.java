package com.example.zhefengli.mycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        ArrayList<Reminder> specifiedReminders = new ArrayList<>();
        for(int i = 0; i < currentReminders.size(); i++){
            if(currentReminders.get(i).getDate().equals(dateText))
                specifiedReminders.add(currentReminders.get(i));
        }

        if(specifiedReminders.isEmpty())
            return inflater.inflate(R.layout.fragment_noevents, container, false);
        else{
            View view = inflater.inflate(R.layout.fragment_events, container, false);
            final ListView lv1 = (ListView) view.findViewById(R.id.custom_list);
            lv1.setAdapter(new CustomListAdapter(getActivity(), specifiedReminders));
            return view;
        }

    }
}

