package org.jp441.mymediatracker;

import java.util.ArrayList;
import java.time.YearMonth;

public abstract class Media {
    protected String name;
    protected String type;
    protected String image;
    protected String status;
    //userRating is a string because a user can choose not to rate a piece of media.
    //If they do not a give a rating then it will appear as N/A.
    protected String userRating;
    protected ArrayList<String> genre;

    //dateConsumed is used for when the user watched/read/played
    //that piece of media. This will be used to sort media based
    //on date.
    protected YearMonth dateConsumed;

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getGenre(){
        return genre;
    }

    public YearMonth getYearMonth(){
        return dateConsumed;
    }

    public String getUserRating(){
        return userRating;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGenre(ArrayList<String> genre){
        this.genre = genre;
    }

    public void setUserRating(String userRating){
        this.userRating = userRating;
    }


    @Override
    public String toString(){
        return "Name: " + name +
                "\nType: " + type +
                "\nimage: " + image +
                "\nstatus: "  + status +
                "\nGenre: " + genre +
                "\nDate Consumed" + dateConsumed +
                "\nUser Rating: " + userRating +
                "\nDate Consumed: " + dateConsumed.toString();
    }

}
