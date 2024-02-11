package org.jp441.mymediatracker;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        ArrayList<String> genre = new ArrayList<>(Arrays.asList("Comedy", "Romance"));
        ArrayList<String> director = new ArrayList<>(Arrays.asList("Alphalpha"));
        ArrayList<String> writer = new ArrayList<>(Arrays.asList("sergio"));
        String name = "Star Wars";
        String country = "USA";
        String plot = "blah blah blah";
        String year = "1995";
        double rating = 10.0;

        MongoDB.getMongoDB().addMovie(name, genre, director, writer, country, plot, year, rating);
        MongoIterable<Document> test = MongoDB.getMongoDB().getMovieCollection().find();
        for (Document movie : test) {
            System.out.println(movie.toJson());
        }
    }
}
