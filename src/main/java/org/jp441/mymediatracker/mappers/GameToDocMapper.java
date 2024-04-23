package org.jp441.mymediatracker.mappers;

import org.bson.Document;
import org.jp441.mymediatracker.Game;

public class GameToDocMapper {
        public Document createGameDoc(Game game){
        Document newGameDoc = new Document("id", game.getIgdbID())
                .append("name", game.getName())
                .append("genres", game.getGenres())
                .append("cover", game.getCover())
                .append("platforms", game.getPlatforms())
                .append("firstReleaseDate", game.getFirstReleaseDate())
                .append("igdbRating", game.getIgdbRating())
                .append("summary", game.getSummary())
                .append("userRating", game.getUserRating())
                .append("status", game.getStatus())
                .append("dateConsumed", game.getDateConsumed());
        return newGameDoc;
    }
}
