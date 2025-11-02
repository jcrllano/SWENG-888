package com.example.safeauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView tvWelcome = view.findViewById(R.id.tvWelcome);
        TextView tvEmail   = view.findViewById(R.id.tvEmail);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String name  = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            tvWelcome.setText("Welcome, " + (name != null ? name : "(no name)"));
            tvEmail.setText(email);
        }
        return view;
    }
}
