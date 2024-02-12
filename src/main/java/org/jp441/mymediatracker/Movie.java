package org.jp441.mymediatracker;

import java.util.ArrayList;

public class Movie extends Media{
    protected ArrayList<String> director;
    protected ArrayList<String> writer;
    protected String country;
    protected String plot;
    protected String year; //year is a String because of OMDb formatting


    public Movie(){}

    public Movie(
            String name, ArrayList<String> genre,
            ArrayList<String> director, ArrayList<String> writer, String country, String plot,
            String year, double rating
    ) {
        this.name = name;
        this.type = "Movie";
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.country = country;
        this.plot = plot;
        this.year = year;
        this.imdbRating = rating;
    }

    public ArrayList<String> getDirector(){
        return director;
    }

    public String getYear(){
        return year;
    }

    public void setDirector(ArrayList<String> director){
        this.director = director;
    }

    public void setYear(String year){
        this.year = year;
    }

    @Override
    public String toString(){
        return super.toString() + "\nDirector: " + director +
                "\nStudio: " + year;
    }
}
