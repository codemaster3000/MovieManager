package database.domain;
// Generated 29.04.2016 17:03:11 by Hibernate Tools 4.3.1



/**
 * Audioline generated by hbm2java
 */
public class Audioline  implements java.io.Serializable {


     private Integer id;
     private Audiolinepos audiolinepos;
     private Movie movie;

    public Audioline() {
    }

    public Audioline(Audiolinepos audiolinepos, Movie movie) {
       this.audiolinepos = audiolinepos;
       this.movie = movie;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Audiolinepos getAudiolinepos() {
        return this.audiolinepos;
    }
    
    public void setAudiolinepos(Audiolinepos audiolinepos) {
        this.audiolinepos = audiolinepos;
    }
    public Movie getMovie() {
        return this.movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }




}


