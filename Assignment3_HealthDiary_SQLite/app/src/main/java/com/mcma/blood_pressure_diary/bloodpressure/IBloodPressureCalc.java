package com.mcma.blood_pressure_diary.bloodpressure;

import com.mcma.blood_pressure_diary.bloodpressure.BloodPressureReading;

import java.util.List;

/**
 * This interface shall be used to calculate the average blood pressure
 */
public interface IBloodPressureCalc {

    /**
     * This method takes a number of blood pressure readings and
     * calculates the average systolic and diastolic blood pressure
     * @param bloodPressureReadings
     * @return average of systolic and diastolic readings (the timeStamp can be empty)
     */
    BloodPressureReading calcAverage(List<BloodPressureReading> bloodPressureReadings);
}
