package com.mcma.blood_pressure_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnBloodPressure;
    Button btnBodyWeight;
    TextView lastMeasurement;
    TextView lastDiaPressure;
    TextView lastSysPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBloodPressure = findViewById(R.id.btnBloodPressure);
        btnBodyWeight = findViewById(R.id.btnBodyWeight);
        lastMeasurement = findViewById(R.id.lastMeasurement);
        lastDiaPressure = findViewById(R.id.lastDiaPressure);
        lastSysPressure = findViewById(R.id.lastSysPressure);


        /* Intents for BloodPressure & BodyWeight Activities */
        btnBloodPressure.setOnClickListener(view -> {

            Intent bloodPressurePage = new Intent(MainActivity.this, BloodPressureActivity.class);
            startActivityForResult(bloodPressurePage, 2);
        });

        btnBodyWeight.setOnClickListener(view -> {

            Intent bodyWeightPage = new Intent(MainActivity.this, BodyWeightActivity.class);
            startActivityForResult(bodyWeightPage, 2);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            /* Getting data from activities */
            int weight = data.getIntExtra("lastMeasurement", 0);
            int systolic = data.getIntExtra("lastSysPressure", 0);
            int diastolic = data.getIntExtra("lastDiaPressure", 0);

            lastMeasurement.setText("Your last measurement of weight is: ".concat(String.valueOf(weight)));
            lastSysPressure.setText("Your last measurement of sys is: ".concat(String.valueOf(systolic)));
            lastDiaPressure.setText("Your last measurement of dia is: ".concat(String.valueOf(diastolic)));

        }
    }

}