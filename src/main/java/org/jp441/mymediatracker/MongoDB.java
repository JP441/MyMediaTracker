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
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.Instant;
import java.time.LocalDate;
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

    public MongoCollection<Document> getUserCollection() {return mongoDatabase.getCollection("users");}

    public MongoCollection<Document> getIGDBAuthCollention(){return mongoDatabase.getCollection("IGDBAuth");}

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
    public void appendDocumentToUser(String userName, Document newDocument, String docType){
        Bson filter = Filters.eq("userName", userName);
        Bson update = Updates.push(docType, newDocument);
        Document result = getUserCollection().findOneAndUpdate(filter, update);
    }
    public Document createIGDBAuthToken(String accessToken, long tokenExpiry){
        Document auth = new Document("accessToken", accessToken)
                .append("expiresAt", tokenExpiry);
        return auth;
    }

    //This checks that an IGDB auth Token Document exists in the database. If the Document does not exist create one
    public Document getIGDBAuthToken(){
        if(getIGDBAuthCollention().find().first() == null) {
            IGDBHandler igdb = new IGDBHandler();
            long currentTime = Instant.now().getEpochSecond();
            JSONObject json = igdb.getToken();
            String accessToken = json.getString("access_token");
            long expiresAt = json.getLong("expires_in") + currentTime;
            Document auth =  createIGDBAuthToken(accessToken, expiresAt);
            getIGDBAuthCollention().insertOne(auth);
            return auth;
        }
        Document auth = getIGDBAuthCollention().find().first();
        return auth;
    }
}
