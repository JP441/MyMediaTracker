package org.jp441.mymediatracker;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
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


    public void setDatabase(String dataBase){
        mongoDatabase = mongoClient.getDatabase(dataBase);
    }
    public boolean checkIfUserNameIsTaken(String userName){
        userName = userName.strip();
        Document userToFind = new Document("userName", userName);
        MongoIterable<Document> iterDoc = getUserCollection().find(userToFind);
            if(iterDoc.iterator().hasNext()){
                return true;
            }
        return false;
    }

    public void addUser(String userName){
        userName = userName.strip();
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

    public Document findUser(String userName){
        userName = userName.strip();
        Document user = new Document("userName", userName);
        MongoIterable<Document> iterDoc = getUserCollection().find(user);
        if(iterDoc.iterator().hasNext()){
            for(Document u: iterDoc){
                return u;
            }
        }
        return null;
    }


    public void appendDocument(String userName, Document newDocument, String docType){
        Bson filter = Filters.eq("userName", userName);
        Bson update = Updates.push(docType, newDocument);
        Document result = getUserCollection().findOneAndUpdate(filter, update);
    }

    public Document createMovie(
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

    public Document createTVShow(
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
}
