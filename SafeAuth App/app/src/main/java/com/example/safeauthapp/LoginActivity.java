package com.example.safeauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    public EditText emailInput, passwordInput;
    public FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView signUpLink = findViewById(R.id.SignUpLink);

        loginBtn.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("username", email);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(e ->
                        Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}


