package org.jp441.mymediatracker;

import java.util.ArrayList;

public class User {
    private String userName;
    private int itemsAdded;
    private ArrayList<Movie> movies;
    private ArrayList<TVShow> tvShows;
    private ArrayList<Game> games;
    private ArrayList<Book> books;
    private ArrayList<Comic> comics;


    public User(){}


    public User(String userName) {
        this.userName = userName;
    }

    public User(
            String userName, ArrayList<Movie> movies, ArrayList<TVShow> tvShows,
            ArrayList<Game> games, ArrayList<Book> books, ArrayList<Comic> comics
            ){
        this(userName);
        this.movies = movies;
        this.tvShows = tvShows;
        this.games = games;
        this.books = books;
        this.comics = comics;
    }

    public User(String userName, int itemsAdded){
        this(userName);
        this.itemsAdded = itemsAdded;
    }

    public String getUserName(){
        return userName;
    }

    public int getItemsAdded(){
        return itemsAdded;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<TVShow> getTvShows() {
        return tvShows;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Comic> getComics() {
        return comics;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setItemsAdded(int itemsAdded){
        this.itemsAdded = itemsAdded;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setTvShows(ArrayList<TVShow> tvShows) {
        this.tvShows = tvShows;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setComics(ArrayList<Comic> comics) {
        this.comics = comics;
    }

    @Override
    public String toString(){
        return userName + " " + itemsAdded;
    }
}


