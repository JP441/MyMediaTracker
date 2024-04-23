//This class converts Json data into a Document, which is then used to insert data into MongoDB
package org.jp441.mymediatracker.mappers;

import org.bson.Document;
import org.jp441.mymediatracker.Movie;

public class MovieToDocMapper {
    public Document createMovieDoc(Movie movie){
        Document newMovie = new Document("imdbID", movie.getImdbID())
                .append("name", movie.getName())
                .append("cover", movie.getCover())
                .append("ageRating", movie.getAgeRating())
                .append("fullReleaseDate", movie.getFullReleaseDate())
                .append("runtime", movie.getRuntime())
                .append("genres", movie.getGenres())
                .append("directors", movie.getDirectors())
                .append("writers", movie.getWriters())
                .append("actors", movie.getActors())
                .append("plot", movie.getPlot())
                .append("imdbRating", movie.getImdbID())
                .append("metascore", movie.getMetascore())
                .append("userRating", movie.getUserRating())
                .append("status", movie.getStatus())
                .append("dateConsumed", movie.getDateConsumed());
        return newMovie;
    }
}

