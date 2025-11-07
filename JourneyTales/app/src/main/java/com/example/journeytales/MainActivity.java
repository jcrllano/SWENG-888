package com.example.journeytales;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //This initialize the DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //This set the listener for the NavigationView
        navigationView.setNavigationItemSelectedListener(this);

        // This finds and set the Toolbar as the ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //This will set the custom title on the top of the home page
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home Page");

        // Enable the navigation (back) icon
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //This sets up the action bar drawer toggle
        //This handles the hamburger icon and its animation.
        androidx.appcompat.app.ActionBarDrawerToggle toggle = new androidx.appcompat.app.ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Floating Action Button setup
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "FAB clicked!", Toast.LENGTH_SHORT).show();
        });
    }

    //This class handles the clicks on the navigation button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Navigation button clicked!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //This class will handle the settings icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Toast.makeText(this, "Settings button clicked!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_about) {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            fragment = new AboutFragment();
        } else if (id == R.id.nav_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }

        // 2. Load the selected Fragment (if one was chosen)
        if (fragment != null) {
            loadFragment(fragment);
            //This updates the Toolbar title
            getSupportActionBar().setTitle(item.getTitle());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //This loads the fragment Loading Helper Method
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //This replacea the content of the fragment_container with the new Fragment
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.commit();
    }

}