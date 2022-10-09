package com.mcma.blood_pressure_diary;

import java.util.ArrayList;
import java.util.List;

public class BodyWeightCalcImpl implements IBodyWeightCalc{

    @Override
    public BodyWeightReading calcAverage(List<BodyWeightReading> bodyWeightReadings) {
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
}