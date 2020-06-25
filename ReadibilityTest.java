package com.company;

public class ReadibilityTest {
    private int words;
    private int sentences;
    private int characters;
    private int syllables;
    private int polysyllables;
    private ReadibilityKindOfTest kindOfTest;

    public ReadibilityTest(ReadibilityKindOfTest kindOfTest, int words, int sentences, int characters, int syllables, int polysyllables) {
        this.words = words;
        this.sentences = sentences;
        this.characters = characters;
        this.syllables = syllables;
        this.polysyllables = polysyllables;
        this.kindOfTest = kindOfTest;
    }

    public double calculateScore() {
        if (kindOfTest.equals(ReadibilityKindOfTest.ARI)) {
            return calculateARI();
        } else if(kindOfTest.equals(ReadibilityKindOfTest.FK)) {
            return calculateFK();
        } else if (kindOfTest.equals(ReadibilityKindOfTest.SMOG)) {
            return calculateSMOG();
        } else if (kindOfTest.equals(ReadibilityKindOfTest.CL)) {
            return calculateCL();
        } else{
            return -1;
        }
    }

    private double calculateARI() {
        return 4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43;
    }

    private double calculateFK() {
        return 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59;
    }

    private double calculateSMOG() {
        return 1.043 * Math.sqrt(polysyllables * 30.0 / sentences) + 3.1291;
    }

    private double calculateCL() {
        double L = 100 * (double) characters / words; //average number of characters per 100 words
        double S = 100 * (double) sentences / words; //average number of sentences per 100 words

        return 0.0588 * L - 0.296 * S - 15.8;
    }

}
