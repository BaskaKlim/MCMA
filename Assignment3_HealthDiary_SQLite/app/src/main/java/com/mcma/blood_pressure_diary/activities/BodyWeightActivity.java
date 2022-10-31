package com.mcma.blood_pressure_diary.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.mcma.blood_pressure_diary.dao.BloodPressureReadingDAO;
import com.mcma.blood_pressure_diary.dao.BodyWeightReadingDAO;
import com.mcma.blood_pressure_diary.impl.bodyweight.BodyWeightCalcImpl;
import com.mcma.blood_pressure_diary.models.bodyweight.BodyWeightReading;

import java.util.Random;

public class BodyWeightActivity extends AppCompatActivity {

    ImageView bodyWeightImg;
    EditText bodyWeightValue;
    Button btnSaveWeight;
    Button btnGenerateWeight;
    Button btnWeightBackHome;
    TextView lastMeasurementText;
    TextView lastMeasurement;
    TextView numberOfMeasurementsText;
    TextView numberOfMeasurements;

    BodyWeightCalcImpl bodyWeight = new BodyWeightCalcImpl();
    BodyWeightReadingDAO dataBaseHelper = new BodyWeightReadingDAO(BodyWeightActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyweight);

        bodyWeightImg = findViewById(R.id.bodyWeightImg);
        bodyWeightValue = findViewById(R.id.bodyWeightValue);
        btnSaveWeight = findViewById(R.id.btnSaveWeight);
        btnGenerateWeight = findViewById(R.id.btnGenerateWeight);
        lastMeasurementText = findViewById(R.id.lastMeasurementText);
        lastMeasurement = findViewById(R.id.lastMeasurement);
        numberOfMeasurementsText = findViewById(R.id.numberOfMeasurementsText);
        numberOfMeasurements = findViewById(R.id.numberOfMeasurements);
        btnWeightBackHome = findViewById(R.id.btnWeightBackHome);
        btnWeightBackHome.setBackgroundColor(Color.GRAY);

        /* setting values from DB when the page is loaded */

        if( dataBaseHelper.getAllWeightRecords().size() >=1){
            int lastMeasurementValueOnStart = bodyWeight.getLastMeasurement(dataBaseHelper.getAllWeightRecords());
            int numberOfMeasurementsValue = bodyWeight.numberOfMeasurements(dataBaseHelper.getAllWeightRecords());

            lastMeasurement.setText(Integer.toString(lastMeasurementValueOnStart));
            numberOfMeasurements.setText(Integer.toString(numberOfMeasurementsValue));

        }

        /* intent handling data */
        btnWeightBackHome.setOnClickListener(view -> {
            Intent homePage = new Intent(BodyWeightActivity.this, MainActivity.class);
            int avgWeight = calAverage(view);

            homePage.putExtra("avgWeight", avgWeight);
            setResult(2, homePage);
            finish();
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
        BodyWeightReading bodyWeightReading = new BodyWeightReading(Integer.parseInt(data));
        dataBaseHelper.addWeightRecord(bodyWeightReading);
        showMeasurements(v);

    }
    /* Private support methods  */

    private int getRandomWeight() {
        Random random = new Random();
        return random.nextInt(220 - 45) + 45;
    }

    public void showMeasurements(View v) {
        int measurements = bodyWeight.numberOfMeasurements(dataBaseHelper.getAllWeightRecords());
        numberOfMeasurements.setText(Integer.toString(measurements));

        int lastMeasurementValue = bodyWeight.getLastMeasurement(dataBaseHelper.getAllWeightRecords());
        lastMeasurement.setText(Integer.toString(lastMeasurementValue));
    }

    public int calAverage(View v) {
        BodyWeightReading average = bodyWeight.calcAverage(dataBaseHelper.getAllWeightRecords());
        return average.getBodyWeight();
    }


}
