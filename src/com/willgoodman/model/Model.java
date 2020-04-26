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

    public final static Set<String> REGULAR_ALGORITHMS = new HashSet<>(Arrays.asList(ROT13.NAME));
    public final static Set<String> FREQUENCY_ALGORITHMS = new HashSet<>(Arrays.asList(FrequencyAnalysis.NAME, NearestFrequency.NAME));


    /**
     * Decrypts text using a regular algorithm
     *
     * @param encrypted The encrypted text
     * @param algorithm The algorithm to use
     * @return The plain text
     */
    public String decrypt(String encrypted, String algorithm) {
        switch (algorithm) {
            case ROT13.NAME:
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
    public void decrypt(String srcFilename, String outFilename, String algorithm) throws IOException {
        File srcFile = new File(srcFilename);

        if (srcFile.isFile()) {
            switch (algorithm) {
                case ROT13.NAME:
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
    public String decrypt(String encrypted, String algorithm, HashMap<Character, Float> characterFrequencies) {
        switch (algorithm) {
            case FrequencyAnalysis.NAME:
                ArrayList<Character> uniqueCharacters = new ArrayList<>(characterFrequencies.keySet());
                uniqueCharacters.sort(Comparator.comparing(characterFrequencies::get));
                Collections.reverse(uniqueCharacters);
                return new FrequencyAnalysis(new LinkedHashSet<>(uniqueCharacters)).decrypt(encrypted);
            case NearestFrequency.NAME:
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
    public void decrypt(String srcFilename, String outFilename, String algorithm, HashMap<Character, Float> characterFrequencies) throws IOException {
        File srcFile = new File(srcFilename);

        if (srcFile.isFile()) {
            switch (algorithm) {
                case FrequencyAnalysis.NAME:
                    ArrayList<Character> uniqueCharacters = new ArrayList<>(characterFrequencies.keySet());
                    uniqueCharacters.sort(Comparator.comparing(characterFrequencies::get));
                    Collections.reverse(uniqueCharacters);
                    new FrequencyAnalysis(new LinkedHashSet<>(uniqueCharacters)).decrypt(srcFilename, outFilename);
                case NearestFrequency.NAME:
                    new NearestFrequency(characterFrequencies).decrypt(srcFilename, outFilename);
            }
        }
    }

}
