package com.example.zhefengli.mycalendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by zhefengli on 3/6/16.
 */
public class MainFragment extends Fragment {

    private ButtonListener activityCallback;

    public interface ButtonListener {
        public void onAddEventClick();
        public void onViewEventsClick();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a = getActivity();
        if (context instanceof MainActivity){
            a = (MainActivity) context;
        }

        try {
            activityCallback = (ButtonListener) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(activityCallback.toString() + " must implement ButtonListener");
        }
    }



    //public static MainFragment newInstance() {
        //return new MainFragment();
    //}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        final Button button1 = (Button) view.findViewById(R.id.button);
        final Button button2 = (Button) view.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    public void buttonClicked (View view) {
        if(view.getId() == R.id.button)
            activityCallback.onAddEventClick();
        if(view.getId() == R.id.button2)
            activityCallback.onViewEventsClick();
    }
}
