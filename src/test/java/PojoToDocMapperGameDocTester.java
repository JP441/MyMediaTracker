//import org.bson.Document;
//import org.jp441.mymediatracker.IGDBHandler;
//import org.jp441.mymediatracker.mappers.PojoToDocMapper;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Scanner;
//import static org.junit.Assert.assertEquals;
//
//public class PojoToDocMapperGameDocTester {
//
//    private static Document gameDoc;
//
//    //This is used to retrieve mock json data which we will use throughout our application.
//    @BeforeClass
//    public static void setUpMockData(){
//        String pathToMockIGDBData = "src/main/resources/org/jp441/mymediatracker/mockIGDBJSONData";
//        JSONArray gameDataArray = new JSONArray(getMockData(pathToMockIGDBData));
//        IGDBHandler igdb = new IGDBHandler();
//        JSONObject specificGame = igdb.getSpecificGame(113112, gameDataArray);
//        PojoToDocMapper pojoToDocMapper = new PojoToDocMapper();
//        LocalDate dateMediaConsumed = LocalDate.parse("2024-01-22");
//        gameDoc = pojoToDocMapper.createGame(specificGame, 9.5, "Completed", dateMediaConsumed);
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectID(){
//        int id = gameDoc.getInteger("id");
//        assertEquals(113112, id);
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectName() {
//        String gameName = gameDoc.getString("name");
//        Assert.assertEquals("Hades", gameName);
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectGenres(){
//        String[] expectedGenres = {
//                "Role-playing (RPG)", "Hack and slash/Beat \u0027em up",
//                "Adventure", "Indie"
//        };
//        List<String> gameDocGenres = gameDoc.getList("genres", String.class);
//        for(int i = 0; i < gameDocGenres.size(); i++){
//            assertEquals(expectedGenres[i], gameDocGenres.get(i));
//        }
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectCover(){
//        String url = gameDoc.getString("cover");
//        assertEquals("//images.igdb.com/igdb/image/upload/t_thumb/co39vc.jpg", url);
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectPlatforms(){
//        String[] expectedPlatforms = {
//                "PC (Microsoft Windows)", "Mac", "PlayStation 4", "Xbox One", "Nintendo Switch",
//                "PlayStation 5", "Xbox Series X|S"
//        };
//        List<String> gameDocPlatforms = gameDoc.getList("platforms", String.class);
//        for(int i = 0; i < expectedPlatforms.length; i++){
//            assertEquals(expectedPlatforms[i], gameDocPlatforms.get(i));
//        }
//    }
//
//    @Test
//    public void gameDocCreatedWithCorrectSummary(){
//        String pathToGameSummary = "src/main/resources/org/jp441/mymediatracker/mockGameSummaryData";
//        String expectedSummary = getMockData(pathToGameSummary);
//        assertEquals(expectedSummary, gameDoc.getString("summary"));
//    }
//
////    @Test
////    public void gameDocCreatedWithCorrectIGDBRating(){
////        double expectedRating = 9.5;
////        assertEquals((Double) expectedRating, gameDoc.getDouble("userRating"));
////    }
//
//}
