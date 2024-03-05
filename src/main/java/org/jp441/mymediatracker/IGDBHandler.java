package org.jp441.mymediatracker;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import org.bson.Document;
import org.json.JSONObject;
import org.json.JSONArray;

public class IGDBHandler {
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;

    public IGDBHandler() {
    }

    public JSONObject getToken() {
        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://id.twitch.tv/oauth2/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "client_id=" + System.getenv("IGDB_CLIENT_ID") +
                                "&client_secret=" + System.getenv("IGDB_CLIENT_SECRET") +
                                "&grant_type=client_credentials"))
                .build();
        try {
            HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = tokenResponse.body();
            JSONObject json = new JSONObject(responseBody);
            return json;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    //checks that a token stored in the database has not expired. If the
    //token has expired request a new one, store it in the database
    //and return the access_token string.
    //else return the access_token string saved in the database.
    public String checkTokenIsValid(){
       Document auth =  MongoDB.getMongoDB().getIGDBAuthToken();
       long expiry = auth.getLong("expiresAt");
       if(Instant.now().getEpochSecond() >= expiry){
           MongoDB.getMongoDB().getIGDBAuthCollention().drop();
           long currentTime = Instant.now().getEpochSecond();
           JSONObject json = getToken();
           String authToken = json.getString("access_token");
           long expiresAt = json.getLong("expires_in") + currentTime;
           Document newAuth = MongoDB.getMongoDB().createIGDBAuthToken(authToken, expiresAt);
           MongoDB.getMongoDB().getIGDBAuthCollention().insertOne(newAuth);
           System.out.println("new accessToken acquired");
           return newAuth.getString("accessToken");
       }
        System.out.println("access token valid");
       return auth.getString("accessToken");
    }


    public JSONArray searchGameByName(String name) {
        String fields = "fields name, genres.name, cover.url, first_release_date, " +
                "platforms.name, platforms.platform_logo.url, rating, summary;";
        request = HttpRequest.newBuilder().uri(URI.create("https://api.igdb.com/v4/games/"))
                .header("Client-ID", System.getenv("IGDB_CLIENT_ID"))
                .header("Authorization", "Bearer " + checkTokenIsValid())
                .POST(HttpRequest.BodyPublishers.ofString("search " + "\"" + name + "\"; " + fields))
                .build();
        try {
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
            System.out.println(responseBody);
        JSONArray jsonArray = new JSONArray(responseBody);
        return jsonArray;
    } catch(IOException | InterruptedException e) {
        e.printStackTrace();
        return null;
    }
    }

    public JSONObject getSpecificGame(int id, JSONArray jsonArray){
        for(int i=0; i < jsonArray.length(); i++){
            if(jsonArray.getJSONObject(i).getInt("id") == id){
                return jsonArray.getJSONObject(i);
            }
        }
        return null;
    }

}
