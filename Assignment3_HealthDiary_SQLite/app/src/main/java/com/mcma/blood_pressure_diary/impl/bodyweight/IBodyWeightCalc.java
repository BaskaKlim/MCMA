package com.mcma.blood_pressure_diary.impl.bodyweight;

import com.mcma.blood_pressure_diary.models.bodyweight.BodyWeightReading;

import java.util.LinkedList;
import java.util.List;

/**
 * This interface shall be used to calculate the average body weight
 */
public interface IBodyWeightCalc {

    BodyWeightReading calcAverage(LinkedList<BodyWeightReading> bodyWeightReadings);

    int numberOfMeasurements(LinkedList<BodyWeightReading> bodyWeightReadings);

    int getLastMeasurement(LinkedList<BodyWeightReading> bodyWeightReadings);
}
