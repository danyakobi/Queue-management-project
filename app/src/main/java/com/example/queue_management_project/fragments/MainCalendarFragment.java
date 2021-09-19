package com.example.queue_management_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.queue_management_project.Activity.CalendarActivity;
import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentMainCalendarBinding;

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
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";


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
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainCalendarFragment_to_mainBooking);
            //Intent intent = new Intent(getActivity(), CalendarActivity.class);
            //intent.putExtra(KEY_DISPLAY_TIME_SLOT , "DISPLAY_TIME_SLOT");

            //localBroadcastManager.sendBroadcast(intent);
            //Intent i = new Intent(getActivity(), CalendarActivity.class);
         //   i.putExtra(KEY , "eden");
            //startActivity(intent);
            //((Activity) getActivity()).overridePendingTransition(0, 0);

        }

    }




}
