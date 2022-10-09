package com.mcma.blood_pressure_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnBloodPressure;
    Button btnBodyWeight;
    TextView lastSysPressure;
    TextView lastDiaPressure;
    TextView lastBodyWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBloodPressure = findViewById(R.id.btnBloodPressure);
        btnBodyWeight = findViewById(R.id.btnBodyWeight);
        lastSysPressure = findViewById(R.id.lastSysPressure);
        lastDiaPressure = findViewById(R.id.lastDiaPressure);
        lastBodyWeight = findViewById(R.id.lastBodyWeight);

        /* Intents for BloodPressure & BodyWeight Activities */
        btnBloodPressure.setOnClickListener(view -> {

            Intent bloodPressurePage = new Intent(MainActivity.this, BloodPressureActivity.class);
            startActivity(bloodPressurePage);
        });

        btnBodyWeight.setOnClickListener(view -> {

            Intent bodyWeightPage = new Intent(MainActivity.this, BodyWeightActivity.class);
            startActivity(bodyWeightPage);
        });

        /* Intents for getting data from activities */
        Intent data = getIntent();

        lastSysPressure.setText(String.valueOf(data.getIntExtra("lastSysPressure", 0)));
        lastDiaPressure.setText(String.valueOf(data.getIntExtra("lastDiaPressure", 0)));
        lastBodyWeight.setText(String.valueOf(data.getIntExtra("lastBodyWeight", 0)));

    }
}