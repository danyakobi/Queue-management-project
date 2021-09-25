package com.example.queue_management_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queue_management_project.R;
import com.example.queue_management_project.TimeSlot;

import java.util.List;

public class ListAdaptor extends RecyclerView.Adapter {



    private List<TimeSlot> mList;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<TimeSlot> mTimeSlotList;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    public static String CurrentTime;
    public static TextView tempdescription;
    public static int publicPosition;
    public static CardView selectedCardView;

    public ListAdaptor(LayoutInflater mLayoutInflater, List<TimeSlot> timeSlotList){
        this.mLayoutInflater = mLayoutInflater;
        this.mTimeSlotList = timeSlotList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time_slot,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
        ((ListViewHolder) holder).cardview.setCardBackgroundColor(mContext.getResources().getColor(android.R.color.background_light));
        if (selectedItem==position)
        {
             ((ListViewHolder) holder).cardview.setCardBackgroundColor(mContext.getResources().getColor(R.color.teal_700));
             tempdescription =((ListViewHolder) holder).itemView.findViewById(R.id.text_time_slot_description);
            selectedCardView  =((ListViewHolder) holder).cardview.findViewById(R.id.card_view);
             //tempdescription.setText("full");
            CurrentTime =TimeSlot.convertTimeSlotToString(position);
            publicPosition=position;

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mTimeSlotList.size();
    }


    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView time;
        private TextView description;
        private CardView cardview;
        public ListViewHolder(View view){
            super(view);
            time=(TextView) view.findViewById(R.id.text_time_slot);
            description=(TextView) view.findViewById(R.id.text_time_slot_description);
            cardview = (CardView) view.findViewById(R.id.card_view);
            view.setOnClickListener(this);

        }
        public void bindView(int position) {

            time.setText(TimeSlot.convertTimeSlotToString(position));
            //description.setText(TimeSlot.description[position]);
            time.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (selectedItem != getAdapterPosition())
                    {
                        notifyItemChanged(selectedItem);
                       selectedItem=getAdapterPosition();
                    }
                }
            });
        }


        @Override
        public void onClick(View view) {

        }
    }
    public ListAdaptor(List<TimeSlot> list , Context myText ){
        this.mContext=myText;
        this.mTimeSlotList=list;
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
    }

}
