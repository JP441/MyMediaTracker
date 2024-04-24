package org.jp441.mymediatracker;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jp441.mymediatracker.mappers.DocToUserMapper;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB implements UserRepository {

    private static MongoDB mongoDB;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private DocToUserMapper docToUserMapper = new DocToUserMapper();

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

    public void setDatabase(String dataBase){
        mongoDatabase = mongoClient.getDatabase(dataBase);
    }

    public MongoCollection<Document> getUserCollection() {return mongoDatabase.getCollection("users");}

    @Override
    public ArrayList<User> getAllUsers(){
        FindIterable<Document> userDocs = getUserCollection().find();
        ArrayList<User> users = new ArrayList<>();
        for(Document doc: userDocs){
            users.add(docToUserMapper.createUserObj(doc));
        }
        return users;
    }

    @Override
    public User getUserByName(String username){
        username = username.strip();
        Bson query = eq("username", username);
        MongoIterable<Document> iterDoc = getUserCollection().find(query);
        if(iterDoc.iterator().hasNext()){
            for(Document u: iterDoc){
                return docToUserMapper.createUserObj(u);
            }
        }
        return null;
    }

    @Override
    public void addUser(String username){
        username = username.strip();
        ArrayList<Document> movies = new ArrayList<>();
        ArrayList<Document> tvShows = new ArrayList<>();
        ArrayList<Document> games = new ArrayList<>();
        ArrayList<Document> books = new ArrayList<>();
        ArrayList<Document> comics = new ArrayList<>();
        Document newUser = new Document("username", username)
                .append("itemsAdded", 0)
                .append("movies", movies)
                .append("tvShows", tvShows)
                .append("games", games)
                .append("books", books)
                .append("comics", comics);
        getUserCollection().insertOne(newUser);
    }

    @Override
    public void removeUser(String username){
        Bson query = eq("username", username);
        getUserCollection().deleteOne(query);
    }

    public MongoCollection<Document> getIGDBAuthCollention(){return mongoDatabase.getCollection("IGDBAuth");}



    public boolean checkIfUserNameIsTaken(String username){
        username = username.strip();
        Bson query = eq("username", username);
        MongoIterable<Document> iterDoc = getUserCollection().find(query);
            if(iterDoc.iterator().hasNext()){
                return true;
            }
        return false;
    }





    public void appendDocumentToUser(String userName, Document newDocument, String docType){
        Bson filter = eq("userName", userName);
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
