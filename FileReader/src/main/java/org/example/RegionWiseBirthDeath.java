package org.example;

/**
 * This class contains the format of regionwise birth death details arrayl list"s object
 */
public class RegionWiseBirthDeath {
    String region;
    int birthCount;
    int deathCount;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getBirthCount() {
        return birthCount;
    }

    public void setBirthCount(int birthCount) {
        this.birthCount = birthCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    int year;
}
