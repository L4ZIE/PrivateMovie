package be;



import java.time.LocalDate;
import java.util.Date;

public class Movie {

        private int id = 0;
        private String name;
        private double IMDB;
        private String genre;
        private String cast;
        private String description;

        private String path;

        private String personalRating;

        private Date lastView;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(String personalRating) {
        this.personalRating = personalRating;
    }

    public Movie(int movieID, String name, double IMDB, String genre , String path , String cast, String description, String personalRating, Date lastView){
            this.name = name;
            this.IMDB = IMDB;
            this.genre = genre;
            this.cast = cast;
            this.description = description;
           this.path = path;
            this.id = movieID;
            this.personalRating=personalRating;
            this.lastView = lastView;
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

    public void setLastView(Date newViewDate){this.lastView = newViewDate;}
    public Date getLastView(){return lastView;}

}
