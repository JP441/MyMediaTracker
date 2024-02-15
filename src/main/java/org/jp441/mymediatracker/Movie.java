package org.jp441.mymediatracker;

import java.time.YearMonth;
import java.util.ArrayList;

public class Movie extends Media{
    protected ArrayList<String> director;
    protected ArrayList<String> writer;
    protected String plot;
    protected String releaseYear; //releaseYear is a String because of OMDb formatting.
    protected String imdbRating;
    protected String imdbID;

    public Movie(){}

    //constructor for when a user does not provide a rating.
    public Movie(
            String name, ArrayList<String> genre, ArrayList<String> director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image , String imdbRating, YearMonth dateConsumed,
            String status, String imdbID
    ) {
        this.name = name;
        this.type = "Movie";
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.plot = plot;
        this.releaseYear = releaseYear;
        this.image = image;
        this.imdbRating = imdbRating;
        this.userRating = "N/A";
        this.dateConsumed = dateConsumed;
        this.status = status;
        this.imdbID = imdbID;
    }

    //constructor for when a user does provide a rating
    public Movie(
            String name, ArrayList<String> genre, ArrayList<String> director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image , String imdbRating, String userRating, YearMonth dateConsumed,
            String status, String imdbID
    ){
        this(
                name, genre, director, writer, plot, releaseYear,
                image, imdbRating, dateConsumed, status, imdbID
        );
        this.userRating = userRating;
    }

    public ArrayList<String> getDirector(){
        return director;
    }

    public ArrayList<String> getWriter() {
        return writer;
    }

    public String getPlot() {
        return plot;
    }

    public String getReleaseYear(){
        return releaseYear;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setDirector(ArrayList<String> director){
        this.director = director;
    }

    public void setWriter(ArrayList<String> writer) {
        this.writer = writer;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setReleaseYear(String releaseYear){
        this.releaseYear = releaseYear;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString(){
        return super.toString() + "\nDirector: " + director +
                "\nWriter: " + writer +
                "\nPlot: " + plot +
                "\nRelease Year: " + releaseYear +
                "\nIMDB Rating: " + imdbRating;
    }
}
