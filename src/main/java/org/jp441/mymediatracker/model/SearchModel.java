package org.jp441.mymediatracker.model;

import org.jp441.mymediatracker.Game;
import org.jp441.mymediatracker.IGDBHandler;
import org.jp441.mymediatracker.mappers.JsonToGameMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchModel {
    private IGDBHandler igdbHandler = new IGDBHandler();
    private JsonToGameMapper jsonToGameMapper = new JsonToGameMapper();
    private ArrayList<Game> gameList = new ArrayList<>();

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void createGameList(String name) {
       gameList.clear();
       JSONArray JsonArray = igdbHandler.searchGameByName(name);
       for(int i = 0; i < JsonArray.length(); i++) {
            JSONObject jsonObject = JsonArray.getJSONObject(i);
            Game game = jsonToGameMapper.createGame(jsonObject);
            System.out.println(game.getCover());
            gameList.add(game);
       }
    }

    public void coverEnlarger() {
        for(Game game : gameList) {
            String coverUrl = game.getCover();
            String convertedUrl = coverUrl.replace("t_thumb", "t_cover_big");
            convertedUrl = "https:" + convertedUrl;
            game.setCover(convertedUrl);
        }
    }
}
