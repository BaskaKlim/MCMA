package com.mcma.blood_pressure_diary;

/**
 * This class shall be used to contain a reading of
 * systolic and diastolic blood pressure readings, as
 * well as the corresponding timestamp when the measurement
 * was taken
 *
 * Please extend this class with necessary functions (e.g.
 * constructor and/or getter and setter)
 */
public class BloodPressureReading {

    private int sys;
    private int dia;
    private long timeStamp;

    public void setSys(int sys) {
        this.sys = sys;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getSys() {
        return sys;
    }

    public int getDia() {
        return dia;
    }

    public long getTimeStamp() {
        return timeStamp;
    }


    public BloodPressureReading(int sys, int dia){
        this.sys = sys;
        this.dia = dia;
        this.timeStamp = System.currentTimeMillis();
    }

    public BloodPressureReading(){}
}
