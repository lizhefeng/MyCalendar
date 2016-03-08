package com.example.zhefengli.mycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhefengli on 3/6/16.
 */
public class CalendarFragment extends Fragment {
    private CalendarView calendarView;
    private TextView datePick;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        datePick = (TextView) view.findViewById(R.id.textView2);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                datePick.setText("You currently pick date: " + month  + "/" + dayOfMonth + "/" + year);

                Toast.makeText(getActivity().getApplicationContext(), "Date Picked:\n" + "Day = " + dayOfMonth + "\n" + "Month = " + month + "\n" + "Year = " + year, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
