package com.example.queue_management_project.dbmodel;
import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.queue_management_project.Model.Event;
import com.example.queue_management_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDp {
    private static firebaseDp object;
    private static FirebaseAuth myAuth;
    private static Activity activityDB;
    public static List<Event> eventList=new ArrayList<Event>();
    private static boolean flagLogin;
    private static boolean flagRegister;
      String tempFullName;
      String tempPhone;

    private firebaseDp(){
        myAuth = FirebaseAuth.getInstance();

    }
    public static firebaseDp getInstance(Activity activity){
        if (object == null){
            FirebaseApp.initializeApp(activity);
            activityDB=activity;
            object = new firebaseDp();



        }
        return object;
    }
    public static boolean createUser(String email,String password) {
        myAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activityDB, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            flagRegister = true;



                        } else {
                            // If sign in fails, display a message to the user.
                            flagRegister = false;

                        }
                    }

                });
           return flagRegister;
    }

    public static boolean login(String email, String password){
        myAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activityDB, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //I want to do this

                        // Sign in success, update UI with the signed-in user's information
                        flagLogin = true;

                    } else {
                        // If sign in fails, display a message to the user.
                        flagLogin = false;

                    }
                }
            });
        return flagLogin;
    }

    public void funcAddData(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FirebaseUser currentUser = myAuth.getCurrentUser();
        String uid = user.getFullName();
        DatabaseReference myRef = database.getReference("users").child(uid);
        myRef.setValue(user);

    }

    public void getData(String userName){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FirebaseUser currentUser = myAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("users").child(userName);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.getValue(User.class);
                 // tempFullName =String.va,child("fullName").getValue().toString();
                //tempPhone=dataSnapshot.child("phone").getValue().toString();

                //get data by.........................................................!!!!!!



                Log.wtf(TAG, "Value is: " + tempFullName);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public String getUserName(User user) {//need fix this func

        return user.getFullName() ;
    }
    public String getUserPhone(){
        return tempPhone;

    }
    public void UpdateUserDetails(User user){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(user.getFullName()).build();
        firebaseUser.updateProfile(profileUpdates);
        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

    }
    public void funcAddEvent(Event event){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FirebaseUser currentUser = myAuth.getCurrentUser();
        String uid = event.getNameEvent();
        DatabaseReference myRef = database.getReference("events").child(uid);
        myRef.setValue(event);


    }
    public void getEvent(){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("events");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Event value = snapshot.getValue(Event.class);
                    //temp2.setDateEvent(value.getDateEvent());
                    eventList.add(value);



                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }



}
