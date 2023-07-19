package org.example;
/**
 * This class contains the format of yearwise birth death details arrayl list"s object
 */
public class YearwiseBirthDeath {
    int maxBirthyear;
    int maxDeathYear;
    int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMaxBirthyear() {
        return maxBirthyear;
    }

    public void setMaxBirthyear(int maxBirthyear) {
        this.maxBirthyear = maxBirthyear;
    }

    public int getMaxDeathYear() {
        return maxDeathYear;
    }

    public void setMaxDeathYear(int maxDeathYear) {
        this.maxDeathYear = maxDeathYear;
    }

    int birthCount;



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

    int deathCount;
}
