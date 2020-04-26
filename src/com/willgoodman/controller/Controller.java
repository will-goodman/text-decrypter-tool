package com.willgoodman.controller;

import com.willgoodman.model.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Controller for the decryption tool
 *
 * @author Will Goodman
 */
public class Controller {

    private final Model MODEL;

    private HashMap<Character, Float> characterFrequencies;
    private String srcFile;
    private String outFile;
    private String algorithm;


    /**
     * Constructor
     *
     * @param model Model for the decryption tool
     */
    public Controller(Model model) {
        this.MODEL = model;
    }


    /**
     * Gets all available decryption algorithms
     *
     * @return The names of all the available algorithms
     */
    public Set<String> getAlgorithms() {
        HashSet<String> algorithms = new HashSet<>();
        algorithms.addAll(Model.REGULAR_ALGORITHMS);
        algorithms.addAll(Model.FREQUENCY_ALGORITHMS);

        return algorithms;
    }


    /**
     * Gets the name of the source file
     *
     * @return The name of the source file
     */
    public String getSourceFile() {
        return this.srcFile;
    }


    /**
     * Checks a provided source file is valid
     *
     * @param fileName The source file to check
     * @return Whether or not the source file is valid
     */
    public boolean setSourceFile(String fileName) {
        File file = new File(fileName);

        if (file.isFile()) {
            this.srcFile = fileName;

            return true;
        }
        return false;
    }


    /**
     * Gets the name of the output file
     *
     * @return The name of the output file
     */
    public String getOutputFile() {
        return this.outFile;
    }


    /**
     * Sets the name of the output file
     *
     * @param fileName The name of the output file
     * @return Whether or not the output file was updated successfully
     */
    public boolean setOutputFile(String fileName) {
        this.outFile = fileName;

        return true;
    }


    /**
     * Gets the name of the selected algorithm
     *
     * @return The name of the selected algorithm
     */
    public String getAlgorithm() {
        return this.algorithm;
    }


    /**
     * Sets the decryption algorithm to be used
     *
     * @param algorithm The name of the algorithm to use
     * @return Whether or not the algorithm was successfully set
     */
    public boolean setAlgorithm(String algorithm) {
        if (this.getAlgorithms().contains(algorithm)) {
            this.algorithm = algorithm;
            return true;
        }
        return false;
    }


    /**
     * Gets the loaded character frequency values
     *
     * @return The HashMap of characters and their frequencies
     */
    public HashMap<Character,Float> getCharacterFrequencies() {
        return this.characterFrequencies;
    }


    /**
     * Loads and fills the HashMap of characters and their frequencies from a file
     *
     * @param fileName The file to load
     * @return Whether or not the file was loaded successfully
     */
    public boolean loadCharacterFrequencies(String fileName) {
        File file = new File(fileName);

        if (file.isFile()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));

                Pattern validLine = Pattern.compile("^(\\w) (\\d+(.\\d+)?)$");
                Matcher matcher;

                HashMap<Character, Float> tempCharacterFrequencies = new HashMap<>();

                String line;
                while((line = in.readLine()) != null) {
                    matcher = validLine.matcher(line);
                    if (matcher.matches()) {
                        /*
                         *       (\w) (\d+(.\d+)?)
                         * Group:( 1) (     2    )
                         *         e
                         * charAt  0
                         */
                        tempCharacterFrequencies.put(matcher.group(1).charAt(0), Float.parseFloat(matcher.group(2)));
                    } else {
                        return false;
                    }
                }

                this.characterFrequencies = tempCharacterFrequencies;

                return true;
            } catch (IOException | NumberFormatException ex) {
                return false;
            }
        }

        return false;
    }


    /**
     * Decrypts a file using a given algorithm
     *
     * @return Whether or not the file was successfully decrypted
     */
    public boolean decrypt() {
        try {
            if (this.srcFile != null && this.outFile != null && this.algorithm != null) {
                if (Model.REGULAR_ALGORITHMS.contains(this.algorithm)) {
                    MODEL.decrypt(this.srcFile, this.outFile, this.algorithm);
                } else if (Model.FREQUENCY_ALGORITHMS.contains(this.algorithm) && this.characterFrequencies != null) {
                    MODEL.decrypt(this.srcFile, this.outFile, this.algorithm, this.characterFrequencies);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }

        return true;
    }


    /**
     * Decrypts a given string
     *
     * @param encrypted The string to decrypt
     * @return The decrypted string
     */
    public String decrypt(String encrypted) {
        if (this.algorithm != null) {
            if (Model.REGULAR_ALGORITHMS.contains(this.algorithm)) {
                return MODEL.decrypt(encrypted, this.algorithm);
            } else if (Model.FREQUENCY_ALGORITHMS.contains(this.algorithm) && this.characterFrequencies != null) {
                return MODEL.decrypt(encrypted, this.algorithm, this.characterFrequencies);
            } else {
                return encrypted;
            }
        } else {
            return  encrypted;
        }
    }
    
}
