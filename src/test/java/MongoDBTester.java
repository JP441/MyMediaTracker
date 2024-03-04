import org.bson.Document;
import org.jp441.mymediatracker.MongoDB;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class MongoDBTester {

    private static MongoDB mongoDB;
    @BeforeClass
    public static void setUpDatabaseConnection() {
        mongoDB = MongoDB.getMongoDB();
        mongoDB.setDatabase("MyMediaManagerTester");
    }

    @Before
    public void setUp(){
        mongoDB.addUser("dummyUser");
    }

    //Result should return true
    @Test
    public void checkIfUserNameIsTakenTrueTest(){
        mongoDB.addUser("Terry441");
        assertTrue(mongoDB.checkIfUserNameIsTaken("Terry441"));
    }

    //Result should return false
    @Test
    public void checkIfUserNameIsTakenFalseTest(){
        assertFalse(mongoDB.checkIfUserNameIsTaken("Bob23"));
    }

    @Test
    public void AddUserToDatabase(){
    mongoDB.addUser("Tony241");
    Document user = mongoDB.findUser("Tony241");
    assertEquals("Tony241", user.get("userName"));
    }

    @Test
    public void nullReturnedIfUserNotFound(){
        Document user = mongoDB.findUser("noUser");
        assertNull(user);
    }


    @Test
    public void movieAddedToUser(){
        mongoDB.appendDocument("dummyUser", createDummyMovie(), "movies");
        Document user = mongoDB.findUser("dummyUser");
        List<Document> movies = user.getList("movies", Document.class);
        for(Document d: movies){
            assertEquals("Dummy Movie", d.get("name"));
        }
    }

    @Test
    public void tvShowAddedToUser(){
        mongoDB.appendDocument("dummyUser", createDummyTVShow(), "tvShows");
        Document user = mongoDB.findUser("dummyUser");
        List<Document> tvshows = user.getList("tvShows", Document.class);
        for(Document d: tvshows){
            assertEquals("Dummy TV Show", d.get("name"));
        }
    }

    @Test
    public void iGDBAuthCollectionIsNull(){
        mongoDB.getIGDBAuthCollention().drop();
        mongoDB.getIGDBAuthToken();
        assertNotNull(mongoDB.getIGDBAuthCollention().find().first());
    }

    public Document createDummyMovie(){
        String name = "Dummy Movie";
        ArrayList<String> genre = new ArrayList<>();
        genre.add("Comedy");
        genre.add("Drama");
        ArrayList<String> director = new ArrayList<>();
        director.add("John Doe");
        ArrayList<String> writer = new ArrayList<>();
        writer.add("Jane Smith");
        String plot = "This is a dummy plot for a dummy movie.";
        String releaseYear = "2021";
        String image = "http://dummyimage.com/movie.jpg";
        String imdbRating = "7.5";
        String userRating = "8";
        String status = "Complete";
        String dateConsumed = "2022-01-01";
        String imdbID = "tt1234567";
        Document movieDocument = mongoDB.createMovie(
                name, genre, director, writer, plot,
                releaseYear, image, imdbRating, userRating,
                status, dateConsumed, imdbID);
        return movieDocument;
    }

    public Document createDummyTVShow(){
        String name = "Dummy TV Show";
        ArrayList<String> genre = new ArrayList<>();
        genre.add("Drama");
        genre.add("Thriller");
        ArrayList<String> director = new ArrayList<>();
        director.add("Jane Doe");
        ArrayList<String> writer = new ArrayList<>();
        writer.add("John Smith");
        String plot = "This is a dummy plot for a dummy TV show.";
        String releaseYear = "2020";
        String image = "http://dummyimage.com/tvshow.jpg";
        String season = "1";
        String seasonWatched = "1";
        String imdbRating = "8.0";
        String userRating = "9";
        String status = "Completed";
        String dateConsumed = "2021-05-01";
        String imdbID = "tt0987654";
        Document tvShowDocument = mongoDB.createTVShow(
                name, genre, director, writer,
                plot, releaseYear, image, season,
                seasonWatched, imdbRating, userRating, status,
                dateConsumed, imdbID
        );
        return tvShowDocument;
    }

    @After
    public void tearDown(){
        mongoDB.getUserCollection().drop();
    }
}
