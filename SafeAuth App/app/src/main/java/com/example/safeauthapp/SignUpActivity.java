package com.example.safeauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//This class Sign-Up screen that allows new users to create accounts using Firebase authentication and Firestore
public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private EditText etName, etEmail, etPassword;

    //This method connects to Firebase authentication and Firestore services.
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(v -> createAccount());
    }

    //This method handles all the logic for registration
    private void createAccount() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pass  = etPassword.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(t -> {
            if (!t.isSuccessful()) {
                Toast.makeText(this, "Sign up failed: " + t.getException().getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
            // This Updates the user
            auth.getCurrentUser().updateProfile(
                    new UserProfileChangeRequest.Builder().setDisplayName(name).build()
            );

            // Save user profile in Firestore
            Map<String,Object> userDoc = new HashMap<>();
            userDoc.put("name", name);
            userDoc.put("email", email);
            db.collection("users").document(auth.getCurrentUser().getUid())
                    .set(userDoc);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
