import org.jp441.mymediatracker.IGDBHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
public class IGDBHandlerTester {
    static IGDBHandler igdb;
    @Before
    public void setupIGDB(){
        igdb = new IGDBHandler();
    }

    @Test
    public void getASpecificGame(){
        JSONArray gameArray = igdb.searchGameByName("Hades");
        JSONObject game = igdb.getSpecificGame(113112, gameArray);
        assertEquals(113112, game.get("id"));
    }

    @Test
    public void returnNullIfNoGameFound(){
        JSONArray gameArray = igdb.searchGameByName("Nonexistent Game85489484");
        JSONObject game = igdb.getSpecificGame(99999934, gameArray);
        assertNull(game);
    }


}
