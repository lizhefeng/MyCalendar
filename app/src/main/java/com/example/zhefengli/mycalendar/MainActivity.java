package com.example.zhefengli.mycalendar;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainActivity extends AppCompatActivity implements MainFragment.ButtonListener {
    private CalendarView calendarView;
    private TextView datePick;
    private EditText title;
    private EditText memo;
    private Button addButton;
    private Button eventButton;
    private TimePicker timePicker;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button newEvent = (Button) findViewById(R.id.button);
        //Button viewEvents = (Button) findViewById(R.id.button2);
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

    }



    public void addClicked(View view) throws ParseException{
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        title = (EditText) findViewById(R.id.editText2);
        memo = (EditText) findViewById(R.id.editText);
        datePick = (TextView) findViewById(R.id.textView2);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        if(datePick.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please select a specific date.", Toast.LENGTH_LONG).show();
        }
        else if(title.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please add a title", Toast.LENGTH_LONG).show();
        }
        else if(memo.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please say some memos.", Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(getApplicationContext(), "You have successfully add an event.", Toast.LENGTH_LONG).show();
            //String tempInfo = datePick.getText().toString().substring(25);
            //String[] dateArray = tempInfo.split("/");
            //int day =  Integer.parseInt(dateArray[0]);
            //int month = Integer.parseInt(dateArray[1]);
            //int year = Integer.parseInt(dateArray[2]);
            //DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            //Date date = formatter.parse(tempInfo);
            //long dateInlong = date.getTime();
            //calendarView.setDate(dateInlong);
            Toast.makeText(getApplicationContext(), "You have successfully add an event.", Toast.LENGTH_LONG).show();
            String timeSelect = datePick.getText().toString().substring(25);
            db.addReminder(new Reminder(title.getText().toString(), memo.getText().toString(), timeSelect));

        }

    }

    public void eventsClicked(View view){
        EventsFragment eventsFragment = new EventsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, eventsFragment)
                .addToBackStack(null)
                .commit();
    }
}
