package com.unitech.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class BMIActivity extends AppCompatActivity {

    MaterialTextView displayBmi, bmiCategory, displayGender;
    Intent intent;
    ImageView image;
    String bmi;
    float myBmi;

    String height;
    String weight;
    float initialWeight, initialHeight;
    RelativeLayout myBackground;


    android.widget.Button reCalculateBmi;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        intent = getIntent();

        displayBmi = findViewById(R.id.bmiDisplay);
        bmiCategory = findViewById(R.id.category);
        displayGender = findViewById(R.id.displayGender);
        myBackground = findViewById(R.id.contentLayout);
        reCalculateBmi = findViewById(R.id.reCalculateBmi);
        image = findViewById(R.id.image);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        initialHeight = Float.parseFloat(height);
        initialWeight = Float.parseFloat(weight);

        initialHeight = initialHeight/100;

        //Calculate BMI
        myBmi = initialWeight / (initialHeight * initialHeight);

        bmi = Float.toString(myBmi);

        if (myBmi < 16){
            bmiCategory.setText("Severe Thinness");
            myBackground.setBackgroundColor(Color.RED);
            image.setImageResource(R.drawable.crosss);
        }else if (myBmi < 16.9 && myBmi > 16){
            bmiCategory.setText("Moderate Thinness");
            myBackground.setBackgroundColor(Color.RED);
            //Warning
            image.setImageResource(R.drawable.warning);
        }else if (myBmi < 18.4 && myBmi > 17){
            bmiCategory.setText("Mild Thinness");
            myBackground.setBackgroundColor(Color.RED);
            //Warning
            image.setImageResource(R.drawable.warning);
        }else if (myBmi < 25 && myBmi > 18.4){
            bmiCategory.setText("Normal");
            //myBackground.setBackgroundColor(Color.YELLOW);
            //Ok
            //image.setImageResource(R.drawable.ic_launcher_foreground);
        }else if (myBmi < 29.4 && myBmi > 25){
            bmiCategory.setText("Overweight");
            myBackground.setBackgroundColor(Color.RED);
            //Warning
            image.setImageResource(R.drawable.warning);
        } else {
            bmiCategory.setText("Obesity");
            myBackground.setBackgroundColor(Color.RED);
            //Warning
            image.setImageResource(R.drawable.crosss);
        }

        displayGender.setText(intent.getStringExtra("gender"));
        displayBmi.setText(bmi);


        reCalculateBmi.setOnClickListener(v -> {
            startActivity(new Intent(BMIActivity.this, MainActivity.class));
            finish();
        });
    }
}