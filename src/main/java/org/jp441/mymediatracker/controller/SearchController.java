package org.jp441.mymediatracker.controller;

import org.jp441.mymediatracker.model.SearchModel;
import org.jp441.mymediatracker.view.SearchView;

public class SearchController {
    SearchView searchView;
    SearchModel searchModel;

    public SearchController(SearchView searchView, SearchModel searchModel) {
        this.searchView = searchView;
        this.searchModel = searchModel;
        setHandlers();
    }

    public void setHandlers() {
        searchView.getSearchBtn().setOnAction(e -> searchForGames());
    }

    private void searchForGames() {
        String searchText = searchView.getSearchTxtField().getText();
        System.out.println(searchText);
        searchModel.createGameList(searchText);
        searchModel.coverEnlarger();
        searchView.createImageView(searchModel.getGameList());
    }


}
