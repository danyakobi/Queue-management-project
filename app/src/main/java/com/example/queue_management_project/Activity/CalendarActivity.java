package com.example.queue_management_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import com.example.queue_management_project.Model.User;
import com.example.queue_management_project.R;
import com.example.queue_management_project.dbmodel.firebaseDp;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Intent intent = getIntent();
        String str = intent.getStringExtra(KEY_DISPLAY_TIME_SLOT);
       // FirebaseUser firebaseUser = FirebaseAuth.getInstance(FirebaseApp.initializeApp(this)).getCurrentUser();
      //  firebaseDp firebase = firebaseDp.getInstance(this);
      //  if(firebaseUser != null) {
       //      uid = firebaseUser.getUid();
       // }

       // firebase.getData(uid);
        //User user = firebase.getUserDetails();


       // Intent intent = new Intent(Intent.ACTION_INSERT);
       // intent.putExtra(CalendarContract.Events.TITLE, user.getFullName());

       // intent.setData(CalendarContract.Events.CONTENT_URI);
       // intent.setType("vnd.android.cursor.item/event");
      //  intent.putExtra(CalendarContract.Events.EVENT_LOCATION, user.getFullName());
      //  intent.putExtra(CalendarContract.Events.DESCRIPTION, user.getPhone());
       // intent.putExtra(CalendarContract.Events.ACCOUNT_NAME,user.getFullName());
      //  intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com, test2@yahoo.com, test3@yahoo.com");
      //  startActivity(intent);


// Setting dates
      //  GregorianCalendar calDate = new GregorianCalendar(2012, 10, 02);
       // intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
        //        calDate.getTimeInMillis());
      //  intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
       //         calDate.getTimeInMillis());

// make it a full day event
       // intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

// make it a recurring Event
        //intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

// Making it private and shown as busy
        //intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        //intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);



    }



}
