package org.jp441.mymediatracker.mappers;

import org.jp441.mymediatracker.Game;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.time.Instant;

public class JsonToPojoMapper {

    public Game createGame(JSONObject jsonGame){
        Game  game = Game.builder()
                .id(jsonGame.getInt("id"))
                .name(jsonGame.getString("name"))
                .genres(getIGDBNames(jsonGame, "genres"))
                .cover(jsonGame.getJSONObject("cover").getString("url"))
                .platforms(getIGDBNames(jsonGame, "platforms"))
                .firstReleaseDate(jsonGame.getLong("first_release_date"))
                .igdbRating((Math.round(checkIgdbRating(jsonGame))))
                .summary(jsonGame.getString("summary"))
                .build();
        return game;
    }

    //The game data contains IDs for various things that the application has no use for. Such as
    //genre ID and Platform ID. This function will just extract the name from that data.
    private ArrayList<String> getIGDBNames(JSONObject game, String key){
        JSONArray jsonArray = game.getJSONArray(key);
        ArrayList<String> extractedNames = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            extractedNames.add(jsonArray.getJSONObject(i).getString("name"));
        }
        return extractedNames;
    }

    //sometimes a game from the IGDB API will not have a rating. This function checks if an
    //igdb rating exists and if it does it will return it. Else this function will return 0.0
    private double checkIgdbRating(JSONObject jsonObj){
        if(jsonObj.has("rating")){
            return jsonObj.getDouble("rating");
        }
        return 0.0;
    }

    private LocalDate convertEpochToLocalDate(JSONObject jsonObj){
        long epoch = jsonObj.getLong("first_release_date");
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.of("GMT")).toLocalDate();
    }

}
