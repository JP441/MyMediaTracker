package org.jp441.mymediatracker.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jp441.mymediatracker.controller.SearchController;
import org.jp441.mymediatracker.model.SearchModel;


public class MainWindow extends Application {
    private Button openSearchBtn;
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        SearchView searchView = new SearchView();
        SearchModel searchModel = new SearchModel();
        SearchController searchController = new SearchController(searchView, searchModel);
        openSearchBtn = new Button("Search");
        openSearchBtn.setOnAction(e -> searchView.display());
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

