package org.jp441.mymediatracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jp441.mymediatracker.Game;

public class GameDetailsView {
    private Button backBtn;
    private SearchWindow searchWindow;

    public GameDetailsView(SearchWindow searchWindow) {
        this.searchWindow = searchWindow;
        backBtn = new Button("Back");
    }

    public Scene buildScene(Game game) {
        VBox root = new VBox();
        root.getChildren().add(backBtn);
        root.getChildren().add(new Label(game.getName()));
        return new Scene(root, 300, 300);
    }

    public Button getBackBtn() {
        return backBtn;
    }

    public void displaySearchView() {
        searchWindow.setSceneToSearchView();
    }

}

