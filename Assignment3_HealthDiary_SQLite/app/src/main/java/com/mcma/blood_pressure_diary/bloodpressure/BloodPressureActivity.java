package com.mcma.blood_pressure_diary.bloodpressure;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mcma.blood_pressure_diary.MainActivity;
import com.mcma.blood_pressure_diary.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BloodPressureActivity extends AppCompatActivity  {

    Button buttonSystolic;
    Button buttonDiastolic;
    Button btnAvg;
    Button btnBloodPressureBackHome;
    TextView timeStamp;
    TextView systolicValue;
    TextView diastolicValue;
    TextView systolicAverage;
    TextView diastolicAverage;

    BloodPressureCalcImpl bloodPressureCalc = new BloodPressureCalcImpl();
    List<BloodPressureReading> listOfAverages = new ArrayList<>();

    int lastSysPressure;
    int lastDiaPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressure);

        //Declaration of xml objects

        systolicValue = findViewById(R.id.systolicBloodPressure);
        diastolicValue = findViewById(R.id.diastolicBloodPressure);
        systolicAverage = findViewById(R.id.systolicAverage);
        diastolicAverage = findViewById(R.id.diastolicAverage);
        timeStamp =  findViewById(R.id.timeStamp);
        buttonSystolic = findViewById(R.id.btnSystolic);
        buttonDiastolic = findViewById(R.id.btnDiastolic);
        btnAvg = findViewById(R.id.btnAvg);
        btnAvg.setBackgroundColor(Color.RED);
        btnBloodPressureBackHome = findViewById(R.id.btnBloodPressureBackHome);
        btnBloodPressureBackHome.setBackgroundColor(Color.GRAY);


        btnBloodPressureBackHome.setOnClickListener(view -> {
            Intent homePage = new Intent(BloodPressureActivity.this, MainActivity.class);
            lastSysPressure = Integer.parseInt(systolicValue.getText().toString().trim());
            lastDiaPressure = Integer.parseInt(diastolicValue.getText().toString().trim());

            homePage.putExtra("lastSysPressure", lastSysPressure);
            homePage.putExtra("lastDiaPressure", lastDiaPressure);
            setResult(2,homePage);
            finish(); //finishing activity
        });
    }


    /* Onclick methods  */

    @SuppressLint("SetTextI18n")
    public void onClickSystolic(View view) {
        int randomRoll = getRandomSystolic();
        systolicValue.setText(Integer.toString(randomRoll));
    }

    @SuppressLint("SetTextI18n")
    public void onClickDiastolic(View view) {
        int randomRoll = getRandomDiastolic();
        diastolicValue.setText(Integer.toString(randomRoll));
    }

    @SuppressLint("SetTextI18n")
    public void onClickAvg(View v) {

        BloodPressureReading average = bloodPressureCalc.calcAverage(listOfAverages);
        int avgS = average.getSys();
        int avgD = average.getDia();

        systolicAverage.setText(Integer.toString(avgS));
        diastolicAverage.setText(Integer.toString(avgD));
    }

    public void onClickSave(View v) {
        String sys = systolicValue.getText().toString();
        String dia = diastolicValue.getText().toString();
        BloodPressureReading bloodPressureReading= new BloodPressureReading(Integer.parseInt(sys), Integer.parseInt(dia));
        listOfAverages.add(bloodPressureReading);
        onClickShowDate(v, bloodPressureReading);
    }

    public void onClickShowDate(View v, @NonNull BloodPressureReading bloodPressureReading) {
        String time = convertTime(bloodPressureReading.getTimeStamp());
        timeStamp.setText(time);
    }

    /* Private support methods  */
    private int getRandomSystolic() {
        Random random = new Random();
        return random.nextInt(180 - 100) + 100;
    }

    private int getRandomDiastolic() {
        Random random = new Random();
        return random.nextInt(105 - 60) + 60;
    }

    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

}