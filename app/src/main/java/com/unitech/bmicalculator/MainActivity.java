package com.unitech.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;

    android.widget.Button calculateBmi;
    MaterialTextView currentState;
    MaterialTextView currentWeight, currentAge;
    ImageView incrementAge, decrementAge, incrementWeight, decrementWeight;
    SeekBar seekBarHeight;
    RelativeLayout male, female;

    int initialWeight = 55;
    int initialAge = 23;
    int currentProgress;
    String myProgress;
    String userType = "0";
    String weight2 = "55";
    String age2 = "23";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        calculateBmi = findViewById(R.id.bmiButton);
        currentAge = findViewById(R.id.currentAge);
        currentState = findViewById(R.id.currentState);
        currentWeight = findViewById(R.id.currentWeight);
        incrementAge = findViewById(R.id.incrementCurrentAge);
        incrementWeight = findViewById(R.id.incrementCurrentWeight);
        decrementAge = findViewById(R.id.decrementCurrentAge);
        decrementWeight = findViewById(R.id.decrementCurrentWeight);
        seekBarHeight = findViewById(R.id.heightSeekBar);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        alertDialog = new AlertDialog.Builder(this);

        male.setOnClickListener(v -> {
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.malefemalefocus));
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.malefemalenotfocus));
            userType = "Male";

        });

        female.setOnClickListener(v -> {
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.malefemalefocus));
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.malefemalenotfocus));
            userType = "Female";

        });

        seekBarHeight.setMax(300);
        seekBarHeight.setProgress(170);
        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress = progress;
                myProgress = String.valueOf(currentProgress);
                currentState.setText(myProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        incrementAge.setOnClickListener(v -> {
            initialAge++;
            age2 = String.valueOf(initialAge);
            currentAge.setText(age2);
        });

        decrementAge.setOnClickListener(v -> {
            initialAge--;
            age2 = String.valueOf(initialAge);
            currentAge.setText(age2);
        });

        incrementWeight.setOnClickListener(v -> {
            initialWeight++;
            weight2 = String.valueOf(initialWeight);
            currentWeight.setText(weight2);
        });

        decrementWeight.setOnClickListener(v -> {
            initialWeight--;
            weight2 = String.valueOf(initialWeight);
            currentWeight.setText(weight2);
        });

        calculateBmi.setOnClickListener(v -> {
            if (userType.equals("0")) {
                Toast.makeText(this, "Select Your Gender First", Toast.LENGTH_SHORT).show();
            } else if (myProgress.equals("0")){
                Toast.makeText(this, "Select Your Height First", Toast.LENGTH_SHORT).show();
            } else if (initialAge == 0 || initialAge < 0) {
                Toast.makeText(this, "Age is Incorrect", Toast.LENGTH_SHORT).show();
            } else if (initialWeight == 0 || initialWeight < 0) {
                Toast.makeText(this, "Weight is incorrect", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                intent.putExtra("gender",userType);
                intent.putExtra("height", myProgress);
                intent.putExtra("weight", weight2);
                intent.putExtra("age", age2);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onBackPressed() {
        alertDialog.setMessage("Do you want to log out?")
                .setCancelable(false)
                .setPositiveButton("Confirm", (dialog, id) -> {
                    dialog.dismiss();
                    finish();
                    System.exit(0);
                })
                .setNegativeButton("Cancel Exit", (dialog, id) -> {
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(), "EXIT CANCELLED",
                            Toast.LENGTH_SHORT).show();
                });
        //Create Alert dialog box
        AlertDialog alert = alertDialog.create();
        //Set Title
        alert.setTitle("Exit Application");
        alert.show();

    }
}