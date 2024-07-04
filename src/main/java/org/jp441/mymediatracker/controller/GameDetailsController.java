package org.jp441.mymediatracker.controller;

import org.jp441.mymediatracker.model.GameDetailsModel;
import org.jp441.mymediatracker.view.GameDetailsView;

public class GameDetailsController {
    GameDetailsView gameDetailsView;
    GameDetailsModel gameDetailsModel;

    public GameDetailsController(
            GameDetailsView gameDetailsView, GameDetailsModel gameDetailsModel
    ) {
        this.gameDetailsView = gameDetailsView;
        this.gameDetailsModel = gameDetailsModel;
        setInitHandlers();
    }

    private void setInitHandlers() {
        gameDetailsView.getBackBtn().setOnAction(e -> gameDetailsView.displaySearchView());
    }
}
