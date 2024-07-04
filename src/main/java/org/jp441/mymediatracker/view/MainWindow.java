package org.jp441.mymediatracker.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private Button openSearchBtn;
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        SearchWindow searchWindow = new SearchWindow();
        openSearchBtn = new Button("Search");
        openSearchBtn.setOnAction(e -> searchWindow.display());
        root.getChildren().add(openSearchBtn);
        Scene scene = new Scene(root, 300 ,300);
        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

