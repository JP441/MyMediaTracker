import org.bson.Document;
import org.jp441.mymediatracker.IGDBHandler;
import org.jp441.mymediatracker.MongoDB;
import org.jp441.mymediatracker.User;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

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
    public void setup(){
        createManyUsersHelper();
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
    public void getAllUsersFromDatabase(){
        String[] expectedUsers = {"Jack441", "Hunterx245", "Champion225"};
        ArrayList<User> users = mongoDB.getAllUsers();
        for(int i = 0; i < users.size(); i++){
            assertEquals(expectedUsers[i], users.get(i).getUsername());
        }
    }

    @Test
    public void addUserToDatabase(){
    mongoDB.addUser("Tony241");
    User user = mongoDB.getUserByName("Tony241");
    assertEquals("Tony241", user.getUsername());
    }

    @Test
    public void nullReturnedIfUserNotFound(){
        User user = mongoDB.getUserByName("noUser");
        assertNull(user);
    }

    @Test
    public void removeUserFromDatabase(){
        mongoDB.removeUser("Hunterx245");
        ArrayList<User> users = mongoDB.getAllUsers();
        for(User user: users){
            assertNotEquals("Hunterx245", user.getUsername());
        }
    }


//    @Test
//    public void iGDBAuthCollectionIsNull(){
//        mongoDB.getIGDBAuthCollention().drop();
//        mongoDB.getIGDBAuthToken();
//        assertNotNull(mongoDB.getIGDBAuthCollention().find().first());
//    }

    @After
    public void tearDown(){
        mongoDB.getUserCollection().drop();
    }

    private void createManyUsersHelper(){
        mongoDB.addUser("Jack441");
        mongoDB.addUser("Hunterx245");
        mongoDB.addUser("Champion225");
    }
}
