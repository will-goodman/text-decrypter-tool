package com.willgoodman.controller;

import com.willgoodman.model.Model;
import com.willgoodman.model.algorithms.decryption.caesar.ROT13;
import org.junit.Test;

import java.util.HashSet;


public class ControllerTest {

    @Test
    public void getAlgorithms() {
        Model model = new Model();
        Controller controller = new Controller(model);

        HashSet<String> algorithms = new HashSet<>();
        algorithms.addAll(Model.REGULAR_ALGORITHMS);
        algorithms.addAll(Model.FREQUENCY_ALGORITHMS);

        assert controller.getAlgorithms().equals(algorithms);
    }

    @Test
    public void getSourceFile() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getSourceFile() == null;
    }

    @Test
    public void setSourceFileInvalid() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getSourceFile() == null;

        controller.setSourceFile("source.txt");

        assert controller.getSourceFile() == null;
    }

    @Test
    public void getOutputFile() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getOutputFile() == null;
    }

    @Test
    public void setOutputFile() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getOutputFile() == null;

        controller.setOutputFile("output.txt");

        assert controller.getOutputFile().equals("output.txt");
    }

    @Test
    public void getAlgorithm() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getAlgorithm() == null;
    }

    @Test
    public void setAlgorithm() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getAlgorithm() == null;

        controller.setAlgorithm(ROT13.NAME);

        assert controller.getAlgorithm().equals(ROT13.NAME);
    }

    @Test
    public void setAlgorithmInvalid() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getAlgorithm() == null;

        controller.setAlgorithm("fake algorithm");

        assert controller.getAlgorithm() == null;
    }

    @Test
    public void getCharacterFrequencies() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getCharacterFrequencies() == null;
    }

    @Test
    public void loadCharacterFrequenciesInvalid() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert controller.getCharacterFrequencies() == null;

        controller.loadCharacterFrequencies("frequencies.txt");

        assert controller.getCharacterFrequencies() == null;
    }

    @Test
    public void decryptInvalid() {
        Model model = new Model();
        Controller controller = new Controller(model);

        assert !controller.decrypt();
    }


    @Test
    public void decryptString() {
        Model model = new Model();
        Controller controller = new Controller(model);

        controller.setAlgorithm(ROT13.NAME);

        String plainText = controller.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr.");
        assert plainText.equals("To Sherlock Holmes she is always the woman. I have seldom heard him mention her under any other name.");
    }
}