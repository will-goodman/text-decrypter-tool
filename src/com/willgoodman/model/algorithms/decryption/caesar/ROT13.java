package com.willgoodman.model.algorithms.decryption.caesar;

import com.willgoodman.model.algorithms.decryption.DecryptionAlgorithm;

import java.io.*;

/**
 * Decrypts text using the ROT13 algorithm
 *
 * @author Will Goodman
 */
public class ROT13 implements DecryptionAlgorithm {

    public final static String NAME = "ROT13";


    /**
     * Decrypts a given String
     *
     * @param encrypted The string to be decrypted
     * @return The plain text
     */
    @Override
    public String decrypt(String encrypted) {
        return rot13(encrypted);
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

                File outFile = new File(outFilename);
                if (!outFile.isFile()) {
                    outFile.createNewFile();
                }

                PrintWriter out = new PrintWriter(new FileWriter(outFile));

                String line;
                while ((line = in.readLine()) != null) {
                    out.println(rot13(line));
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
     * Performs the ROT13 algorithm on a string to either encrypt or decrypt the string
     *
     * @param original The original string to be encrypted/decrypted
     * @return The string after being put through the ROT13 algorithm
     */
    private static String rot13(String original) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : original.toCharArray()) {
            if ((character >= 'a' && character <= 'm') || (character >= 'A' && character <= 'M')) {
                character += 13;
            } else if ((character >= 'n' && character <= 'z') || (character >= 'N' && character <= 'Z')) {
                character -= 13;
            }

            stringBuilder.append(character);
        }

        return stringBuilder.toString();
    }

}
