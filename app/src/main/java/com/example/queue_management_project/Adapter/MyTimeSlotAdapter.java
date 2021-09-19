package com.example.queue_management_project.Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queue_management_project.Activity.CalendarActivity;
import com.example.queue_management_project.Interface.IRecyclerItemSelectedListener;
import com.example.queue_management_project.Model.TimeSlot;
import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentMainBookingBinding;
import com.google.common.eventbus.EventBus;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {
    private static final String TAG = MyTimeSlotAdapter.class.getSimpleName();

    Context context;
    private List<TimeSlot> timeSlotList;
    private List<CardView> mCardViewList;

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
        this.mCardViewList = new ArrayList<>();

    }
    public MyTimeSlotAdapter() {
        this.timeSlotList = new ArrayList<>();
        this.mCardViewList = new ArrayList<>();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_time_slot,parent,false);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.text_time_slot.setText(new StringBuilder(convertTimeToString(position)).toString());
        if(timeSlotList.size()==0)
        {
            holder.card_view.setEnabled(true);
            holder.card_view.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.text_time_slot_description.setText("Available!");
            holder.text_time_slot_description.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
            holder.text_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));
            //holder.card_view.setEnabled(true);


        }
        else
        {
            for(TimeSlot slotValue:timeSlotList)
            {
                int slot=Integer.parseInt(slotValue.getSlot().toString());
                if(slot==position)
                {
                    //holder.card_view.setEnabled(false);
                    //holder.card_view.setTag(CalendarActivity.DISABLE_TAG);
                    holder.card_view.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    holder.text_time_slot_description.setText("Full");
                    holder.text_time_slot_description.setTextColor(context.getResources()
                            .getColor(android.R.color.black));
                    holder.text_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

                }
            }

        }
        if (!mCardViewList.contains(holder.card_view)) {
            mCardViewList.add(holder.card_view);
        }
        if (!timeSlotList.contains(position)) {
            holder.setIRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {
                    // Loop all card in card list
                    for (CardView cardView : mCardViewList) {
                        // Only available card time slot be change
                        if (cardView.getTag() == null) {
                            cardView.setCardBackgroundColor(context.getResources()
                                    .getColor(android.R.color.white));
                        }
                    }
                    // Our selected card will be change color
                    holder.card_view.setCardBackgroundColor(context.getResources()
                            .getColor(android.R.color.holo_orange_dark));

                    // After that, send broadcast to enable button next
//                    Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                    // Put index of time slot we have selected
//                    intent.putExtra(Common.KEY_TIME_SLOT, position);
                    // Go to step3
//                    intent.putExtra(Common.KEY_STEP, 3);
//                    mLocalBroadcastManager.sendBroadcast(intent);

                    // EventBus
                    //EventBus.getDefault().postSticky(new EnableNextButton(3, position));
                }
            });
        }




    }

    @Override
    public int getItemCount() { return 20; }

    public class MyViewHolder extends RecyclerView.ViewHolder{

         TextView text_time_slot,text_time_slot_description;
         CardView card_view;
         IRecyclerItemSelectedListener mIRecyclerItemSelectedListener;


        private void setIRecyclerItemSelectedListener(IRecyclerItemSelectedListener IRecyclerItemSelectedListener) {
            mIRecyclerItemSelectedListener = IRecyclerItemSelectedListener;
        }
        public MyViewHolder(View itemView) {
            super(itemView);
            this.card_view= (CardView) itemView.findViewById(R.id.card_view);
            this.text_time_slot=(TextView)itemView.findViewById(R.id.text_time_slot);
            this.text_time_slot_description=(TextView)itemView.findViewById(R.id.text_time_slot_description);

        }

    }
    public static String convertTimeToString(int slot)
    {
        switch (slot) {
            case 0:
                return "9:00-9:30";
            case 1:
                return "9:30-10:00";
            case 2:
                return "10:00-10:30";
            case 3:
                return "10:30-11:00";
            case 4:
                return "11:00-11:30";
            case 5:
                return "11:30-12:00";
            case 6:
                return "12:00-12:30";
            case 7:
                return "12:30-13:00";
            case 8:
                return "13:00-13:30";
            case 9:
                return "13:30-14:00";
            case 10:
                return "14:00-14:30";
            case 11:
                return "14:30-15:00";
            case 12:
                return "15:00-15:30";
            case 13:
                return "15:30-16:00";
            case 14:
                return "16:00-16:30";
            case 15:
                return "16:30-17:00";
            case 16:
                return "17:00-17:30";
            case 17:
                return "17:30-18:00";
            case 18:
                return "18:00-18:30";
            case 19:
                return "18:30-19:00";
            default:
                return "Closed";


        }

    }
}
