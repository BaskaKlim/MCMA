package com.mcma.blood_pressure_diary.entities.bodyweight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mcma.blood_pressure_diary.MainActivity;
import com.mcma.blood_pressure_diary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BodyWeightActivity extends AppCompatActivity {

    ImageView bodyWeightImg;
    EditText bodyWeightValue;
    Button btnSaveWeight;
    Button btnGenerateWeight;
    Button btnWeightBackHome;
    TextView weightTxt;
    TextView weightAvgValue;

    BodyWeightCalcImpl bodyWeight = new BodyWeightCalcImpl();
    List<BodyWeightReading> listOfWeights = new ArrayList<>();

    int lastBodyWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyweight);

        bodyWeightImg = findViewById(R.id.bodyWeightImg);
        bodyWeightValue = findViewById(R.id.bodyWeightValue);
        btnSaveWeight = findViewById(R.id.btnSaveWeight);
        btnGenerateWeight = findViewById(R.id.btnGenerateWeight);
        weightTxt = findViewById(R.id.weightTxt);
        weightAvgValue = findViewById(R.id.weightAvgValue);
        btnWeightBackHome = findViewById(R.id.btnWeightBackHome);
        btnWeightBackHome.setBackgroundColor(Color.GRAY);


        btnWeightBackHome.setOnClickListener(view -> {
            Intent homePage = new Intent(BodyWeightActivity.this, MainActivity.class);
            lastBodyWeight = Integer.parseInt(bodyWeightValue.getText().toString().trim());
            homePage.putExtra("lastMeasurement", lastBodyWeight);
            setResult(2,homePage);
            finish();//finishing activity
        });
    }


    /* Onclick methods  */

    @SuppressLint("SetTextI18n")
    public void onClickGenerate(View view) {
        int randomRoll = getRandomWeight();
        bodyWeightValue.setText(Integer.toString(randomRoll));
    }


    public void onClickSave(View v) {
        String data = bodyWeightValue.getText().toString();
        BodyWeightReading bodyWeightReading= new BodyWeightReading(Integer.parseInt(data));
        listOfWeights.add(bodyWeightReading);
        calAverage(v);

    }
    /* Private support methods  */

    private int getRandomWeight() {
        Random random = new Random();
        return random.nextInt(220 - 45) + 45;
    }

    @SuppressLint("SetTextI18n")
    public void calAverage(View v) {
        BodyWeightReading average = bodyWeight.calcAverage(listOfWeights);
        int avg = average.getBodyWeight();
        weightAvgValue.setText(Integer.toString(avg));
    }



}
