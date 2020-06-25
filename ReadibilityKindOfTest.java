package com.company;

public enum ReadibilityKindOfTest {
    ARI("ARI", "Automated Readability Index"),
    FK("FK", "Flesch–Kincaid readability tests"),
    SMOG("SMOG", "Simple Measure of Gobbledygook"),
    CL("CL", "Coleman–Liau index");

    private String name;
    private String description;

    ReadibilityKindOfTest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
