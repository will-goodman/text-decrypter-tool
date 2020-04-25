package com.willgoodman.model.algorithms.decryption.frequency;

import com.willgoodman.model.algorithms.decryption.DecryptionAlgorithm;

import java.io.*;
import java.util.*;

/**
 * Decrypts text using Nearest Frequency
 *
 * @author Will Goodman
 */
public class NearestFrequency implements DecryptionAlgorithm {

    private HashMap<Character, Float> characterFrequencies;


    /**
     * Constructor
     *
     * @param characterFrequencies Characters and their frequencies in normal text
     */
    public NearestFrequency(HashMap<Character, Float> characterFrequencies) {
        this.characterFrequencies = characterFrequencies;
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
        HashMap<Character, Integer> srcCharacterOccurrences = calculateElementOccurrences(encryptedCharacters, new HashMap<>());

        HashMap<Character, Float> srcCharacterFrequencies = new HashMap<>();
        for (Character character : srcCharacterOccurrences.keySet()) {
            srcCharacterFrequencies.put(character, (float) srcCharacterOccurrences.get(character) / (float) encrypted.length());
        }

        HashMap<Character, Character> cipherToPlain = generateCharacterDecrypter(srcCharacterFrequencies);

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

                HashMap<Character, Integer> srcCharacterOccurrences = new HashMap<>();

                String line;
                int totalCharacters = 0;
                while ((line = in.readLine()) != null) {
                    srcCharacterOccurrences = calculateElementOccurrences(toCharacterArray(line.toLowerCase()), srcCharacterOccurrences);
                    totalCharacters += line.length();
                }
                in.close();

                HashMap<Character, Float> srcCharacterFrequencies = new HashMap<>();
                for (Character character : srcCharacterOccurrences.keySet()) {
                    srcCharacterFrequencies.put(character, (float) srcCharacterOccurrences.get(character) / (float) totalCharacters);
                }

                HashMap<Character, Character> cipherToPlain = generateCharacterDecrypter(srcCharacterFrequencies);

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
     * @param srcCharacterFrequencies Characters and their frequencies in the cipher text
     * @return HashMap of enciphered characters and their plain text counterparts
     */
    private HashMap<Character, Character> generateCharacterDecrypter(HashMap<Character, Float> srcCharacterFrequencies) {
        HashMap<Character, Character> cipherToPlain = new HashMap<>();

        HashMap<Character, Float> remainingCharacters = new HashMap<>(this.characterFrequencies);

        ArrayList<Character> mostFrequentRemainingCharacters = new ArrayList<>(this.characterFrequencies.keySet());
        mostFrequentRemainingCharacters.sort(Comparator.comparing(this.characterFrequencies::get));
        Collections.reverse(mostFrequentRemainingCharacters);

        ArrayList<Character> mostFrequentSrcCharacters = new ArrayList<>(srcCharacterFrequencies.keySet());
        mostFrequentSrcCharacters.sort(Comparator.comparing(srcCharacterFrequencies::get));
        Collections.reverse(mostFrequentSrcCharacters);

        for (Character encryptedCharacter : mostFrequentSrcCharacters) {
            if (this.characterFrequencies.containsKey(encryptedCharacter)) {

                float smallestFrequencyDistance = 100.0f;
                Character nearestCharacter = encryptedCharacter;
                for (Character plainTextCharacter : mostFrequentRemainingCharacters) {
                    float frequencyDistance = Math.abs(srcCharacterFrequencies.get(encryptedCharacter) - remainingCharacters.get(plainTextCharacter));
                    if (frequencyDistance < smallestFrequencyDistance) {
                        smallestFrequencyDistance = frequencyDistance;
                        nearestCharacter = plainTextCharacter;
                    }
                }

                cipherToPlain.put(encryptedCharacter, nearestCharacter);
                mostFrequentRemainingCharacters.remove(nearestCharacter);
            } else {
                cipherToPlain.put(encryptedCharacter, encryptedCharacter);
            }
        }

        return cipherToPlain;
    }


    /**
     * Calculates how many times each unique element in an array occurs and updates a HashMap with the results
     *
     * @param array The array
     * @param occurrences The HashMap to be updated
     * @param <T> A non-primitive type
     * @return The updated HashMap of elements and their frequency
     */
    private static  <T> HashMap<T, Integer> calculateElementOccurrences(T[] array, HashMap<T, Integer> occurrences) {
        for (T element : array) {
            if (occurrences.containsKey(element)) {
                occurrences.put(element, occurrences.get(element) + 1);
            } else {
                occurrences.put(element, 1);
            }
        }

        return occurrences;
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
