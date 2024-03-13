import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MockDataUtils {

    public static String getMockData(String path){
        try{
            String mockData = new Scanner(new File(path)).useDelimiter("\\Z").next();
            return mockData;
        }catch(FileNotFoundException e){
            System.out.println("could not find file");
            return null;
        }
    }
}
