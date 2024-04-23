package org.jp441.mymediatracker;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Builder
public class Movie{
    private String mongoID;
    private String name;
    private String cover;
    private String releaseYear; //releaseYear is for search results
    private String ageRating;
    private LocalDate fullReleaseDate;
    private String runtime;
    private ArrayList<String> genres;
    private ArrayList<String> directors;
    private ArrayList<String> writers;
    private ArrayList<String> actors;
    private String plot;
    private String imdbRating;
    private String metascore;
    private String imdbID;
    private String userRating;
    private LocalDate dateConsumed;
}
