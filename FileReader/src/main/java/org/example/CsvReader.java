package org.example;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import static org.example.Constants.BIRTHS;
import static org.example.Constants.DEATHS;


/**
 * This class deals with functionalities og collecting and filtering data from a csv file
 */
public class CsvReader {

    public static final ArrayList<Record> dataset =new ArrayList<>();
    public static HashSet<String> regionsList=new HashSet<>();

    public static HashSet<Integer> yearList=new HashSet<>();
    public static ArrayList<YearwiseBirthDeath> yearWiseCountList=new ArrayList<>();

    public static ArrayList<RegionWiseBirthDeath> regionWiseCountList=new ArrayList<>();


    public static final String delimiter = ",";

    /**
     * This function enables reading data from csv file and storing it as a ArrayList
     * @param csvFile
     */
    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            br.readLine();
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);

                    String nyear=tempArr[0].replaceAll("\"", "");
                    int year= Integer.parseInt(nyear);

                    String ncount=tempArr[3].replaceAll("\"", "");
                    int count= Integer.parseInt(ncount);

                    String birthAndDeath=tempArr[1].replaceAll("\"", "");

                 Record record=new Record(year,birthAndDeath,tempArr[2],count);
                 dataset.add(record);

            }
            extractAllRegions();
            extractAllYears();
            System.out.println();
            br.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * This function extracts all unique regions from csv data
     */
    public static void extractAllRegions() {
        for(Record record:dataset){
        regionsList.add(record.getRegion());
    }
    }

    /**
     * This function extracts all unique years from csv
     */
    public static void extractAllYears(){
        for(Record record:dataset){
            yearList.add(record.getPeriod());
        }
    }

    /**
     * This function displays all data in regionslist
     */
    public static void displayAllRegions(){
        for (String str:regionsList){
            System.out.println(str);
        }

    }

    /**
     * This function displays all the years from yearlist
     */
    public static void displayAllYears(){
        for (int year:yearList){
            System.out.println(year);
        }

    }

    /**
     * This function displays the overall birth and death count
     */

    public static void overallBirthAndDeathCount(){
        int birthCount=0;
        int deathCount=0;
        for(Record record:dataset){
            if(record.getBirthAndDeath().equals(BIRTHS)) birthCount+=record.getCount();
            else deathCount+= record.getCount();
            //System.out.println(record.getBirthAndDeath());
        }
        System.out.println("BirthCount: " +birthCount+ "  DeathCount: "+deathCount);
    }

    /**
     * This function calculates the yearwise birthdeath count and returns the maxbirth count and max death count in form of array
     * @return
     */
    public static int[] yearWiseBirthDeath(){
        int birthCount=0;
        int deathCount=0;
        int maxBirthcount=0;
        int maxDeathCount=0;
        int maxCount[]=new int[4];
        for(int year:yearList){
        for(Record record:dataset){
            if(year==record.getPeriod()){
                if(record.getBirthAndDeath().equals(BIRTHS)) {
                    birthCount+=record.getCount();
                    if(birthCount>maxBirthcount){ maxBirthcount=birthCount; }}
                else {
                    deathCount+= record.getCount();
                    if(deathCount>maxDeathCount) { maxDeathCount=deathCount;}
                }
            }

        }
        YearwiseBirthDeath yearwiseBirthDeath=new YearwiseBirthDeath();
        yearwiseBirthDeath.setBirthCount(birthCount);
        yearwiseBirthDeath.setDeathCount(deathCount);
        yearwiseBirthDeath.setYear(year);
        yearWiseCountList.add(yearwiseBirthDeath);
            }
        maxCount[0]=birthCount;
        maxCount[2]=deathCount;

        return maxCount;
        }

    /**
     * This function displays yearwiseBirthDeath details
     */

    public static void displayYearWiseBirthDeath(){
        yearWiseBirthDeath();
        for(YearwiseBirthDeath y:yearWiseCountList){
            System.out.println("Year :"+y.getYear()+" BirthCount: " +y.getBirthCount()+ "  DeathCount: "+y.getDeathCount());
        }
        }
    /**
     * This function calculates the regionwise birthdeath count and returns the maxbirth count and max death count in form of array
     * @return
     */

    public static int[] regionWiseBirthDeath(){
        int birthCount=0;
        int deathCount=0;
        int maxCount[]=new int[2];
        int maxBirthcount=0;
        int maxDeathCount=0;
        int year = 0;
        for(String region:regionsList){
            for(Record record:dataset){
                if(region.equals(record.getRegion())){
                    year=record.getPeriod();
                    if(record.getBirthAndDeath().equals(BIRTHS)){
                        birthCount+=record.getCount();
                        if(birthCount>maxBirthcount) maxBirthcount=birthCount;
                    }
                    else {deathCount+= record.getCount();   if(deathCount>maxDeathCount)  maxDeathCount=deathCount;}
                }

            }
            RegionWiseBirthDeath regionwiseBirthDeath=new RegionWiseBirthDeath();
            regionwiseBirthDeath.setRegion(region);
            regionwiseBirthDeath.setBirthCount(birthCount);
            regionwiseBirthDeath.setDeathCount(deathCount);
            regionwiseBirthDeath.setYear(year);
            regionWiseCountList.add(regionwiseBirthDeath);

        }
        maxCount[0]=birthCount;
        maxCount[1]=deathCount;
        return maxCount;
    }


    /**
     * This function displays the maximum birth and death count for each region
     */
    public static void maxCountRegionwise(){
        for(String str:regionsList){

            ArrayList<Integer> birthCounts=new ArrayList<>();
            ArrayList<Integer> deathCounts=new ArrayList<>();
            for(Record record:dataset){
                if(record.getRegion().equals(str)){
                    if(record.getBirthAndDeath().equalsIgnoreCase(BIRTHS))
                    birthCounts.add(record.getCount());
                    else deathCounts.add(record.getCount());

                }
            }
            System.out.print("For Region "+str);
            System.out.println("---------->MaxBirthCounts : "+Collections.max(birthCounts)+" MaxDeathCounts  : "+Collections.max(deathCounts));
        }


    }

    /**
     * This function displays regionWiseBirthDeathDetails
     */
    public static void displayRegionWiseBirthDeath(){
        regionWiseBirthDeath();
        for(RegionWiseBirthDeath r:regionWiseCountList){
            System.out.println("Region :"+r.getRegion()+" BirthCount: " +r.getBirthCount()+ "  DeathCount:  "+r.getDeathCount());
        }
    }
    /**
     * This function returns the year in which maximum birth and death occured
     */
    public static void highestBirthDeathRateYear() {
        int maxBirthrate = 0;
        int maxDeathRate = 0;
        int birthYear = 0;
        int deathYear = 0;
        for (Record record : dataset) {
            if (record.getBirthAndDeath().equalsIgnoreCase(BIRTHS) && record.getCount() > maxBirthrate) {
                maxBirthrate = record.getCount();
                birthYear = record.getPeriod();
            } else if (record.getBirthAndDeath().equalsIgnoreCase(DEATHS) && record.getCount() > maxDeathRate) {
                maxDeathRate = record.getCount();
                deathYear = record.getPeriod();
            }

        }
        System.out.println("BirthRate   :  " + maxBirthrate + " " + birthYear + "    DeathRate : " + maxDeathRate + " " + deathYear);
    }
}






