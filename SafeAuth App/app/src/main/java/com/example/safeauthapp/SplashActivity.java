package com.example.safeauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    // 3 seconds of splash duration
    public static final int splashLength = 3000;

    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
