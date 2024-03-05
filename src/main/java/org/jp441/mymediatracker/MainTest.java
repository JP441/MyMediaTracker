package org.jp441.mymediatracker;

import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        IGDBHandler igdb = new IGDBHandler();
        JSONArray array = igdb.searchGameByName("Hades");
        for(int i = 0; i < array.length(); i++){
            JSONObject json = array.getJSONObject(i);
            System.out.println(json.getString("name"));
        }
//        MongoDB mongoDB = MongoDB.getMongoDB();
//        OMDbHandler omdb = new OMDbHandler();
//
//        JSONObject json = omdb.searchByID("tt1194172");
//        String[] genreArray = json.getString("Genre").split(",\\s");
//        String[] directorArray = json.getString("Director").split(",\\s");
//        String[] writerArray = json.getString("Writer").split(",\\s");
//        ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genreArray));
//        ArrayList<String> directorList = new ArrayList<>(Arrays.asList(directorArray));
//        ArrayList<String> writerList = new ArrayList<>(Arrays.asList(writerArray));
//        String name = json.getString("Title");
//        String plot = json.getString("Plot");
//        String year = json.getString("Year");
//        String image = json.getString("Poster");
//        String imdbRating = json.getString("imdbRating");
//        String imdbID = json.getString("imdbID");
//        Document movie = mongoDB.addMovie(
//                name, genreList, directorList, writerList, plot, year, image,
//                imdbRating, "10", "Completed",
//                "2007-12", imdbID
//                );
//        mongoDB.appendDocumentToUser("lucas21", movie, "movies");
//        System.out.println(mongoDB.getUserCollection().find());
//        mongoDB.addUser("lucas21");
//        for(Document user: users){
//            System.out.println(user);
//        }
//        OMDbHandler omdb = new OMDbHandler();
//        System.out.println("Please enter what you would like to search for (Movie/Tv show");
//        Scanner input = new Scanner(System.in);
//        String type = input.nextLine();
//        System.out.println("Please enter a Title");
//        String inputName = input.nextLine();
//        JSONArray jsonArray = omdb.searchMovieAndTVByName(inputName, type);
//        JSONObject json;
//        for (int i = 0; i < jsonArray.length(); i++) {
//            json = jsonArray.getJSONObject(i);
//            System.out.println(
//                    "\ninputName: " + json.getString("Title") +
//                            "\nyear: " + json.getString("Year") + "\nimdb ID: " + json.getString("imdbID")
//
//            );
//        }
//        System.out.println("Please enter the ID of a movie you want to search for");
//        String ID = input.nextLine();
//        json = omdb.searchByID(ID);
//        String[] genreArray = json.getString("Genre").split(",\\s");
//        String[] directorArray = json.getString("Director").split(",\\s");
//        String[] writerArray = json.getString("Writer").split(",\\s");
//        ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genreArray));
//        ArrayList<String> directorList = new ArrayList<>(Arrays.asList(directorArray));
//        ArrayList<String> writerList = new ArrayList<>(Arrays.asList(writerArray));
//        String name = json.getString("Title");
//        String plot = json.getString("Plot");
//        String year = json.getString("Year");
//        String imdbID = json.getString("imdbID");
//        double rating = Double.parseDouble(json.getString("imdbRating"));
//        MongoDB.getMongoDB().addMovie(name, genreList, directorList, writerList, plot, year, rating, imdbID);
//        MongoIterable<Document> test = MongoDB.getMongoDB().getMovieCollection().find();
//        for (Document movie : test) {
//            System.out.println(movie.toJson());
//        }
//    }
    }
}