package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadibilityText {
    private int words;
    private int sentences;
    private int characters;
    private int syllables;
    private int polysyllables;
    private File file;
    private String text;
    private double score;

    public ReadibilityText(File file) {
        this.file = file;
        this.text = "";
        checkText();
    }

    //Check and calculate all the characteristcs of the text
    private void checkText() {
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                text += line;

                String[] splitByWords = line.split("[\\s\\.\\?!]+");
                this.words += splitByWords.length;
                String[] splitBySenteces = line.split("[\\.\\?!]");
                this.sentences += splitBySenteces.length;
                this.characters += cantChar(line);
                int[] syllablesAndPolysyllables = countTextSyllabes(splitByWords);
                this.syllables += syllablesAndPolysyllables[0];
                this.polysyllables += syllablesAndPolysyllables[1];
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found.");
        }
    }

    //Calculate the quantity of characters of a text
    private int cantChar (String line) {
        int cantChar = 0;

        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) != ' ' && line.charAt(i) != '\n' && line.charAt(i) != '\t') {
                ++cantChar;
            }
        }

        return cantChar;
    }

    //Count the quantity of syllables which a text has
    private int[] countTextSyllabes(String[] words) {
        int countSyllabes = 0;
        int countPolysyllables = 0;

        for (int i = 0; i < words.length; i++) {
            String newWord = eliminateQuotetionMark(words[i]);

            int countWordSyllables = countWordSyllables(newWord);

            countSyllabes += countWordSyllables;

            if (countWordSyllables > 2) {
                ++countPolysyllables;
            }
        }

        return new int[]{countSyllabes, countPolysyllables};
    }

    //Count the quantity of syllables of a word
    private int countWordSyllables(String word) {
        int countSyllabes = 0;
        word = word.toLowerCase();

        for (int i = 0; i < word.length() - 1; i++) {
            String letter = word.substring(i, i + 1);

            if (i == 0 && letter.matches("[aeiouy]")) {
                ++countSyllabes;
            } else if (i > 0 && i < word.length() - 1 && letter.matches("[aeiouy]")) {
                String previousLetter = word.substring(i - 1, i);

                if (!previousLetter.matches("[aeiouy]")) {
                    ++countSyllabes;
                }
            }
        }

        if (word.length() > 1) {
            String previousLetter = word.substring(word.length() - 2, word.length() - 1);

            if (word.substring(word.length() - 1, word.length()).matches("[aiouy]") && !previousLetter.matches("[aeiouy]")) {
                ++countSyllabes;
            }
        }

        if (countSyllabes == 0){
            return 1;
        } else {
            return countSyllabes;
        }
    }

    //Eliminate quotietion marks of a word(if it has)
    private String eliminateQuotetionMark(String word) {
        return word.replaceAll("[\\.\\?!,;: ]+", "");
    }

    public void calculateScore(ReadibilityKindOfTest readibilityKindOfTest) {
        ReadibilityTest readibilityTest = new ReadibilityTest(readibilityKindOfTest, words, sentences, characters, syllables, polysyllables);
        this.score = readibilityTest.calculateScore();
    }

    public void printInformation() {
        System.out.println("The text is: \n" + this.text + "\n");
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
    }

    public int age(double score) {
        double roundScore = Math.round(score);
        int age = -1;

        if (roundScore == 1) {
            age = 6;
        } else if (roundScore == 2) {
            age = 7;
        } else if (roundScore == 3) {
            age = 9;
        } else if (roundScore == 4) {
            age = 10;
        } else if (roundScore == 5) {
            age = 11;
        } else if (roundScore == 6) {
            age = 12;
        } else if (roundScore == 7) {
            age = 13;
        } else if (roundScore == 8) {
            age = 14;
        } else if (roundScore == 9) {
            age = 15;
        } else if (roundScore == 10) {
            age = 16;
        } else if (roundScore == 11) {
            age = 17;
        } else if (roundScore == 12) {
            age = 18;
        } else if(roundScore == 13) {
            age = 24;
        } else if(roundScore == 14) {
            age = 24;
        }

        return age;
    }


    public int getWords() {
        return words;
    }

    public int getSentences() {
        return sentences;
    }

    public int getCharacters() {
        return characters;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolysyllables() {
        return polysyllables;
    }

    public String getText() {
        return text;
    }

    public double getScore() {
        return score;
    }

}
