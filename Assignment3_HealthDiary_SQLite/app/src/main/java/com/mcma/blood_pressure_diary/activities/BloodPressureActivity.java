package com.mcma.blood_pressure_diary.activities;


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
import com.mcma.blood_pressure_diary.dao.BloodPressureReadingDAO;
import com.mcma.blood_pressure_diary.dao.BodyWeightReadingDAO;
import com.mcma.blood_pressure_diary.impl.bloodpressure.BloodPressureCalcImpl;
import com.mcma.blood_pressure_diary.models.bloodpressure.BloodPressureReading;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BloodPressureActivity extends AppCompatActivity {

    TextView systolicValue;
    TextView diastolicValue;
    Button buttonSystolic;
    Button buttonDiastolic;
    Button btnSave;
    TextView timeStamp;
    TextView numberOfMeasurementsText;
    TextView numberOfMeasurements;
    Button btnBloodPressureBackHome;

    BloodPressureCalcImpl bloodPressureCalc = new BloodPressureCalcImpl();
    BloodPressureReadingDAO dataBaseHelper = new BloodPressureReadingDAO(BloodPressureActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressure);

        /* Declaration of xml objects */

        systolicValue = findViewById(R.id.systolicBloodPressure);
        diastolicValue = findViewById(R.id.diastolicBloodPressure);
        numberOfMeasurementsText = findViewById(R.id.numberOfMeasurementsText);
        numberOfMeasurements = findViewById(R.id.numberOfMeasurements);
        timeStamp = findViewById(R.id.timeStamp);
        buttonSystolic = findViewById(R.id.btnSystolic);
        buttonDiastolic = findViewById(R.id.btnDiastolic);
        btnSave = findViewById(R.id.btnSave);
        btnBloodPressureBackHome = findViewById(R.id.btnBloodPressureBackHome);
        btnBloodPressureBackHome.setBackgroundColor(Color.GRAY);

        /* setting values from DB when the page is loaded */

        if( dataBaseHelper.getAllBloodPressureRecords().size() >=1) {
            int numberOfMeasurementsValue = bloodPressureCalc.numberOfMeasurements(dataBaseHelper.getAllBloodPressureRecords());
            String timeOfLastMeasurement = convertTime(bloodPressureCalc.timeOfLastMeasurement(dataBaseHelper.getAllBloodPressureRecords()));

            numberOfMeasurements.setText(Integer.toString(numberOfMeasurementsValue));
            timeStamp.setText(timeOfLastMeasurement);
        }

        /* intent handling data */
        btnBloodPressureBackHome.setOnClickListener(view -> {
            Intent homePage = new Intent(BloodPressureActivity.this, MainActivity.class);

            int systolicAverage = calAverageSys(view);
            int diastolicAverage = calAverageDia(view);

            homePage.putExtra("systolicAverage", systolicAverage);
            homePage.putExtra("diastolicAverage", diastolicAverage);
            setResult(2, homePage);
            finish();
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

    public void onClickSave(View v) {
        String sys = systolicValue.getText().toString();
        String dia = diastolicValue.getText().toString();

        BloodPressureReading bloodPressureReading = new BloodPressureReading(Integer.parseInt(sys), Integer.parseInt(dia));
        dataBaseHelper.addBloodPressureRecord(bloodPressureReading);

        onClickShowDate(v, bloodPressureReading);
        showMeasurements(v);

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

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

    public int calAverageSys(View v) {
        BloodPressureReading average = bloodPressureCalc.calcAverage(dataBaseHelper.getAllBloodPressureRecords());
        return average.getSys();
    }

    public int calAverageDia(View v) {
        BloodPressureReading average = bloodPressureCalc.calcAverage(dataBaseHelper.getAllBloodPressureRecords());
        return average.getDia();
    }

    public void showMeasurements(View v) {
        int lastMeasurementValue = bloodPressureCalc.numberOfMeasurements(dataBaseHelper.getAllBloodPressureRecords());
        numberOfMeasurements.setText(Integer.toString(lastMeasurementValue));
    }

}