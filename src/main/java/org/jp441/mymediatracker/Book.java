package org.jp441.mymediatracker;

import java.util.ArrayList;

public class Book extends Media{
    protected String author;
    protected String publisher;

    public Book(){}

    public Book(
            String name, ArrayList<String> genre,
            String author, String publisher,
            String rating
    ){
        this.name = name;
        this.type = "Book";
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.userRating = rating;
    }

    public String getAuthor(){
        return author;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    @Override
    public String toString(){
        return super.toString() + "\nAuthor: " + author +
                "\nPublisher: " + publisher;
    }
}
