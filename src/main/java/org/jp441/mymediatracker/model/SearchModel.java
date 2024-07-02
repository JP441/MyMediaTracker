package org.jp441.mymediatracker.model;

import org.jp441.mymediatracker.IGDBHandler;
import org.jp441.mymediatracker.covers.GameCover;
import org.jp441.mymediatracker.mappers.JsonToGameMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchModel {
    private IGDBHandler igdbHandler = new IGDBHandler();
    private JsonToGameMapper jsonToGameMapper = new JsonToGameMapper();
    private ArrayList<GameCover> gameList = new ArrayList<>();

    public ArrayList<GameCover> getGameList() {
        return gameList;
    }

    public void createGameList(String name) {
       gameList.clear();
       JSONArray JsonArray = igdbHandler.searchGameByName(name);
       for(int i = 0; i < JsonArray.length(); i++) {
            JSONObject jsonObject = JsonArray.getJSONObject(i);
            GameCover gameCover = jsonToGameMapper.createGameCover(jsonObject);
           System.out.println(gameCover.getCoverURL());
            gameList.add(gameCover);
       }
    }

    public void coverEnlarger() {
        for(GameCover gameCover : gameList) {
            String coverUrl = gameCover.getCoverURL();
            String convertedUrl = coverUrl.replace("t_thumb", "t_cover_big");
            convertedUrl = "https:" + convertedUrl;
            gameCover.setCoverURL(convertedUrl);
        }
    }



}
