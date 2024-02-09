package org.jp441.mymediatracker;

public abstract class Media {
    protected String name;
    protected String type;
    protected String genre;
    //status (Complete, Playing/Watching/Reading, Stopped)
    protected String status;
    protected double rating;
    protected byte[] image;

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getGenre(){
        return genre;
    }

    public String getStatus(){ return status; }

    public double getRating(){
        return rating;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setStatus(String status) { this.status = status; }
    public void setRating(double rating){
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                "\nType: " + type +
                "\nGenre: " + genre +
                "\nRating: " + rating;
    }

}
