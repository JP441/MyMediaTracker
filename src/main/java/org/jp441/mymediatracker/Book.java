package org.jp441.mymediatracker;

public class Book extends Media{
    protected String author;
    protected String publisher;

    public Book(){}

    public Book(
            String name, String genre,
            String status, String author, String publisher,
            double rating
    ){
        this.name = name;
        this.type = "Book";
        this.genre = genre;
        this. status = status;
        this.author = author;
        this.publisher = publisher;
        this.rating = rating;
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
