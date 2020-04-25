package com.willgoodman.model;

import com.willgoodman.model.algorithms.decryption.caesar.ROT13;
import com.willgoodman.model.algorithms.decryption.frequency.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Model for the decryption tool
 *
 * @author Will Goodman
 */
public class Model {

    /**
     * Enum for character frequency based algorithms
     */
    private enum FrequencyAlgorithm {
        FREQUENCY_ANALYSIS, NEAREST_FREQUENCY
    }


    /**
     * Enum for algorithms which do not require additional information other than the source text
     */
    private enum RegularAlgorithm {
        ROT13
    }


    /**
     * Gets the available character frequency based algorithms
     *
     * @return The frequency algorithms
     */
    public FrequencyAlgorithm[] getFrequencyAlgorithms() {
        return FrequencyAlgorithm.values();
    }


    /**
     * Gets the available algorithms which only require the source text as input
     *
     * @return The regular algorithms
     */
    public RegularAlgorithm[] getRegularAlgorithms() { return RegularAlgorithm.values(); }


    /**
     * Decrypts text using a regular algorithm
     *
     * @param encrypted The encrypted text
     * @param algorithm The algorithm to use
     * @return The plain text
     */
    public String decrypt(String encrypted, RegularAlgorithm algorithm) {
        switch (algorithm) {
            case ROT13:
                return new ROT13().decrypt(encrypted);
            default:
                return encrypted;
        }
    }

    /**
     * Decrypts the text of a given file, and writes the result to another given file, using a regular algorithm
     *
     * @param srcFilename The name of the file to decrypt
     * @param outFilename The name of the file to write the result to
     * @param algorithm The algorithm to use
     * @throws IOException Throws if there is an error reading from the source file
     */
    public void decrypt(String srcFilename, String outFilename, RegularAlgorithm algorithm) throws IOException {
        File srcFile = new File(srcFilename);

        if (srcFile.isFile()) {
            switch (algorithm) {
                case ROT13:
                    new ROT13().decrypt(srcFilename, outFilename);
            }
        }
    }


    /**
     * Decrypts text using an algorithm based on character frequencies
     *
     * @param encrypted The text to decrypt
     * @param algorithm The algorithm to use
     * @param characterFrequencies The characters and their frequencies in regular text
     * @return The plain text
     */
    public String decrypt(String encrypted, FrequencyAlgorithm algorithm, HashMap<Character, Float> characterFrequencies) {
        switch (algorithm) {
            case FREQUENCY_ANALYSIS:
                ArrayList<Character> uniqueCharacters = new ArrayList<>(characterFrequencies.keySet());
                uniqueCharacters.sort(Comparator.comparing(characterFrequencies::get));
                Collections.reverse(uniqueCharacters);
                return new FrequencyAnalysis(new LinkedHashSet<>(uniqueCharacters)).decrypt(encrypted);
            case NEAREST_FREQUENCY:
                return new NearestFrequency(characterFrequencies).decrypt(encrypted);
            default:
                return encrypted;
        }
    }


    /**
     * Decrypts the text of a given file, and writes the result to another given file, using a frequency algorithm
     *
     * @param srcFilename The name of the file to decrypt
     * @param outFilename The name of the file to write the result to
     * @param algorithm The algorithm to use
     * @param characterFrequencies The characters and their frequencies in regular text
     * @throws IOException Throws if there is an error reading from the source file
     */
    public void decrypt(String srcFilename, String outFilename, FrequencyAlgorithm algorithm, HashMap<Character, Float> characterFrequencies) throws IOException {
        File srcFile = new File(srcFilename);

        if (srcFile.isFile()) {
            switch (algorithm) {
                case FREQUENCY_ANALYSIS:
                    ArrayList<Character> uniqueCharacters = new ArrayList<>(characterFrequencies.keySet());
                    uniqueCharacters.sort(Comparator.comparing(characterFrequencies::get));
                    Collections.reverse(uniqueCharacters);
                    new FrequencyAnalysis(new LinkedHashSet<>(uniqueCharacters)).decrypt(srcFilename, outFilename);
                case NEAREST_FREQUENCY:
                    new NearestFrequency(characterFrequencies).decrypt(srcFilename, outFilename);
            }
        }
    }

}
