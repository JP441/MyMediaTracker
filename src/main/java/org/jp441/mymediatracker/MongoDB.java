package org.jp441.mymediatracker;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;


public class MongoDB {

    private static MongoDB mongoDB;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoDB(){
        mongoClient = MongoClients.create();
        mongoDatabase = mongoClient.getDatabase("MyMediaManager");
    }
    public static MongoDB getMongoDB(){
        if(mongoDB == null){
            synchronized (MongoDB.class){
                if (mongoDB == null){
                    mongoDB = new MongoDB();
                }
            }
        }
        return mongoDB;
    }

    public MongoCollection<Document> getMovieCollection(){
        return mongoDatabase.getCollection("movies");
    }
    public MongoCollection<Document> getTVShowCollection(){return mongoDatabase.getCollection("tvshows");}
    public MongoCollection<Document> getUserCollection() {return mongoDatabase.getCollection("users");}


    public void addUser(String userName){
        ArrayList<Document> movies = new ArrayList<>();
        ArrayList<Document> tvShows = new ArrayList<>();
        ArrayList<Document> games = new ArrayList<>();
        ArrayList<Document> books = new ArrayList<>();
        ArrayList<Document> comics = new ArrayList<>();
        Document newUser = new Document("userName", userName)
                .append("itemsAdded", 0)
                .append("movies", movies)
                .append("tvShows", tvShows)
                .append("games", games)
                .append("books", books)
                .append("comics", comics);
        getUserCollection().insertOne(newUser);
    }

    public Document addMovie(
            String name, ArrayList<String> genre, ArrayList<String>director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String imdbRating, String userRating,
            String status, String dateConsumed, String imdbID
    ){
        Document newMovie = new Document("name", name)
                .append("genre", genre)
                .append("director", director)
                .append("writer", writer)
                .append("plot", plot)
                .append("releaseYear", releaseYear)
                .append("image", image)
                .append("imdbRating", imdbRating)
                .append("userRating", userRating)
                .append("dateConsumed", dateConsumed)
                .append("status", status)
                .append("imdbID", imdbID);
        return newMovie;
    }

    public Document addTVShow(
            String name, ArrayList<String> genre, ArrayList<String>director,
            ArrayList<String> writer, String plot, String releaseYear,
            String image, String season, String seasonWatched,
            String imdbRating, String userRating, String status,
            String dateConsumed, String imdbID
    ){
            Document newTVShow = new Document("name", name)
                    .append("genre", genre)
                    .append("director", director)
                    .append("writer", writer)
                    .append("plot", plot)
                    .append("releaseYear", releaseYear)
                    .append("image", image)
                    .append("season", season)
                    .append("seasonWatched", seasonWatched)
                    .append("imdbRating", imdbRating)
                    .append("userRating", userRating)
                    .append("dateConsumed", dateConsumed)
                    .append("status", status)
                    .append("imdbID", imdbID);
            return newTVShow;
    }



//    public static void main(String[] args) {
//        try {
//            MongoClient mongoClient = MongoClients.create();
//            MongoDatabase database = mongoClient.getDatabase("MyMediaManager");
//            System.out.println("Connected successfully");
//            MongoCollection<Document> collection = database.getCollection("movies");
//            FindIterable<Document> movies = collection.find();
//            for (Document movie : movies) {
//                System.out.println(movie.toJson());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
