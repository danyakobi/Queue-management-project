package com.example.queue_management_project.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queue_management_project.Adapter.MyTimeSlotAdapter;
import com.example.queue_management_project.Interface.ITimeSlotLoadListener;
import com.example.queue_management_project.Model.TimeSlot;
import com.example.queue_management_project.R;
import com.example.queue_management_project.SpaceItemDecoration;
import com.example.queue_management_project.databinding.FragmentMainBookingBinding;
import com.google.firebase.firestore.CollectionReference;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainBooking extends Fragment implements ITimeSlotLoadListener  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @NonNull
    private FragmentMainBookingBinding binding;
    private Unbinder mUnbinder;
    private CollectionReference Doc;
    private static RecyclerView recyclerView;
    ITimeSlotLoadListener iTimeSlotLoadListener;
    AlertDialog dialog;
    static View.OnClickListener myOnClickListener;
    LocalBroadcastManager localBroadcastManager;
    Calendar selected_date;
    SimpleDateFormat simpleDateFormat;


    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE,0);
            loadAvailableTimeSlot(simpleDateFormat.format(date.getTime()));

        }
    };

    private void loadAvailableTimeSlot(String date) {
        dialog.show();
       // Doc = FirebaseFirestore.getInstance().collection(date);
      //  Doc.get().addOnCanceledListener(new OnCompleteListener<DocumentSnapshot>() {
        //    @Override
        //    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        //        if(task.

        //    }
       // })

    }

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
        iTimeSlotLoadListener = this;
        simpleDateFormat= new SimpleDateFormat("dd_MM_yyyy");
        dialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();
        selected_date = Calendar.getInstance();
        // Init current date
        selected_date.add(Calendar.DATE, 0);
    }
    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_main_booking, container, false);
        init(itemView);



        mUnbinder = ButterKnife.bind(this, itemView);

        return itemView;
    }

    private void init(View itemView) {
        //myOnClickListener = new MyOnClickListener(this);
        binding.recyclerTimeSlot.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        //List <MyTimeSlotAdapter> adapter= new MyTimeSlotAdapter(getContext(),ListAdapter);
        //need to repair the List adapter maybe better  create new recyleView;

       // binding.recyclerTimeSlot.setAdapter();
        binding.recyclerTimeSlot.setLayoutManager(gridLayoutManager);

       // binding.recyclerTimeSlot.addItemDecoration(new SpaceItemDecoration(8));
        binding.recyclerTimeSlot.setItemAnimator(new DefaultItemAnimator());

        // Calendar
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 0);

        Calendar endDate = Calendar.getInstance();
        // 2 day left
        endDate.add(Calendar.DATE, 2);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(itemView, R.id.calendarViewView)
                .range(startDate, endDate)
                .datesNumberOnScreen(1)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                if (selected_date.getTimeInMillis() != date.getTimeInMillis()) {
                    // This code will not load again if you select new day same with day selected
                    selected_date=date;
                    loadAvailableTimeSlot(simpleDateFormat.format(date.getTime()));
                }
            }
        });
    }

    @Override
    public void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList) {

        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext(), timeSlotList);
        binding.recyclerTimeSlot.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onTimeSlotLoadFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void onTimeSlotLoadEmpty() {
        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext());
        binding.recyclerTimeSlot.setAdapter(adapter);

        dialog.dismiss();
    }



}