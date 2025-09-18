package com.example.agecalculatorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //These variables will be used to set up the values from the UI
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etDOB;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //These variables call the UI elements from the activity_main
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etDOB = findViewById(R.id.etDOB);
        btnCalculate = findViewById(R.id.btnCalculate);

        //Calculate button creates an event when is clicked on the UI
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            //This method calls the calculateAge method
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    //This Method calculates the age of the users when user inputs valid date of birth, first name
    // and last name
    private void calculateAge() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String dobString = etDOB.getText().toString().trim();

        //This statement makes sure any fields are not empty, if they are an, it will promt the user
        //with a toast message
        if (firstName.isEmpty() || lastName.isEmpty() || dobString.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            //This statement formats the date for easier read and compatibility
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date dob = sdf.parse(dobString);

            //These statements get today's date and set the time as zeros
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0); //Hour
            today.set(Calendar.MINUTE, 0);  //Minutes
            today.set(Calendar.SECOND, 0);  //Seconds
            today.set(Calendar.MILLISECOND, 0); //Milliseconds

            Calendar dobCal = Calendar.getInstance();
            //This sets the date of birth as calendar date instead of string for comparison
            dobCal.setTime(dob);

            //This statement will check the date and make sure date of birth and today's date are
            //not the same otherwise it will produce a toast message
            if (dobCal.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    dobCal.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                    dobCal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {

                Toast.makeText(this, "Date of birth should not be the same as today's date!", Toast.LENGTH_SHORT).show();
                return;
            }

            //This statements will calculate the age of the user
            int age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }
            //this toast displays the usre name and age in digits
            Toast.makeText(this, firstName + " " + lastName + " is " + age + " years old.", Toast.LENGTH_LONG).show();
            
        } catch (ParseException e) {
            //This toast will display if the user enters an invalid date
            Toast.makeText(this, "Invalid date format. Use YYYY-MM-DD.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
