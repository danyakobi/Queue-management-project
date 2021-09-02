package com.example.queue_management_project.dbmodel;
import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class firebaseDp {
    private static firebaseDp object;
    private static FirebaseAuth myAuth;
    private static Activity activityDB;


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
    public static void createUser(String email,String password) {
        myAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activityDB, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                        } else {
                            // If sign in fails, display a message to the user.


                        }
                    }
                });

    }

public static void login(String email, String password){
    myAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activityDB, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                    } else {
                        // If sign in fails, display a message to the user.

                    }
                }
            });

}


}
