package com.mcma.blood_pressure_diary.impl.bodyweight;

import com.mcma.blood_pressure_diary.models.bodyweight.BodyWeightReading;

import java.util.LinkedList;
import java.util.List;

public class BodyWeightCalcImpl implements IBodyWeightCalc {

    @Override
    public BodyWeightReading calcAverage(LinkedList<BodyWeightReading> bodyWeightReadings) {
        int bodyWeights = 0;

        BodyWeightReading avrBodyWeight = new BodyWeightReading();


        for (BodyWeightReading bodyWeightReading : bodyWeightReadings) {
            bodyWeights += bodyWeightReading.getBodyWeight();
        }
        if(bodyWeightReadings.size()!=0) {
            int avg = bodyWeights / bodyWeightReadings.size();
            avrBodyWeight.setBodyWeight(avg);
            return avrBodyWeight;
        }

        return new BodyWeightReading(0);
    }

    @Override
    public int numberOfMeasurements(LinkedList<BodyWeightReading> bodyWeightReadings) {
        int numberOfMeasurements = bodyWeightReadings.size();
        return numberOfMeasurements;
    }

    @Override
    public int getLastMeasurement(LinkedList<BodyWeightReading> bodyWeightReadings) {
        int valueOfLastMeasurement = bodyWeightReadings.getLast().getBodyWeight();
        return valueOfLastMeasurement;
    }

}