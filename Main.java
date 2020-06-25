package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\enma_\\OneDrive\\Desktop\\syllables.txt");

        ReadibilityText readibilityText = new ReadibilityText(file);

        readibilityText.printInformation();
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        String kindOfTest = scanner.nextLine();
        kindOfTest = kindOfTest.toUpperCase();

        System.out.println();
        evaluateKindOfTest(kindOfTest, readibilityText);
    }

    public static void evaluateKindOfTest(String kindOfTest, ReadibilityText readibilityText) {
        double score = 0;

        if("ARI".equals(kindOfTest)) {
            readibilityText.calculateScore(ReadibilityKindOfTest.ARI);
            score = readibilityText.getScore();
            printInfo(ReadibilityKindOfTest.ARI, readibilityText, score);
        } else if("FK".equals(kindOfTest)) {
            readibilityText.calculateScore(ReadibilityKindOfTest.FK);
            score = readibilityText.getScore();
            printInfo(ReadibilityKindOfTest.FK, readibilityText, score);
        } else if ("SMOG".equals(kindOfTest)) {
            readibilityText.calculateScore(ReadibilityKindOfTest.SMOG);
            score = readibilityText.getScore();
            printInfo(ReadibilityKindOfTest.SMOG, readibilityText, score);
        } else if("CL".equals(kindOfTest)) {
            readibilityText.calculateScore(ReadibilityKindOfTest.CL);
            score = readibilityText.getScore();
            printInfo(ReadibilityKindOfTest.CL, readibilityText, score);
        } else if("ALL".equals(kindOfTest)) {
            readibilityText.calculateScore(ReadibilityKindOfTest.ARI);
            double scoreARI = readibilityText.getScore();
            readibilityText.calculateScore(ReadibilityKindOfTest.FK);
            double scoreFK = readibilityText.getScore();
            readibilityText.calculateScore(ReadibilityKindOfTest.SMOG);
            double scoreSMOG = readibilityText.getScore();
            readibilityText.calculateScore(ReadibilityKindOfTest.CL);
            double scoreCL = readibilityText.getScore();
            printInfo(readibilityText, scoreARI, scoreFK, scoreSMOG, scoreCL);
        }
    }

    public static void printInfo(ReadibilityKindOfTest readibilityKindOfTest, ReadibilityText readibilityText, double score) {
        double age = (double) readibilityText.age(score);
        System.out.printf("%s: %.2f (about %d year olds).\n", readibilityKindOfTest.getDescription(), score, readibilityText.age(score));
        System.out.println();
        System.out.printf("This text should be understood in average by %.2f year olds.\n", age);
    }

    public static void printInfo(ReadibilityText readibilityText, double scoreARI, double scoreFK, double scoreSMOG, double scoreCL) {
        int ageARI =  readibilityText.age(scoreARI);
        int ageFK = readibilityText.age(scoreFK);
        int ageSMOG = readibilityText.age(scoreSMOG);
        int ageCL = readibilityText.age(scoreCL);
        System.out.printf("%s: %.2f (about %d year olds).\n", ReadibilityKindOfTest.ARI.getDescription(), scoreARI, ageARI);
        System.out.printf("%s: %.2f (about %d year olds).\n", ReadibilityKindOfTest.FK.getDescription(), scoreFK, ageFK);
        System.out.printf("%s: %.2f (about %d year olds).\n", ReadibilityKindOfTest.SMOG.getDescription(), scoreSMOG, ageSMOG);
        System.out.printf("%s: %.2f (about %d year olds).\n", ReadibilityKindOfTest.CL.getDescription(), scoreCL, ageCL);
        System.out.println();
        System.out.printf("This text should be understood in average by %.2f year olds.\n", avgAge(ageARI, ageFK, ageSMOG, ageCL));
    }

    public static double avgAge(int ageARI, int ageFK, int ageSMOG, int ageCL) {
        return (ageARI + ageFK + ageSMOG + ageCL) / 4.0;
    }

}
