package org.jp441.mymediatracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.jp441.mymediatracker.Game;

import java.util.ArrayList;

public class SearchView {
    private SearchWindow searchWindow;
    private VBox root;
    private HBox searchHBox;
    private TilePane tilePane;
    private ScrollPane scrollPane;
    private Button searchBtn;
    private TextField searchTxtField;
    private Scene scene;

    public Button getSearchBtn() {
        return searchBtn;
    }

    public TextField getSearchTxtField() {
        return searchTxtField;
    }

    public Scene getScene() { return scene; }

    public SearchView(SearchWindow searchWindow) {
        this.searchWindow = searchWindow;
        root = new VBox();
        searchHBox = new HBox();
        createTilePane();
        createScrollPane();
        root.getChildren().addAll(searchHBox, tilePane, scrollPane);
        searchTxtField = new TextField();
        searchBtn = new Button("Search");
        searchHBox.getChildren().addAll(searchTxtField, searchBtn);
        HBox.setHgrow(searchTxtField, Priority.ALWAYS);
        scene = new Scene(root, 840,800);;
    }

    private void createTilePane() {
        tilePane = new TilePane();
        tilePane.setPrefColumns(5);
        tilePane.setPrefTileWidth(240);
        tilePane.setPrefTileHeight(410);
    }

    private void createScrollPane() {
        scrollPane = new ScrollPane(tilePane);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void createImageViews(ArrayList<Game> games) {
        tilePane.getChildren().clear();
        for(Game game : games) {
            Image image = new Image(game.getCover());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(230);
            imageView.setFitHeight(400);
            imageView.setOnMouseClicked(event -> displaySpecificGameDetails(game));
            Tooltip toolTipTxt = new Tooltip(game.getName());
            Tooltip.install(imageView, toolTipTxt);
            tilePane.getChildren().add(imageView);
        }
    }

    private void displaySpecificGameDetails(Game game) {
       this.searchWindow.setSceneToGameDetailsView(game);
    }
}
