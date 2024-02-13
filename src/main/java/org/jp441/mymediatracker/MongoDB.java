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

    public void addMovie(
            String name, ArrayList<String> genre, ArrayList<String>director,
            ArrayList<String> writer, String plot, String year,
            double rating, String imdbID
    ){
        Document newMovie = new Document("name", name)
                .append("genre", genre)
                .append("director", director)
                .append("writer", writer)
                .append("Plot", plot)
                .append("year", year)
                .append("imdbRating", rating)
                .append("imdbID", imdbID);
        getMovieCollection().insertOne(newMovie);
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
