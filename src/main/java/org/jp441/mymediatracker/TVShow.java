package org.jp441.mymediatracker;

public class TVShow extends Movie{
    private int season;
    public TVShow(){}

    public TVShow(
            String name, String genre, String status,
            String director, String studio, int season, double rating
           ){
        super(name, genre, status, director, studio, rating);
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
