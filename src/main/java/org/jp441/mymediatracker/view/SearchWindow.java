package org.jp441.mymediatracker.view;

import javafx.stage.Stage;
import org.jp441.mymediatracker.Game;
import org.jp441.mymediatracker.controller.GameDetailsController;
import org.jp441.mymediatracker.controller.SearchController;
import org.jp441.mymediatracker.model.GameDetailsModel;
import org.jp441.mymediatracker.model.SearchModel;

public class SearchWindow extends Stage {
    private SearchView searchView;
    private SearchModel searchModel;
    private SearchController searchController;
    private GameDetailsView gameDetailsView;
    private GameDetailsController gameDetailsController;
    private GameDetailsModel gameDetailsModel;

    public void display() {
        createSearchMVC();
        createGameDetailsMVC();
        this.setWidth(1228);
        this.setHeight(880);
        this.setResizable(false);
        this.setScene(searchView.getScene());
        this.show();
    }

    private void createSearchMVC() {
        searchView = new SearchView(this);
        searchModel = new SearchModel();
        searchController = new SearchController(searchView, searchModel);
    }

    private void createGameDetailsMVC() {
        gameDetailsView = new GameDetailsView(this);
        gameDetailsModel = new GameDetailsModel();
        gameDetailsController = new GameDetailsController(gameDetailsView , gameDetailsModel);
    }

    public void setSceneToSearchView() {
        this.setScene(searchView.getScene());
    }

    public void setSceneToGameDetailsView(Game game) {
        this.setScene(gameDetailsView.buildScene(game));
    }

}
