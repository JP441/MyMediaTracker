package org.jp441.mymediatracker;

import java.util.ArrayList;

public abstract class Media {
    protected String name;
    protected String type;
    protected ArrayList<String> genre;

    protected double imdbRating;
    protected String image;

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public ArrayList<String> getGenre(){
        return genre;
    }


    public double getImdbRating(){
        return imdbRating;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setGenre(ArrayList<String> genre){
        this.genre = genre;
    }

    public void setImdbRating(double imdbRating){
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                "\nType: " + type +
                "\nGenre: " + genre +
                "\nRating: " + imdbRating;
    }

}
