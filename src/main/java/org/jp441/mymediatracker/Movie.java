package org.jp441.mymediatracker;

public class Movie extends Media{
    protected String director;
    protected String studio;

    public Movie(){}

    public Movie(
            String name, String genre, String status,
            String director, String studio, double rating
    ) {
        this.name = name;
        this.type = "Movie";
        this.genre = genre;
        this.status = status;
        this.director = director;
        this.studio = studio;
        this.rating = rating;
    }

    public String getDirector(){
        return director;
    }

    public String getStudio(){
        return studio;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setStudio(String studio){
        this.studio = studio;
    }

    @Override
    public String toString(){
        return super.toString() + "\nDirector: " + director +
                "\nStudio: " + studio;
    }
}
