package org.jp441.mymediatracker;

public class Game extends Media{
    private String console;
    private String developer;

    public Game(){}

    public Game(
            String name, String genre, String status,
            String console, String developer, double rating
    ){
        this.name = name;
        this.type = "Game";
        this.genre = genre;
        this.status = status;
        this.console = console;
        this.developer = developer;
        this.rating = rating;
    }

    public String getConsole(){
        return console;
    }

    public String getDeveloper(){
        return developer;
    }

    public void setConsole(String console){
        this.console = console;
    }

    public void setDeveloper(String developer){
        this.developer = developer;
    }

    @Override
    public String toString(){
        return super.toString() + "\nConsole: " + console +
                "\nDeveloper: " + developer;
    }
}
