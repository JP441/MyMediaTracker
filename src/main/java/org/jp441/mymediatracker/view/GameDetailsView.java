package org.jp441.mymediatracker.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jp441.mymediatracker.Game;

public class GameDetailsView {
    private Button backBtn;
    private SearchWindow searchWindow;
    private Game game;

    public GameDetailsView(SearchWindow searchWindow) {
        this.searchWindow = searchWindow;
        backBtn = new Button("Back");
    }

    public Scene buildScene(Game game) {
        this.game = game;
        VBox root = new VBox();
        root.getChildren().add(createHeaderHBox());
        root.getChildren().add(backBtn);
        root.getChildren().add(new Label(game.getName()));
        return new Scene(root, 1226, 878);
    }

    private ImageView createGameCover() {
        Image image = new Image(game.getCover());
        return new ImageView(image);
    }

    private ImageView createGameScreenshot() {
        String coverURL = game.getScreenshots().get(0);
        String convertedCoverUrl = coverURL.replace("t_thumb", "t_1080p");
        convertedCoverUrl = "https:" + convertedCoverUrl;
        Image image = new Image(convertedCoverUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(352);
        imageView.setFitWidth(948);
        return imageView;
    }

    private HBox createHeaderHBox() {
        HBox headerHBox = new HBox();
        headerHBox.getChildren().add(createGameCover());
        if(game.getScreenshots().isEmpty()) {
            headerHBox.setBackground(new Background((
                    new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
            return headerHBox;
        } else {
            ImageView imageView = createGameScreenshot();
            headerHBox.getChildren().add(imageView);
            return headerHBox;
        }
        }


    public Button getBackBtn() {
        return backBtn;
    }

    public void displaySearchView() {
        searchWindow.setSceneToSearchView();
    }

}

