package org.example;

/**
 * This class contains the format in which each row of the csv has to parsed and stored
 */
public class Record {
    int period;
    String region;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Record(int period, String birthAndDeath, String region, int count) {
        this.period = period;
        this.region = region;
        this.birthAndDeath = birthAndDeath;
        this.count = count;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBirthAndDeath() {
        return birthAndDeath;
    }

    public void setBirthAndDeath(String birthAndDeath) {
        this.birthAndDeath = birthAndDeath;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    String birthAndDeath;
    int count;

}
