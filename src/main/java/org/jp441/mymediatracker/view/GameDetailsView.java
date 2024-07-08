package org.jp441.mymediatracker.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        root.getChildren().add(createGameTitleHBox());
        root.getChildren().add(createGenresHBox());
        root.getChildren().add(backBtn);
        Scene scene = new Scene(root, 1226, 878);
        scene.getStylesheets().add(getClass().getResource("/org/jp441/mymediatracker/css/darkMode.css").toExternalForm());
        return scene;
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

    private HBox createGameTitleHBox() {
        HBox hbox = new HBox();
        Label label = new Label(game.getName());
        label.setId("TitleLabel");
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setId("GameTitleHBox");
        return hbox;
    }

    private HBox createGenresHBox() {
        HBox tagHBox = new HBox(5);
        tagHBox.setPadding(new Insets(10,0,10,0));
        if(!game.getGenres().isEmpty()) {
            for(String genre: game.getGenres()) {
                StackPane genreTag = createGenreTag(genre);
                tagHBox.getChildren().add(genreTag);
            }
        }
        return tagHBox;
    }

    private StackPane createGenreTag(String genre) {
        Label label = new Label(genre);
        label.setPadding(new Insets(5,10,5,10));
        Rectangle rectangle = new Rectangle();
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(30);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.widthProperty().bind(label.widthProperty().add(20));
        rectangle.heightProperty().bind(label.heightProperty().add(10));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, label);
        return stackPane;
    }




    public Button getBackBtn() {
        return backBtn;
    }

    public void displaySearchView() {
        searchWindow.setSceneToSearchView();
    }

}

