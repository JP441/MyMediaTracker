package org.jp441.mymediatracker.mappers;

import org.jp441.mymediatracker.Movie;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class JsonToMovieMapper {

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
