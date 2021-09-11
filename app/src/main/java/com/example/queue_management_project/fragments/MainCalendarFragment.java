package com.example.queue_management_project.fragments;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.CalendarContract;
import android.telecom.Call;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.queue_management_project.Activity.CalendarActivity;
import com.example.queue_management_project.Activity.Event;
import com.example.queue_management_project.Activity.MainActivity;
import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentMainCalendarBinding;
import com.example.queue_management_project.databinding.FragmentPage1Binding;
import com.google.firebase.database.core.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainCalendarFragment extends Fragment implements View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @NonNull
    private FragmentMainCalendarBinding binding;
    private String date;
    private static final String KEY = "key";

    public MainCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Page1Fragment newInstance(String param1, String param2) {
        Page1Fragment fragment = new Page1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMainCalendarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_calendar, container, false);
        binding.buttonSet.setOnClickListener(this);
        binding.calendarView.setOnClickListener(this);
        binding.textDate.setOnClickListener(this);
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month = month +1;
                date =  dayOfMonth  + "/" + month + "/" + year;
                binding.textDate.setText(date);
                //Log.wtf("wtf","onSelectedDayChange:" + date);
            }
        });

       return binding.getRoot();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==binding.buttonRemove.getId()){






        }
        else if (view.getId()==binding.buttonSet.getId() && date != null){
            //Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainCalendarFragment_to_eventFragment);
            Intent i = new Intent(getActivity(), CalendarActivity.class);
            i.putExtra(KEY , "eden");
            startActivity(i);
            //((Activity) getActivity()).overridePendingTransition(0, 0);

        }

    }




}
