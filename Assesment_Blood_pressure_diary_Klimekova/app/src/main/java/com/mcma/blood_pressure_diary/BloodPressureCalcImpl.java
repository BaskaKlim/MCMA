package com.mcma.blood_pressure_diary;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureCalcImpl implements IBloodPressureCalc {
    List<Integer> sysList = new ArrayList<>();
    List<Integer> diaList = new ArrayList<>();

    @Override
    public BloodPressureReading calcAverage(List<BloodPressureReading> bloodPressureReadings) {
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
