package com.mcma.blood_pressure_diary;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureCalcImpl implements IBloodPressureCalc {
    List<Integer> sysList = new ArrayList<>();
    List<Integer> diaList = new ArrayList<>();
    int sumOfSys = 0;
    int sumOfDia = 0;

    @Override
    public BloodPressureReading calcAverage(List<BloodPressureReading> bloodPressureReadings) {

        BloodPressureReading avgBloodPressure = new BloodPressureReading();

        for (BloodPressureReading bloodPressureReading : bloodPressureReadings) {
            sysList.add(bloodPressureReading.getSys());
            sumOfSys += bloodPressureReading.getSys();

            diaList.add(bloodPressureReading.getDia());
            sumOfDia += bloodPressureReading.getDia();

        }
        if (bloodPressureReadings.size() != 0) {
            int sysAvg = sumOfSys / sysList.size();
            int diaAvg = sumOfDia / diaList.size();

            avgBloodPressure.setSys(sysAvg);
            avgBloodPressure.setDia(diaAvg);

            return avgBloodPressure;
        }
        return new BloodPressureReading(0,0);
    }
}