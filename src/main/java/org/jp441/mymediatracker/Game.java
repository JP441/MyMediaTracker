package org.jp441.mymediatracker;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;


@Data
@Builder
public class Game{
    private String mongoID;

    private int igdbID;

    private String name;

    private ArrayList<String> genres;

    private String cover;

    private ArrayList<String> platforms;

    private long firstReleaseDate;

    private long igdbRating;

    private String summary;

    private double userRating;

    private String status;

    private LocalDate dateConsumed;



}
