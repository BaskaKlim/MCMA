package com.mcma.blood_pressure_diary.impl.bloodpressure;

import com.mcma.blood_pressure_diary.models.bloodpressure.BloodPressureReading;

import java.util.LinkedList;

public class BloodPressureCalcImpl implements IBloodPressureCalc {
    LinkedList<Integer> sysList = new LinkedList<>();
    LinkedList<Integer> diaList = new LinkedList<>();
    int sumOfSys = 0;
    int sumOfDia = 0;

    @Override
    public BloodPressureReading calcAverage(LinkedList<BloodPressureReading> bloodPressureReadings) {

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

    @Override
    public int numberOfMeasurements(LinkedList<BloodPressureReading> bloodPressureReadings) {
        return  bloodPressureReadings.size();
    }

    @Override
    public long timeOfLastMeasurement(LinkedList<BloodPressureReading> bloodPressureReadings) {
        return bloodPressureReadings.getLast().getTimeStamp();
    }
}