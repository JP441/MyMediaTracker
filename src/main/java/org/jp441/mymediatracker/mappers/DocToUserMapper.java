package org.jp441.mymediatracker.mappers;

import org.bson.Document;
import org.jp441.mymediatracker.*;

import java.util.ArrayList;

public class DocToUserMapper {
    public User createUserObj(Document doc){
        User user = User.builder()
                .username(doc.getString("username"))
                .itemsAdded(doc.getInteger("itemsAdded"))
                .movies(new ArrayList<>(doc.getList("movies", Movie.class)))
                .tvShows(new ArrayList<>(doc.getList("tvShows", TVShow.class)))
                .games(new ArrayList<>(doc.getList("games", Game.class)))
                .books(new ArrayList<>(doc.getList("books", Book.class)))
                .comics(new ArrayList<>(doc.getList("comics", Comic.class)))
                .build();
        return user;
    }
}
