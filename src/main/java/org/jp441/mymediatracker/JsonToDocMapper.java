//This class converts Json data into a Document, which is then used to insert data into MongoDB
package org.jp441.mymediatracker;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class JsonToDocMapper {

    public Document createMovie(
            String name, ArrayList<String> genre, ArrayList<String>director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String imdbRating, String userRating,
            String status, String dateConsumed, String imdbID
    ){
        Document newMovie = new Document("name", name)
                .append("genre", genre)
                .append("director", director)
                .append("writer", writer)
                .append("plot", plot)
                .append("releaseYear", releaseYear)
                .append("image", image)
                .append("imdbRating", imdbRating)
                .append("userRating", userRating)
                .append("dateConsumed", dateConsumed)
                .append("status", status)
                .append("imdbID", imdbID);
        return newMovie;
    }

    public Document createTVShow(
            String name, ArrayList<String> genre, ArrayList<String>director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String season, String seasonWatched,
            String imdbRating, String userRating, String status,
            String dateConsumed, String imdbID
    ){
        Document newTVShow = new Document("name", name)
                .append("genre", genre)
                .append("director", director)
                .append("writer", writer)
                .append("plot", plot)
                .append("releaseYear", releaseYear)
                .append("image", image)
                .append("season", season)
                .append("seasonWatched", seasonWatched)
                .append("imdbRating", imdbRating)
                .append("userRating", userRating)
                .append("dateConsumed", dateConsumed)
                .append("status", status)
                .append("imdbID", imdbID);
        return newTVShow;
    }

    public Document createGame(
            JSONObject game, double userRating, String status, LocalDate dateConsumed
    ){
        Document newGameDoc = new Document("id", game.getInt("id"))
                .append("name", game.getString("name"))
                .append("genres", getIGDBNames(game, "genres"))
                .append("cover", game.getJSONObject("cover").getString("url"))
                .append("platforms", getIGDBNames(game, "platforms"))
                .append("firstReleaseDate", LocalDate.ofEpochDay(game.getLong("first_release_date")))
                .append("igdbRating", game.getDouble("rating"))
                .append("summary", game.getString("summary"))
                .append("userRating", userRating)
                .append("status", status)
                .append("dateConsumed", dateConsumed);
        return newGameDoc;
    }

    //The game data contains IDs for various things that the application has no use for. Such as
    //genre ID and Platform ID. This function will just extract the name from that data.
    public ArrayList<String> getIGDBNames(JSONObject game, String key){
        JSONArray jsonArray = game.getJSONArray(key);
        ArrayList<String> extractedNames = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            extractedNames.add(jsonArray.getJSONObject(i).getString("name"));
        }
        return extractedNames;
    }
}

