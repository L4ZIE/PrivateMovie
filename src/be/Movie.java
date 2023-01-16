package be;

public class Movie {

        private int id = 0;
        private String name;
        private double IMDB;
        private String genre;
        private String cast;
        private String description;

        private String path;


        public Movie( String name, double IMDB, String genre ,String path ,  String cast, String description){
            this.name = name;
            this.IMDB = IMDB;
            this.genre = genre;
            this.cast = cast;
            this.description = description;
           this.path = path;
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
    public String getPath(){
            return path;
    }public void setPath(String path){
            this.path = path;
    }
}
