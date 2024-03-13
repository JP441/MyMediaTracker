//This class converts Json data into a Document, which is then used to insert data into MongoDB
package org.jp441.mymediatracker.mappers;
import org.bson.Document;
import org.jp441.mymediatracker.Game;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class PojoToDocMapper {

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

//    public Document createGameDoc(Game game){
//        Document newGameDoc = new Document("id", game.getId())
//                .append("name", game.getName())
//                .append("genres", game.getGenres())
//                .append("cover", game.getCover())
//                .append("platforms", game.getPlatforms())
//                .append("firstReleaseDate", game.getFirstReleaseDate())
//                .append("igdbRating", game.getIgdbRating())
//                .append("summary", game.getSummary())
//                .append("userRating", game.getUserRating())
//                .append("status", game.getStatus())
//                .append("dateConsumed", game.getDateConsumed());
//        return newGameDoc;
//    }

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

