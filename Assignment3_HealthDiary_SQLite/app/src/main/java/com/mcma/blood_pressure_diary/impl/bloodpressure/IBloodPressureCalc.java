package com.mcma.blood_pressure_diary.impl.bloodpressure;

import com.mcma.blood_pressure_diary.models.bloodpressure.BloodPressureReading;

import java.util.LinkedList;

/**
 * This interface shall be used to calculate the average blood pressure
 */
public interface IBloodPressureCalc {

    BloodPressureReading calcAverage(LinkedList<BloodPressureReading> bloodPressureReadings);

    int numberOfMeasurements(LinkedList<BloodPressureReading> bloodPressureReadings);

    long timeOfLastMeasurement(LinkedList<BloodPressureReading> bloodPressureReadings);
}
