package com.mcma.blood_pressure_diary;


import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IBloodPressureCalc {

    GridLayout gridLayout2;

    LinearLayout linearLayout;
    Button buttonSystolic;
    Button buttonDiastolic;
    Button btnAvg;
    TextView timeStamp;
    TextView systolicValue;
    TextView diastolicValue;
    TextView systolicAverage;
    TextView diastolicAverage;

    BloodPressureCalcImpl bloodPressureCalc = new BloodPressureCalcImpl();
    List<BloodPressureReading> listOfAverages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaration of xml objects
        gridLayout2 = (GridLayout) findViewById(R.id.gridLayout2) ;
        linearLayout = (LinearLayout) findViewById(R.id.linearImg);
        linearLayout.setAlpha(0.500f);  //opacity of second img

        systolicValue = (TextView) findViewById(R.id.systolicBloodPressure);
        diastolicValue = (TextView) findViewById(R.id.diastolicBloodPressure);
        systolicAverage = (TextView) findViewById(R.id.systolicAverage);
        diastolicAverage = (TextView) findViewById(R.id.diastolicAverage);
        timeStamp = (TextView) findViewById(Build.VERSION_CODES.R.id.timeStamp);


        buttonSystolic = (Button) findViewById(R.id.btnSystolic);
        buttonDiastolic = (Button) findViewById(R.id.btnDiastolic);
        btnAvg = (Button) findViewById(R.id.btnAvg);
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

        //cleaning the data
        listOfAverages.clear();
        avgS = 0;
        avgD = 0;
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



    @Override
    public BloodPressureReading calcAverage(List<BloodPressureReading> bloodPressureReadings) {

        List<Integer> sysList = new ArrayList<>();
        List<Integer> diaList = new ArrayList<>();

        int sumOfSys = 0;
        int sumOfDia = 0;

        BloodPressureReading avgBloodPressure = new BloodPressureReading();

        for (BloodPressureReading bloodPressureReading : bloodPressureReadings) {
            sysList.add(bloodPressureReading.getSys());
            sumOfSys += bloodPressureReading.getSys();

            diaList.add(bloodPressureReading.getDia());
            sumOfDia += bloodPressureReading.getDia();

            int sysAvg = sumOfSys / sysList.size();
            int diaAvg = sumOfDia / diaList.size();

            avgBloodPressure.setSys(sysAvg);
            avgBloodPressure.setDia(diaAvg);
        }
        return avgBloodPressure;
    }
}