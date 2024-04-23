package org.jp441.mymediatracker.mappers;

import org.jp441.mymediatracker.Game;
import org.jp441.mymediatracker.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class JsonToPojoMapper {

    public Game createGame(JSONObject jsonGame){
        Game game = Game.builder()
                .igdbID(jsonGame.getInt("id"))
                .name(jsonGame.getString("name"))
                .genres(getIGDBNames(jsonGame, "genres"))
                .cover(checkIgdbCover(jsonGame))
                .platforms(getIGDBNames(jsonGame, "platforms"))
                .firstReleaseDate(checkIgdbFirstReleaseDate(jsonGame))
                .igdbRating((Math.round(checkIgdbRating(jsonGame))))
                .summary(checkIGDBSummary(jsonGame))
                .build();
        return game;
    }

    //This function creates a Movie object, with the results from a search request
    //from the OMDB API.
    public Movie createSearchMovie(JSONObject jsonMovie){
        Movie movie = Movie.builder()
                .name(jsonMovie.getString("Title"))
                .releaseYear(jsonMovie.getString("Year"))
                .imdbID(jsonMovie.getString("imdbID"))
                .cover(jsonMovie.getString("Poster"))
                .build();
        return movie;
    }

    public Movie createFullMovie(JSONObject jsonObject){
        Movie movie = createSearchMovie(jsonObject);
        movie.setAgeRating(jsonObject.getString("Rated"));
        movie.setFullReleaseDate(omdbDateToLocalDate(jsonObject.getString("Released")));
        movie.setRuntime(jsonObject.getString("Runtime"));
        movie.setGenres(stringToArrayList(jsonObject.getString("Genre")));
        movie.setDirectors(stringToArrayList(jsonObject.getString("Director")));
        movie.setWriters(stringToArrayList(jsonObject.getString("Writer")));
        movie.setActors(stringToArrayList(jsonObject.getString("Actors")));
        movie.setPlot(jsonObject.getString("Plot"));
        movie.setImdbRating(jsonObject.getString("imdbRating"));
        movie.setMetascore(jsonObject.getString("Metascore"));
        return movie;
    }

    //The game data contains IDs for various things that MyMediaManager has no use for, such as
    //genre ID and Platform ID. This function will just extract the names from that data.
    //Sometimes the lists containing the names we want to extract are missing in the api data, so
    //we will return an empty ArrayList if this is the case.
    private ArrayList<String> getIGDBNames(JSONObject game, String key){
        if(game.has(key)){
            JSONArray jsonArray = game.getJSONArray(key);
            ArrayList<String> extractedNames = new ArrayList<>();
            for(int i=0; i < jsonArray.length(); i++){
                extractedNames.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return extractedNames;
        }
        return new ArrayList<String>();
    }

    //sometimes a game from the IGDB API will not have a rating. This function checks if an
    //igdb rating exists and if it does it will return it. Else this function will return 0.0
    private double checkIgdbRating(JSONObject jsonObj){
        if(jsonObj.has("rating")){
            return jsonObj.getDouble("rating");
        }
        return 0.0;
    }

    //Sometimes a game from the IGDB API will not have a cover. If there is no cover, then return
    //IGDB's missing cover image. Else return actual url.
    private String checkIgdbCover(JSONObject jsonObj){
        if(jsonObj.has("cover")){
            return jsonObj.getJSONObject("cover").getString("url");
        }
        return "//images.igdb.com/igdb/image/upload/t_cover_big/nocover.png";
    }

    //Sometimes a game from the IGDB API will not have a first_release_date.
    //If the first_release_date is missing, the method will return a default value of -1000000000,
    //which corresponds to the year 1938 in Unix timestamp, a period before the release of any
    //video games. This default value will be used later to indicate an unknown release date.
    //If there is a first_release_date, return that value.
    private long checkIgdbFirstReleaseDate(JSONObject jsonObj){
        if(jsonObj.has("first_release_date")){
            return jsonObj.getLong("first_release_date");
        }
        long noReleaseDate = -1000000000;
        return noReleaseDate;
    }

    private String checkIGDBSummary(JSONObject jsonObj){
        if(jsonObj.has("summary")){
            return jsonObj.getString("summary");
        }
        return "No summary";
    }

    private LocalDate convertEpochToLocalDate(JSONObject jsonObj){
        long epoch = jsonObj.getLong("first_release_date");
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.of("GMT")).toLocalDate();
    }

    private LocalDate omdbDateToLocalDate(String omdbDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(omdbDate, formatter);
        return localDate;
    }

    private ArrayList<String> stringToArrayList(String retrievedString) {
        String[] stringArray = retrievedString.split(",\\s");
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, stringArray);
        return arrayList;
    }
}
