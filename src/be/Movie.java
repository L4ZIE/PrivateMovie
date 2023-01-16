package be;

public class Movie {

        private int id = 0;
        private String name;
        private double IMDB;
        private String genre;
        private String cast;
        private String description;

        public Movie( String name, double IMDB, String genre, String cast, String description){
            this.name = name;
            this.IMDB = IMDB;
            this.genre = genre;
            this.cast = cast;
            this.description = description;
           // this.id =  -- TO DO -- make id generator for movie ID
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIMDB() {return IMDB;}

    public void setIMDB(float IMDB) {
        this.IMDB = IMDB;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
