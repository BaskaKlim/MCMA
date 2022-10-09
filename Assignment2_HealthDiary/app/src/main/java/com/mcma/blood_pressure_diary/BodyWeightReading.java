package com.mcma.blood_pressure_diary;

/**
 * This class shall be used to contain a reading of
 * body weight readings, as well as the corresponding timestamp
 * when the measurement was taken
 *
 */
public class BodyWeightReading {

    private int bodyWeight;
    private long timeStamp;

    public void setBodyWeight(int sys) {
        this.bodyWeight = sys;
    }
    public int getBodyWeight() {
        return bodyWeight;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public BodyWeightReading(int bodyWeight){
        this.bodyWeight = bodyWeight;
        this.timeStamp = System.currentTimeMillis();
    }

    public BodyWeightReading(){}
}
