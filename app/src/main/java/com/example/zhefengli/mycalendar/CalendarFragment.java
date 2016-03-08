package com.example.zhefengli.mycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        final Long initDate = calendarView.getDate();
        Date date = new Date(initDate);
        SimpleDateFormat mDy = new SimpleDateFormat("MM/dd/yy");
        String datetText = mDy.format(date);
        datePick.setText("You currently pick date: " + datetText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if (calendarView.getDate() != initDate) {
                    month = month + 1;
                    datePick.setText("You currently pick date: " + month + "/" + dayOfMonth + "/" + year);
                    String toastText = "Date Picked:\n" + "Month = " + month + "\n" + "Day = " + dayOfMonth + "\n" + "Year = " + year;
                    Toast.makeText(getActivity().getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
