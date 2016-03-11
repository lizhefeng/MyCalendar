package com.example.zhefengli.mycalendar;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.util.*;


public class MainActivity extends AppCompatActivity implements MainFragment.ButtonListener {
    private CalendarView calendarView;
    private TextView datePick;
    private TextView timePick;
    private EditText title;
    private EditText memo;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        if(findViewById(R.id.main_fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, new MainFragment())
                            .addToBackStack(null)
                            .commit();

        }
    }

    public void onAddEventClick() {
            CalendarFragment calendarFragment = new CalendarFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, calendarFragment)
                    .addToBackStack(null)
                    .commit();
    }

    public void onViewEventsClick() {
            EventsOverviewFragment eventsOverviewFragment = new EventsOverviewFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, eventsOverviewFragment)
                        .addToBackStack(null)
                        .commit();
    }


    public void setTimeClicked(View view){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(view.isShown()){
                    timePick = (TextView) findViewById(R.id.textView5);
                    String hour = String.valueOf(hourOfDay);
                    String minutes = String.valueOf(minute);
                    if(hourOfDay < 10){
                        hour = "0" + hour;
                    }
                    if(minute < 10){
                        minutes = "0" + minutes;
                    }
                        timePick.setText("You set time to: " + hour + ":" + minutes);
                }
            }
        }, hour, minute, true);
        timePickerDialog.show();

    }

    public void addClicked(View view) throws ParseException{
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        title = (EditText) findViewById(R.id.editText2);
        memo = (EditText) findViewById(R.id.editText);
        datePick = (TextView) findViewById(R.id.textView2);
        timePick = (TextView) findViewById(R.id.textView5);
        if(datePick.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please select a specific date.", Toast.LENGTH_LONG).show();
        }
        else if(title.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please add a title.", Toast.LENGTH_LONG).show();
        }
        else if(memo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please say some memos.", Toast.LENGTH_LONG).show();
        }
        else if(timePick.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please set the event time.", Toast.LENGTH_LONG).show();
        }
        else {
            String timeOnly = timePick.getText().toString().substring(17);
            String timeSelect = datePick.getText().toString().substring(25);
            String dateAndTime = timeSelect + ", " + timeOnly;
            ArrayList<Reminder> currentReminders = db.getAllReminders();
            Reminder reminder = new Reminder(title.getText().toString(), memo.getText().toString(), dateAndTime);
            if(currentReminders.contains(reminder)){
                Toast.makeText(getApplicationContext(), "You have already created this event!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "You have successfully add an event!", Toast.LENGTH_LONG).show();
                db.addReminder(reminder);
                title.getText().clear();
                memo.getText().clear();
            }
        }

    }

    public void eventsClicked(View view){
        datePick = (TextView) findViewById(R.id.textView2);
        if(datePick.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please select a specific date to view events.", Toast.LENGTH_LONG).show();
        }
        else{
            String timeSelect = datePick.getText().toString().substring(25);
            Bundle bundle = new Bundle();
            bundle.putString("eventDate", timeSelect);
            EventsFragment eventsFragment = new EventsFragment();
            eventsFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, eventsFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
