package com.mcma.blood_pressure_diary;

import java.util.List;

/**
 * This interface shall be used to calculate the average body weight
 */
public interface IBodyWeightCalc {

    /**
     * This method takes a number of body weight readings and
     * calculates the average weight
     * @param bodyWeightReadings
     * @return average of weight readings (the timeStamp can be empty)
     */
    BodyWeightReading calcAverage(List<BodyWeightReading> bodyWeightReadings);
}
