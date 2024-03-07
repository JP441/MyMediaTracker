import org.bson.Document;
import org.jp441.mymediatracker.IGDBHandler;
import org.jp441.mymediatracker.MongoDB;
import org.json.JSONArray;
import org.junit.*;
import static org.junit.Assert.*;


public class MongoDBTester {

    private static MongoDB mongoDB;
    private static JSONArray gameSearchArray;
    private static IGDBHandler igdb;
    private static Document gameDoc;

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


//    @Test
//    public void movieAddedToUser(){
//        mongoDB.appendDocumentToUser("dummyUser", createDummyMovie(), "movies");
//        Document user = mongoDB.findUser("dummyUser");
//        List<Document> movies = user.getList("movies", Document.class);
//        for(Document d: movies){
//            assertEquals("Dummy Movie", d.get("name"));
//        }
//    }

//    @Test
//    public void tvShowAddedToUser(){
//        mongoDB.appendDocumentToUser("dummyUser", createDummyTVShow(), "tvShows");
//        Document user = mongoDB.findUser("dummyUser");
//        List<Document> tvshows = user.getList("tvShows", Document.class);
//        for(Document d: tvshows){
//            assertEquals("Dummy TV Show", d.get("name"));
//        }
//    }

    @Test
    public void iGDBAuthCollectionIsNull(){
        mongoDB.getIGDBAuthCollention().drop();
        mongoDB.getIGDBAuthToken();
        assertNotNull(mongoDB.getIGDBAuthCollention().find().first());
    }

    @After
    public void tearDown(){
        mongoDB.getUserCollection().drop();
    }
}
