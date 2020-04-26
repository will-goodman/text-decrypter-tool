package com.willgoodman.model.algorithms.decryption;

import java.io.IOException;

/**
 * Interface for a decryption algorithm
 *
 * @author Will Goodman
 */
public interface DecryptionAlgorithm {


    /**
     * Decrypts a given String
     *
     * @param encrypted The string to be decrypted
     * @return The plain text
     */
    String decrypt(String encrypted);


    /**
     * Decrypts the text of a given file, and writes the result to another given file
     *
     * @param srcFilename The name of the file to decrypt
     * @param outFilename The name of the file to write the result to
     */
    void decrypt(String srcFilename, String outFilename) throws IOException;

}
