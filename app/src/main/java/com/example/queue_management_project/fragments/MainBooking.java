package com.example.queue_management_project.fragments;

import static com.example.queue_management_project.Adapter.ListAdaptor.CurrentTime;
//import static com.example.queue_management_project.Adapter.ListAdaptor.firstTimeRun;
import static com.example.queue_management_project.Adapter.ListAdaptor.publicPosition;
import static com.example.queue_management_project.Adapter.ListAdaptor.selectedCardView;
import static com.example.queue_management_project.Adapter.ListAdaptor.selectedItem;
import static com.example.queue_management_project.Adapter.ListAdaptor.tempdescription;
import static com.example.queue_management_project.dbmodel.firebaseDp.eventList;
import static com.example.queue_management_project.dbmodel.firebaseDp.getInstance;
import static com.example.queue_management_project.fragments.MainCalendarFragment.CurrentDate;
import static com.example.queue_management_project.fragments.MainCalendarFragment.listDate;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.queue_management_project.Model.Event;
import com.example.queue_management_project.TimeSlot;
import com.example.queue_management_project.dbmodel.firebaseDp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queue_management_project.Adapter.ListAdaptor;
import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentMainBookingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.mortbay.io.nio.SelectorManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainBooking extends Fragment  implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String LIST_STATE_KEY = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @NonNull
    private FragmentMainBookingBinding binding;
    private ListAdaptor mAdapter;
    private Button back;
    private Button Confirm;
    private TextView textView;
    private TextView text_description;
    private boolean firstRun = true;
    private int localPosition;
    private RecyclerView recyclerView;
    public static List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
    public static int j =0;

    public static int i = 1;


    public MainBooking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static MainBooking newInstance(String param1, String param2) {
        MainBooking fragment = new MainBooking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMainBookingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_main_booking, container, false);
        binding = FragmentMainBookingBinding.inflate(getLayoutInflater());
        RecyclerView recyclerView = itemView.findViewById(R.id.recyclerTimeSlot);
        ListAdaptor listAdapter = new ListAdaptor(timeSlots, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//maybe dont need

        //recyclerView.setHasFixedSize(true);

        if (firstRun) {
            for (int i = 0; i < 20; i++) {
                timeSlots.add(new TimeSlot(TimeSlot.convertTimeSlotToString(i), ""));
               firstRun = false;
            }
        }
        recyclerView.setAdapter(listAdapter);
        //else {
        //    for (int i = 0; i < timeSlots.size(); i++) {
        //        timeSlots.add(new TimeSlot(TimeSlot.convertTimeSlotToString(i), ""));
        //    }
      //  }
        //CheckEvents();


        back = (Button) itemView.findViewById(R.id.buttonRecycleBack);
        Confirm = (Button) itemView.findViewById(R.id.buttonRecycleConfirm);
        textView = (TextView) itemView.findViewById(R.id.textView2);
        text_description = (TextView) itemView.findViewById(R.id.text_time_slot_description);

        back.setOnClickListener(this);
        Confirm.setOnClickListener(this);
        textView.setOnClickListener(this);

        return itemView;
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == back.getId()) {
            timeSlots.clear();
            //tempdescription.setText("Available");
            Navigation.findNavController(view).navigate(R.id.action_mainBooking_to_mainCalendarFragment);
        } else if (view.getId() == Confirm.getId()) {
            ///1.UserDetails -success
            // 2.date-success
            // 3.time-success
            // 4.Create event to firebase-success
            //CheckEvents();
            String uid;
            String name;
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            firebaseDp firebase = firebaseDp.getInstance(getActivity());
            Random rand = new Random();
            int n = rand.nextInt(242125245);

            if (firebaseUser != null) {
                name = firebaseUser.getDisplayName() + n ;
            } else {
                name = "";
            }
            String date = CurrentDate;
            textView.setText(date);
            String time = CurrentTime;
            textView.setText(time);
            tempdescription.setText("full");
            Event event = new Event(name, date, publicPosition);
            firebase.funcAddEvent(event);
            firebase.getEvent();
            //firstTimeRun = true;
            CheckEvents();
            listDate.add(CurrentDate);
            //Intent intent = new Intent(Intent.ACTION_INSERT);
            //  intent.putExtra(CalendarContract.Events.TITLE, "Haircut to "+name);
            //  intent.setData(CalendarContract.Events.CONTENT_URI);
            //  intent.setType("vnd.android.cursor.item/event");
            // intent.putExtra(CalendarContract.Events.ACCOUNT_NAME,firebaseUser.getEmail());
            // intent.putExtra(CalendarContract.Events.DTSTART,time);//need to be in UTC millis
            // intent.putExtra(CalendarContract.Events.DTEND,time);
            // intent.putExtra(CalendarContract.Events.RDATE,"May 05, 2012, 07:10PM");
            // intent.putExtra(CalendarContract.Events.EXDATE,date);
            //startActivity(intent);


            //need create the recycleView with list to change color of delete item and block the button confrim
            //change time from available to not available


            //Create the event in google calendar API-------need fix


            //return from Calendar API to application->exit after add event

            //control with sync from API to application

            //Navigation to selectedSuccessFragments
        }

    }

    public static int CheckEvents()//need fix to func
    {

        for (int i = 0 ; i < eventList.size();i++) {
            //System.out.println(dateTime.getDateEvent());
            //System.out.println(CurrentDate);
            //System.out.println(eventList.get(i).getDateEvent());
            //System.out.println(listDate.size());

            for ( j=0; j < listDate.size();j++){

                System.out.println(listDate.get(j));

                if (eventList.get(i).getDateEvent().equals(listDate.get(j)) ) {
                    //tempdescription.setText("full");

                    //listDate.remove(j);
                    //System.out.println( eventList.get(i).getPositionEvent());
                    return eventList.get(i).getPositionEvent();
                }
            }

        }
        return 100 ;
            //tempdescription.onSaveInstanceState();

            //mAdapter= new ListAdaptor(timeSlots,getContext());
            //recyclerView.setAdapter(mAdapter);

            //timeSlots.remove(CurrentTime);

            //selectedCardView.setCardBackgroundColor(getContext().getColor(R.color.purple_700));
            // TimeSlot ev = new TimeSlot() ;
            //timeSlots.add(ev); ;


            //check all event in firebase and set text full

    }


}