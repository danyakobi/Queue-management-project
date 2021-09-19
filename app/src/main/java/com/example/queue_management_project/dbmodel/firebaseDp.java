package com.example.queue_management_project.dbmodel;
import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.queue_management_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class firebaseDp {
    private static firebaseDp object;
    private static FirebaseAuth myAuth;
    private static Activity activityDB;
    private static boolean flagLogin;
    private static boolean flagRegister;
    private static String tempFullName;
    private static String tempPhone;

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

    public void getData(String uid){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FirebaseUser currentUser = myAuth.getCurrentUser();
        DatabaseReference myRef = database.getReference("users").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.getValue(User.class);
                tempFullName=value.getFullName();
                tempPhone=value.getPhone();

                //get data by.........................................................!!!!!!



                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public User getUserDetails() {
        User user = new User();
        user.setFullName(tempFullName);
        user.setPhone(tempPhone);
        return user;
    }



}
