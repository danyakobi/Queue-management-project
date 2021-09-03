package com.example.queue_management_project.dbmodel;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.example.queue_management_project.Activity.User;
import com.example.queue_management_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class firebaseDp {
    private static firebaseDp object;
    private static FirebaseAuth myAuth;
    private static Activity activityDB;
    private static boolean flagLogin;
    private static boolean flagRegister;

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
        FirebaseUser currentUser = myAuth.getCurrentUser();
        String uid = currentUser.getUid();
        DatabaseReference myRef = database.getReference("users").child(uid);
        myRef.setValue(user);
    }


}
