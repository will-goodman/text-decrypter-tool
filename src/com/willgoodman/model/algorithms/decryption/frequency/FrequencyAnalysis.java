package com.willgoodman.model.algorithms.decryption.frequency;

import com.willgoodman.model.algorithms.decryption.DecryptionAlgorithm;

import java.io.*;
import java.util.*;

/**
 * Decrypts text using Frequency Analysis
 *
 * @author Will Goodman
 */
public class FrequencyAnalysis implements DecryptionAlgorithm {

    public final static String NAME = "Frequency Analysis";

    private Set<Character> mostFrequentCharacters;


    /**
     * Constructor
     *
     * @param mostFrequentCharacters Characters in descending frequency order
     */
    public FrequencyAnalysis(Set<Character> mostFrequentCharacters) {
        this.mostFrequentCharacters = mostFrequentCharacters;
    }


    /**
     * Decrypts a given String
     *
     * @param encrypted The string to be decrypted
     * @return The plain text
     */
    @Override
    public String decrypt(String encrypted) {
        Character[] encryptedCharacters = toCharacterArray(encrypted.toLowerCase());

        Set<Character> sourceFrequentCharacters = calculateMostFrequentElements(encryptedCharacters);

        HashMap<Character, Character> cipherToPlain = this.generateCharacterDecrypter(sourceFrequentCharacters);

        StringBuilder plainText = new StringBuilder();
        for (Character encryptedCharacter : encryptedCharacters) {
            plainText.append(cipherToPlain.get(encryptedCharacter));
        }

        return plainText.toString();
    }


    /**
     * Decrypts the text of a given file, and writes the result to another given file
     *
     * @param srcFilename The name of the file to decrypt
     * @param outFilename The name of the file to write the result to
     * @throws IOException Throws if there is an error reading from the source file
     */
    @Override
    public void decrypt(String srcFilename, String outFilename) throws IOException {
        File srcFile = new File(srcFilename);

        if (srcFile.isFile()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(srcFile));

                HashMap<Character, Integer> characterOccurrences = new HashMap<>();

                String line;
                while ((line = in.readLine()) != null) {
                    for (Character character : toCharacterArray(line.toLowerCase())) {
                        if (characterOccurrences.containsKey(character)) {
                            characterOccurrences.put(character, characterOccurrences.get(character) + 1);
                        } else {
                            characterOccurrences.put(character, 1);
                        }
                    }
                }
                in.close();

                ArrayList<Character> uniqueCharacters = new ArrayList<>(characterOccurrences.keySet());
                uniqueCharacters.sort(Comparator.comparing(characterOccurrences::get));
                Collections.reverse(uniqueCharacters);

                HashMap<Character, Character> cipherToPlain = this.generateCharacterDecrypter(new LinkedHashSet<>(uniqueCharacters));

                File outFile = new File(outFilename);
                if (!outFile.isFile()) {
                    outFile.createNewFile();
                }

                in = new BufferedReader(new FileReader(srcFile));
                PrintWriter out = new PrintWriter(new FileWriter(outFile));

                StringBuilder plainText;
                while ((line = in.readLine()) != null) {
                    plainText = new StringBuilder();
                    for (Character encryptedCharacter : toCharacterArray(line.toLowerCase())) {
                        plainText.append(cipherToPlain.get(encryptedCharacter));
                    }

                    out.println(plainText);
                }
                in.close();
                out.close();

            } catch (FileNotFoundException ex) {
                // Should not be thrown
                System.out.println("ERROR: Source file does not exist");
            }
        }

    }


    /**
     * Generates a HashMap of enciphered characters and their plain text counterparts
     *
     * @param sourceFrequentCharacters Characters in the encrypted text from most to least frequent
     * @return HashMap of enciphered characters and their plain text counterparts
     */
    private HashMap<Character, Character> generateCharacterDecrypter(Set<Character> sourceFrequentCharacters) {
        HashMap<Character, Character> cipherToPlain = new HashMap<>();
        Character[] mostFrequentCharactersArray = this.mostFrequentCharacters.toArray(new Character[]{});

        int nextMostFrequentCharacterIndex = 0;
        for (Character encryptedCharacter : sourceFrequentCharacters) {
            if (this.mostFrequentCharacters.contains(encryptedCharacter) && nextMostFrequentCharacterIndex < mostFrequentCharactersArray.length) {
                cipherToPlain.put(encryptedCharacter, mostFrequentCharactersArray[nextMostFrequentCharacterIndex]);
                nextMostFrequentCharacterIndex++;
            } else {
                cipherToPlain.put(encryptedCharacter, encryptedCharacter);
            }
        }

        return cipherToPlain;
    }


    /**
     * Calculates how many times each value is present in an array and orders them from most frequent to least frequent
     *
     * @param array The array of values
     * @param <T>   A comparable type so the array can be sorted
     * @return A set of unique values in the array ordered from most frequent to least frequent
     */
    private static <T extends Comparable> Set<T> calculateMostFrequentElements(T[] array) {
        HashMap<T, Integer> elementOccurrences = new HashMap<>();

        for (T element : array) {
            if (elementOccurrences.containsKey(element)) {
                elementOccurrences.put(element, elementOccurrences.get(element) + 1);
            } else {
                elementOccurrences.put(element, 1);
            }
        }

        ArrayList<T> uniqueElements = new ArrayList<>(elementOccurrences.keySet());
        uniqueElements.sort(Comparator.comparing(elementOccurrences::get));
        Collections.reverse(uniqueElements);

        return new LinkedHashSet<>(uniqueElements);
    }


    /**
     * Converts a String into a Character array
     *
     * @param string The original String
     * @return The Characters in the String
     */
    private static Character[] toCharacterArray(String string) {
        Character[] characters = new Character[string.length()];

        for (int index = 0; index < string.length(); index++) {
            characters[index] = string.charAt(index);
        }

        return characters;
    }

}
