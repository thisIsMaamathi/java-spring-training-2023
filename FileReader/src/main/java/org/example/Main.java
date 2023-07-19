package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        final String csvFile = "/Users/maamathikrishnan/Desktop/FileReader/BirthAndDeath.csv";
        CsvReader.read(csvFile);
        while(true) {
            System.out.println("Choose from the following  \n1.Display All Regions \n2.Display all Years \n3.Get Overall Birth and Death rate for all years \n4.Display yearwise Birth Death count \n5.Display regionwise Birth Death count \n6.Year with Highest Birth and deathraten\n7.Highest Birth and deathrate for each region along with year \n8.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    CsvReader.displayAllRegions();
                }
                case 2 -> {
                    CsvReader.displayAllYears();
                }
                case 3 -> {
                    CsvReader.overallBirthAndDeathCount();

                }
                case 4 -> {
                    CsvReader.displayYearWiseBirthDeath();
                }
                case 5 -> {
                    CsvReader.displayRegionWiseBirthDeath();
                }
                case 6 -> {
                    CsvReader.highestBirthDeathRateYear();
                }
                case 7 -> {
                    CsvReader.maxCountRegionwise();
                }

                case 8 -> {System.exit(0);
                }

                default -> System.out.println("Enter valid choice");
            }
        }
    }
}