package com.example.safeauthapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

//This class initializes the firebase authentication
public class FirebaseInitializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase once for the entire app process
        FirebaseApp.initializeApp(this);
    }
}
