package com.mcma.blood_pressure_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mcma.blood_pressure_diary.activities.BloodPressureActivity;
import com.mcma.blood_pressure_diary.activities.BodyWeightActivity;
import com.mcma.blood_pressure_diary.dao.BloodPressureReadingDAO;
import com.mcma.blood_pressure_diary.dao.BodyWeightReadingDAO;
import com.mcma.blood_pressure_diary.dao.HealthDiaryDAO;
import com.mcma.blood_pressure_diary.impl.bloodpressure.BloodPressureCalcImpl;
import com.mcma.blood_pressure_diary.impl.bodyweight.BodyWeightCalcImpl;

public class MainActivity extends AppCompatActivity {

    Button btnBloodPressure;
    Button btnBodyWeight;
    TextView diastolicAverage;
    TextView systolicAverage;
    TextView weightAvgValue;

    BloodPressureReadingDAO dataBaseHelperBlood = new BloodPressureReadingDAO(MainActivity.this);
    BodyWeightReadingDAO dataBaseHelperWeight = new BodyWeightReadingDAO(MainActivity.this);
    BloodPressureCalcImpl bloodPressureCalc = new BloodPressureCalcImpl();
    BodyWeightCalcImpl bodyWeightCalc = new BodyWeightCalcImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBloodPressure = findViewById(R.id.btnBloodPressure);
        btnBodyWeight = findViewById(R.id.btnBodyWeight);

        weightAvgValue = findViewById(R.id.weightAvgValue);
        diastolicAverage = findViewById(R.id.diastolicAverage);
        systolicAverage = findViewById(R.id.systolicAverage);


        /* setting values from DB when the page is loaded */
        showAvgDataFromDatabase();


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
    protected void onResume() {
        super.onResume();
        showAvgDataFromDatabase();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            /* Getting data from activities */
            int avgWeight = data.getIntExtra("avgWeight", 0);
            int systolic = data.getIntExtra("systolicAverage", 0);
            int diastolic = data.getIntExtra("diastolicAverage", 0);

            weightAvgValue.setText("Your average weight is: ".concat(String.valueOf(avgWeight)));
            systolicAverage.setText("Your average sys is: ".concat(String.valueOf(systolic)));
            diastolicAverage.setText("Your average dia is: ".concat(String.valueOf(diastolic)));

        }
    }

    void showAvgDataFromDatabase() {
        if (dataBaseHelperBlood.getAllBloodPressureRecords().size() >= 1) {
            int avgSys = bloodPressureCalc.calcAverage(dataBaseHelperBlood.getAllBloodPressureRecords()).getSys();
            int avgDia = bloodPressureCalc.calcAverage(dataBaseHelperBlood.getAllBloodPressureRecords()).getDia();

            systolicAverage.setText("Your average sys is: ".concat(String.valueOf(avgSys)));
            diastolicAverage.setText("Your average dia is: ".concat(String.valueOf(avgDia)));
        }
        if (dataBaseHelperWeight.getAllWeightRecords().size() >= 1) {
            int avgWeight = bodyWeightCalc.calcAverage(dataBaseHelperWeight.getAllWeightRecords()).getBodyWeight();
            weightAvgValue.setText("Your average weight is: ".concat(String.valueOf(avgWeight)));

        }
    }

}