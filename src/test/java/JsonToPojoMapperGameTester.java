import org.jp441.mymediatracker.Game;
import org.jp441.mymediatracker.IGDBHandler;
import org.jp441.mymediatracker.mappers.JsonToPojoMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonToPojoMapperGameTester {

    private static IGDBHandler igdb;
    private static JSONArray gameDataArray;
    private static JsonToPojoMapper jsonToPojoMapper;
    @BeforeClass
    public static void setUpMockData(){
        jsonToPojoMapper = new JsonToPojoMapper();
        String pathToMockIGDBData = "src/main/resources/org/jp441/mymediatracker/mockIGDBJSONData";
        gameDataArray = new JSONArray(getMockData(pathToMockIGDBData));
        igdb = new IGDBHandler();
    }

    @Test
    public void gameObjCreatedWithCorrectID(){
        Game game = setupFullGame();
        int id = game.getId();
        assertEquals(113112, id);
    }

    @Test
    public void gameObjCreatedWithCorrectName(){
        Game game = setupFullGame();
        String name = game.getName();
        assertEquals("Hades", name);
    }

    @Test
    public void gameObjCreatedWithCorrectGenres(){
        Game game = setupFullGame();
        String[] expectedGenres = {
        "Role-playing (RPG)", "Hack and slash/Beat \u0027em up",
        "Adventure", "Indie"
    };
        ArrayList<String> gameObjGenres = game.getGenres();
        for(int i = 0; i < gameObjGenres.size(); i++){
            assertEquals(expectedGenres[i], gameObjGenres.get(i));
        }
    }

    @Test
    public void gameObjCreatedWithMissingGenres(){
    Game game = setUpGameMissingGenresAndPlatformsAttributes();
    ArrayList<String> emptyGenres = game.getGenres();
    assertTrue(emptyGenres.isEmpty());
    }

    @Test
    public void gameObjCreatedWithCorrectCover(){
        Game game = setupFullGame();
        String expectedCover = "//images.igdb.com/igdb/image/upload/t_thumb/co39vc.jpg";
        String gameCover = game.getCover();
        assertEquals(expectedCover, gameCover);
    }

    @Test
    public void gameObjCreatedWithMissingCover(){
        Game game = setUpGameMissingCoverAndFirstReleaseDate();
        String expectedCover = "//images.igdb.com/igdb/image/upload/t_cover_big/nocover.png";
        String gameCover = game.getCover();
        assertEquals(expectedCover, gameCover);
    }

    @Test
    public void gameObjCreatedWithCorrectPlatforms(){
        Game game = setupFullGame();
        String[] expectedPlatforms = {
                "PC (Microsoft Windows)", "Mac", "PlayStation 4", "Xbox One", "Nintendo Switch",
                "PlayStation 5", "Xbox Series X|S"
        };
        ArrayList<String> gameObjPlatforms = game.getPlatforms();
        for(int i = 0; i < gameObjPlatforms.size(); i++){
            assertEquals(expectedPlatforms[i], gameObjPlatforms.get(i));
        }
    }

    @Test
    public void gameObjCreatedWithMissingPlatforms(){
        Game game = setUpGameMissingGenresAndPlatformsAttributes();
        ArrayList<String> emptyPlatforms = game.getPlatforms();
        assertTrue(emptyPlatforms.isEmpty());
    }

    @Test
    public void gameObjCreatedWithCorrectFirstReleaseDate(){
        Game game = setupFullGame();
        long firstReleaseDate = game.getFirstReleaseDate();
        assertEquals(1544140800, firstReleaseDate);
    }

    @Test
    public void gameObjCreatedWithMissingFirstReleaseDate(){
        Game game = setUpGameMissingCoverAndFirstReleaseDate();
        long firstReleaseDate = game.getFirstReleaseDate();
        assertEquals(-1000000000, firstReleaseDate);
    }

    @Test
    public void gameObjCreatedWithCorrectIGDBRating(){
        Game game = setupFullGame();
        long igdbRating = game.getIgdbRating();
        assertEquals(92, igdbRating);
    }

    //If an IGDB rating is missing from an api call,
    //then a game OBJ should be created with an IGDB Rating of 0
    @Test
    public void gameObjCreatedWithMissingIGDBRating(){
        Game game = setUpGameMissingRatingAttribute();
        long igdbRating = game.getIgdbRating();
        assertEquals(0, igdbRating);
    }

    @Test
    public void gameObjCreatedWithCorrectSummary(){
        Game game = setupFullGame();
        String pathToGameSummary = "src/main/resources/org/jp441/mymediatracker/mockGameSummaryData";
        String expectedSummary = getMockData(pathToGameSummary);
        String gameObjSummary = game.getSummary();
        assertEquals(expectedSummary, gameObjSummary);
    }

    @Test
    public void gameObjCreatedWithMissingSummary(){
        Game game = setupGameMissingSummary();
        String summary = game.getSummary();
        assertEquals("No summary", summary);
    }


    private static String getMockData(String path){
        try{
            String mockGame = new Scanner(new File(path)).useDelimiter("\\Z").next();
            return mockGame;
        }catch(FileNotFoundException e){
            System.out.println("could not find file");
            return null;
        }
    }

    //This game will have all attributes filled, so there should be no errors in creation.
    private Game setupFullGame(){
        JSONObject gameJson =  igdb.getSpecificGame(113112, gameDataArray);
        return jsonToPojoMapper.createGame(gameJson);
    }

    //This game will not have the rating attribute included.
    private Game setUpGameMissingRatingAttribute(){
        JSONObject gameJson = igdb.getSpecificGame(80529, gameDataArray);
        return jsonToPojoMapper.createGame(gameJson);
    }

    //This game will not have genres and platform attributes included.
    private Game setUpGameMissingGenresAndPlatformsAttributes(){
        JSONObject gameJson = igdb.getSpecificGame(256391, gameDataArray);
        return jsonToPojoMapper.createGame(gameJson);
    }

    //This game will not have cover and FirstReleaseDate attributes included.
    private Game setUpGameMissingCoverAndFirstReleaseDate(){
        JSONObject gameJson = igdb.getSpecificGame( 246312, gameDataArray);
        return jsonToPojoMapper.createGame(gameJson);
    }

    //This game will not have a summary attribute included.
    private Game setupGameMissingSummary(){
    JSONObject gameJson = igdb.getSpecificGame(194662, gameDataArray);
    return jsonToPojoMapper.createGame(gameJson);
    }

}
