package org.jp441.mymediatracker;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;
public class OMDbHandler {
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;
    private String responseBody;
    private JSONObject json;
    private JSONArray jsonArray;

    public OMDbHandler(){
    }

    //adds a + sign between the spaces so that the api can search the name correctly.
    public String formatNameQuery(String name){
        return name.replaceAll("\\s", "+");
    }

    public JSONArray searchMovieAndTVByName(String name, String type){
        String formattedName = formatNameQuery(name);
        try {
            if(type.equalsIgnoreCase("movie")){
                request = HttpRequest.newBuilder()
                        .uri(URI.create(
                                "http://www.omdbapi.com/?apikey=" + System.getenv("OMDB_AUTH_KEY")
                                    + "&s=" + formattedName + "&type=movie"
                        ))
                        .build();
            } else {
                request = HttpRequest.newBuilder()
                        .uri(URI.create(
                                "http://www.omdbapi.com/?apikey=" + System.getenv("OMDB_AUTH_KEY")
                                        + "&s=" + formattedName + "&type=series"
                        ))
                        .build();
            }
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
            json = new JSONObject(responseBody);
            if(json.has("Search")){
                jsonArray = json.getJSONArray("Search");
            } else {
                System.out.println("Could not find movie or tv show");
                return null;
            }
            return jsonArray;
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        }

    public JSONObject searchByID(String id){
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "http://www.omdbapi.com/?apikey=" + System.getenv("OMDB_AUTH_KEY")
                                    + "&i=" + id
                    ))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
            json = new JSONObject(responseBody);
            return json;
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }


}
