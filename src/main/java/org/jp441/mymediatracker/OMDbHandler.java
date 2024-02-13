package org.jp441.mymediatracker;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;
public class OMDbHandler {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String responseBody;
    private JSONObject json;
    private Dotenv dotenv;

    public OMDbHandler(){
    }


    //adds a + sign between the spaces so that the api can search the name correctly.
    public String formatNameQuery(String name){
        return name.replaceAll("\\s", "+");
    }

    public JSONObject searchMovieByYear(String name, String year){
        String formattedName = formatNameQuery(name);
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "http://www.omdbapi.com/?apikey=" + System.getenv("OMDB_AUTH_KEY") + "&t="
                                    + formattedName + "&y=" + year + "&type=movie"
                    ))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
            json = new JSONObject(responseBody);
            System.out.println(json.get("Title"));
            return json;
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
            return null;
        }


    }

}
