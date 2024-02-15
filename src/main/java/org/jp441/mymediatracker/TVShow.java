package org.jp441.mymediatracker;

import java.time.YearMonth;
import java.util.ArrayList;

public class TVShow extends Movie{
    private String season; //The amount of seasons currently available
    private String seasonWatched; //The season the user has watched
    public TVShow(){}

    //constructor when the user does not provide a rating.
    public TVShow(
            String name, ArrayList<String> genre, ArrayList<String> director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String season, String seasonWatched,
            String imdbRating, YearMonth dateConsumed, String status,
            String imdbID
           ){
        super(
                name, genre, director, writer, plot, releaseYear, image,
                imdbRating, dateConsumed, status, imdbID
        );
        this.type = "TV Show";
        this.userRating = "N/A";
        this.seasonWatched = seasonWatched;
        this.season = season;
    }

    public TVShow(
            String name, ArrayList<String> genre, ArrayList<String> director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String season, String seasonWatched,
            String imdbRating, String userRating, YearMonth dateConsumed,
            String status, String imdbID
    ){
        this(
                name, genre, director, writer, plot, releaseYear, image,
                season, seasonWatched, imdbRating, dateConsumed,
                status, imdbID
        );
        this.userRating = userRating;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeasonWatched(){
        return seasonWatched;
    }

    public void setSeasonWatched(String seasonWatched){
        this.seasonWatched = seasonWatched;
    }

    @Override
    public String toString(){
        return super.toString() + "\nSeason Watched: " + seasonWatched +
                "Seasons: " + season;
    }

}
