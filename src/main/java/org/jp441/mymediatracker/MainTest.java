package org.jp441.mymediatracker;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {

        OMDbHandler omdb = new OMDbHandler();
        JSONObject json = omdb.searchMovieByYear("it follows", "2014");
        String[] genreArray = json.getString("Genre").split(",\\s");
        String[] directorArray = json.getString("Director").split(",\\s");
        String[] writerArray = json.getString("Writer").split(",\\s");
        ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genreArray));
        ArrayList<String> directorList = new ArrayList<>(Arrays.asList(directorArray));
        ArrayList<String> writerList = new ArrayList<>(Arrays.asList(writerArray));
        String name = json.getString("Title");
        String plot = json.getString("Plot");
        String year = json.getString("Year");
        String imdbID = json.getString("imdbID");
        double rating = Double.parseDouble(json.getString("imdbRating"));
        MongoDB.getMongoDB().addMovie(name, genreList, directorList, writerList, plot, year, rating, imdbID);
        MongoIterable<Document> test = MongoDB.getMongoDB().getMovieCollection().find();
        for (Document movie : test) {
            System.out.println(movie.toJson());
        }
    }
}
