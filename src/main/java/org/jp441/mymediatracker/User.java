package org.jp441.mymediatracker;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class User {
    private String username;
    private int itemsAdded;
    private ArrayList<Movie> movies;
    private ArrayList<TVShow> tvShows;
    private ArrayList<Game> games;
    private ArrayList<Book> books;
    private ArrayList<Comic> comics;
}


