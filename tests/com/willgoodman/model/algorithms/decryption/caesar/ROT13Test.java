package com.willgoodman.model.algorithms.decryption.caesar;

import org.junit.Test;

import java.io.IOException;

public class ROT13Test {

    @Test
    public void decrypt() {
        ROT13 rot13 = new ROT13();

        String plainText = rot13.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr.");
        assert plainText.equals("To Sherlock Holmes she is always the woman. I have seldom heard him mention her under any other name.");
    }

    @Test
    public void decryptFile() {
        ROT13 rot13 = new ROT13();
        try {
            rot13.decrypt("cipher.txt", "output.txt");
        } catch (IOException ex) {
            System.out.println("ERROR");
            assert false;
        }
    }
}