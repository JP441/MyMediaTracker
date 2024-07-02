package org.jp441.mymediatracker.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jp441.mymediatracker.covers.GameCover;

import java.util.ArrayList;

public class SearchView extends Stage {
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

    public TilePane getTilePane() {
        return tilePane;
    }

    public SearchView() {
        root = new VBox();
        searchHBox = new HBox();
        createTilePane();
        createScrollPane();
        root.getChildren().addAll(searchHBox, tilePane, scrollPane);
        searchTxtField = new TextField();
        searchBtn = new Button("Search");
        searchHBox.getChildren().addAll(searchTxtField, searchBtn);
        HBox.setHgrow(searchTxtField, Priority.ALWAYS);
        scene = new Scene(root, 840,800);
        this.setTitle("Media Search");
        this.setScene(scene);
    }

    public void display() {
        this.setScene(scene);
        this.show();
    }

    private void createTilePane() {
        tilePane = new TilePane();
        tilePane.setPrefColumns(4);
        tilePane.setPrefTileWidth(210);
        tilePane.setPrefTileHeight(410);
    }

    private void createScrollPane() {
        scrollPane = new ScrollPane(tilePane);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void createImageView(ArrayList<GameCover> gameCovers) {
        tilePane.getChildren().clear();
        for(GameCover gameCover: gameCovers) {
            Image image = new Image(gameCover.getCoverURL());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(400);
            tilePane.getChildren().add(imageView);
        }
    }

//    private ImageView gameImageTest() {
//        GameCoverFactory gameCoverFactory = new GameCoverFactory();
//        Cover gameCover = gameCoverFactory.createSearchCover("https://images.igdb.com/igdb/image/upload/t_thumb/co39vc.jpg");
//        return new ImageView(gameCover.getCover());
//    }

//            tilePane.getChildren().addAll(
//            test,
//                new Rectangle(200, 400, Color.RED),
//                new Rectangle( 200, 400, Color.GREEN ),
//                new Rectangle( 200, 400, Color.BLUE ),
//                new Rectangle( 200, 400, Color.YELLOW ),
//                new Rectangle( 200, 400, Color.CYAN ),
//                new Rectangle( 200, 400, Color.PURPLE ),
//                new Rectangle( 200, 400, Color.BROWN ),
//                new Rectangle( 200, 400, Color.PINK ),
//                new Rectangle( 200, 400, Color.ORANGE )
//        );

}
