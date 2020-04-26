package com.willgoodman.view;

import com.willgoodman.controller.Controller;

import com.willgoodman.model.Model;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * GUI for the decryption tool using JavaFX
 *
 * @author Will Goodman
 */
public class JavaFxGUI extends Application {

    private final static int DEFAULT_WIDTH = 600;
    private final static int DEFAULT_HEIGHT = 300;

    private final Controller CONTROLLER;

    private ArrayList<String> algorithms;


    /**
     * Constructor
     */
    public JavaFxGUI() {
        this.CONTROLLER = new Controller(new Model());
        this.algorithms = new ArrayList<>();
        this.algorithms.addAll(this.CONTROLLER.getAlgorithms());
    }


    /**
     * Launches the decryption tool application
     *
     * @param args Passed CLI arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Renders the JavaFxGUI
     *
     * @param primaryStage The JavaFX JavaFxGUI stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Decryption Tool");

        StackPane pane = new StackPane();

        Scene scene = new Scene(pane, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        scene.setFill(Color.GHOSTWHITE);

        Text title = new Text();
        title.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setText("Decryption Tool");
        title.setUnderline(true);
        title.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(title);
        StackPane.setAlignment(title, Pos.TOP_CENTER);

        ObservableList<String> algorithms = FXCollections.observableList(this.algorithms);
        ComboBox algorithmChoice = new ComboBox<>(algorithms);
        algorithmChoice.setPromptText("Select an algorithm...");
        pane.getChildren().add(algorithmChoice);
        StackPane.setAlignment(algorithmChoice, Pos.CENTER_LEFT);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


}
