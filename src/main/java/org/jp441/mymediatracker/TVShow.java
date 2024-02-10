package org.jp441.mymediatracker;

import java.util.ArrayList;

public class TVShow extends Movie{
    private int season;
    public TVShow(){}

    public TVShow(
            String name, ArrayList<String> genre, String status,
            ArrayList<String> director, ArrayList<String> writer,
            String country, String plot, String year,
            int season, double rating
           ){
        super(name, genre, status, director, writer, country, plot, year, rating);
        this.type = "TV Show";
        this.season = season;
    }

    public int getSeason(){
        return season;
    }

    public void setSeason(int season){
        this.season = season;
    }

    @Override
    public String toString(){
        return super.toString() + "\nSeason: " + season;
    }

}
