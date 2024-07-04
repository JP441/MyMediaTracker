package org.jp441.mymediatracker.controller;

import org.jp441.mymediatracker.model.SearchModel;
import org.jp441.mymediatracker.view.SearchView;

public class SearchController {
    private SearchView searchView;
    private SearchModel searchModel;

    public SearchController(SearchView searchView, SearchModel searchModel) {
        this.searchView = searchView;
        this.searchModel = searchModel;
        setInitHandlers();
    }

    private void setInitHandlers() {
        searchView.getSearchBtn().setOnAction(e -> searchForGames());
    }

    private void searchForGames() {
        String searchText = searchView.getSearchTxtField().getText();
        searchView.getSearchTxtField().clear();
        searchModel.createGameList(searchText);
        searchModel.coverEnlarger();
        searchView.createImageViews(searchModel.getGameList());
    }
}
