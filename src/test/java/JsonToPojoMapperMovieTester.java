import org.jp441.mymediatracker.Movie;
import org.jp441.mymediatracker.mappers.JsonToPojoMapper;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JsonToPojoMapperMovieTester {
    private static Movie movie;

    @BeforeClass
    public static void setUpMockData(){
        JsonToPojoMapper jsonToPojoMapper = new JsonToPojoMapper();
        String path = "src/main/resources/org/jp441/mymediatracker/mockOMDBJSONData";
        JSONObject jsonObject = new JSONObject(MockDataUtils.getMockData(path));
        movie = jsonToPojoMapper.createFullMovie(jsonObject);
    }

    @Test
    public void movieObjCreatedWithCorrectIMDBID(){
        String expectedID = "tt0304141";
        String actualID = movie.getImdbID();
        assertEquals(expectedID, actualID);
    }

    @Test
    public void movieObjCreatedWithCorrectName(){
        String expectedName = "Harry Potter and the Prisoner of Azkaban";
        String actualName = movie.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void movieObjCreatedWithCorrectCover(){
        String expectedMovieCover = "https://m.media-amazon.com/images/M/" +
                "MV5BMTY4NTIwODg0N15BMl5BanBnXkFtZTcwOTc0MjEzMw@@._V1_SX300.jpg";
        String actualMovieCover = movie.getCover();
        assertEquals(expectedMovieCover, actualMovieCover);
    }

    @Test
    public void movieObjCreatedWithCorrectReleaseYear(){
        String expectedYear = "2004";
        String actualYear = movie.getReleaseYear();
        assertEquals(expectedYear, actualYear);
    }

    @Test
    public void movieObjCreatedWithCorrectAgeRating(){
        String expectedAgeRating = "PG";
        String actualAgeRating = movie.getAgeRating();
        assertEquals(expectedAgeRating, actualAgeRating);
    }

    @Test
    public void movieObjCreatedWithCorrectReleaseDate(){
        LocalDate expectedDate = LocalDate.of(2004, 6, 4);
        LocalDate actualDate = movie.getFullReleaseDate();
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void movieObjCreatedWithCorrectRunTime(){
        String expectedRuntime = "142 min";
        String actualRuntime = movie.getRuntime();
        assertEquals(expectedRuntime, actualRuntime);
    }

    @Test
    public void movieObjCreatedWithCorrectGenres(){
        String[] expectedGenres = {"Adventure", "Family", "Fantasy"};
        ArrayList<String> actualGenres = movie.getGenres();
        compareListsForEquality(expectedGenres, actualGenres);
    }

    private void compareListsForEquality(String[]expectedItems, ArrayList<String>actualItems){
        assertEquals(expectedItems.length, actualItems.size());
        for (int i = 0; i < actualItems.size(); i++) {
            assertEquals(expectedItems[i], actualItems.get(i));
        }
    }


}
