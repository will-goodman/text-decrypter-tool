package com.willgoodman.view;

import com.willgoodman.controller.Controller;
import com.willgoodman.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * GUI for the Decryption tool written using Swing
 *
 * @author Will Goodman
 */
public class SwingGUI {

    private final static int DEFAULT_WIDTH = 800;
    private final static int DEFAULT_HEIGHT = 500;
    private final static String OUTPUT_FILE = new File("output.txt").getAbsolutePath();

    public static void main(String[] args) {
        Controller controller = new Controller(new Model());

        controller.setOutputFile(OUTPUT_FILE);

        JFrame frame = new JFrame("Decryption Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel headPanel = new JPanel();
        JLabel labTitle = new JLabel("Decryption Tool");
        labTitle.setPreferredSize(new Dimension (100,50));
        headPanel.add(labTitle);

        JPanel centerPanel = new JPanel();

        JPanel optionsPanel = new JPanel(new BorderLayout());

        JPanel frequenciesFilePanel = new JPanel(new BorderLayout());
        JLabel labFrequenciesFile = new JLabel("The file currently loaded:");
        JTextArea txtFrequencyFile = new JTextArea();
        JButton btnLoadFrequencies = new JButton("Load Character Frequencies");
        frequenciesFilePanel.add(btnLoadFrequencies, BorderLayout.NORTH);
        frequenciesFilePanel.add(labFrequenciesFile, BorderLayout.CENTER);
        frequenciesFilePanel.add(txtFrequencyFile, BorderLayout.SOUTH);
        frequenciesFilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        JPanel encryptedFilePanel = new JPanel(new BorderLayout());
        JLabel labEncryptedFile = new JLabel("The file currently loaded:");
        JTextArea txtEncryptedFile = new JTextArea();
        JButton btnLoadEncrypted = new JButton("Load Encrypted File");
        encryptedFilePanel.add(btnLoadEncrypted, BorderLayout.NORTH);
        encryptedFilePanel.add(labEncryptedFile, BorderLayout.CENTER);
        encryptedFilePanel.add(txtEncryptedFile, BorderLayout.SOUTH);

        optionsPanel.add(frequenciesFilePanel, BorderLayout.NORTH);
        optionsPanel.add(encryptedFilePanel, BorderLayout.SOUTH);

        JPanel frequenciesPanel = new JPanel(new BorderLayout());
        JLabel labFrequenciesTitle = new JLabel("Frequencies:");
        JTextArea txtFrequencies = new JTextArea();
        JScrollPane sclFrequencies = new JScrollPane(txtFrequencies);
        sclFrequencies.setPreferredSize(new Dimension (200,300));
        frequenciesPanel.add(labFrequenciesTitle, BorderLayout.NORTH);
        frequenciesPanel.add(sclFrequencies, BorderLayout.CENTER);

        centerPanel.add(optionsPanel);
        centerPanel.add(frequenciesPanel);

        JPanel bottomPanel = new JPanel();
        JButton btnDecrypt = new JButton("Decrypt");
        bottomPanel.add(btnDecrypt);

        mainPanel.add(headPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        btnLoadFrequencies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooseFrequencies = new JFileChooser();
                int returnOption = chooseFrequencies.showOpenDialog(null);

                if(returnOption == JFileChooser.APPROVE_OPTION) {
                    String frequenciesFile = chooseFrequencies.getSelectedFile().getAbsolutePath();
                    txtFrequencyFile.setText(frequenciesFile);
                    if (controller.loadCharacterFrequencies(frequenciesFile)) {
                        txtFrequencies.setText(controller.getCharacterFrequencies().toString());
                        JOptionPane.showMessageDialog(frame, "Successfully loaded: \n" + frequenciesFile);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error loading " + frequenciesFile);
                    }
                }
            }
        });


        btnLoadEncrypted.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooseCipher = new JFileChooser();
                int returnOption = chooseCipher.showOpenDialog(null);

                if(returnOption == JFileChooser.APPROVE_OPTION) {
                    String cipherFile = chooseCipher.getSelectedFile().getAbsolutePath();
                    if (controller.setSourceFile(cipherFile)) {
                        txtEncryptedFile.setText(cipherFile);
                        JOptionPane.showMessageDialog(frame, "Successfully loaded: \n" + cipherFile);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error loading " + cipherFile);
                    }
                }
            }
        });


        btnDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controller.getSourceFile() != null && controller.getOutputFile() != null) {
                    String[] algorithms = controller.getAlgorithms().toArray(new String[]{});
                    String input = (String) JOptionPane.showInputDialog(null, "Please select an algorithm below:", "Choose Algorithm", JOptionPane.QUESTION_MESSAGE, null, algorithms, algorithms[0]);
                    controller.setAlgorithm(input);

                    if (controller.decrypt()) {
                        JOptionPane.showMessageDialog(frame, "Decrypted successfully");
                    } else {
                        JOptionPane.showMessageDialog(frame, "ERROR: Not decrypted successfully");
                    }
                }
            }
        });
    }
    
}
