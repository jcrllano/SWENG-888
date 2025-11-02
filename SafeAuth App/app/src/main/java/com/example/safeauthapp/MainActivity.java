package com.example.safeauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.safeauth.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public abstract class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);
        // Default
        replace(new ListFragment());
    }

    private void replace(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, f).commit();
        binding.drawerLayout.close();
    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_items)      replace(new ListFragment());
        else if (id == R.id.nav_account) replace(new AccountFragment());
        else if (id == R.id.nav_settings) replace(new SettingsFragment());
        else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish(); // Splash will reroute to Login on next launch
        }
        return true;
    }

}