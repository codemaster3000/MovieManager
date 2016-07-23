package blackmediamanager.database.domain;
// Generated 03.06.2016 20:33:52 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Genre generated by hbm2java
 */
public class Genre  implements java.io.Serializable {


     private Integer id;
     private String type;
     private Set tvshowHasGenres = new HashSet(0);
     private Set movieHasGenres = new HashSet(0);

    public Genre() {
    }

    public Genre(String type, Set tvshowHasGenres, Set movieHasGenres) {
       this.type = type;
       this.tvshowHasGenres = tvshowHasGenres;
       this.movieHasGenres = movieHasGenres;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public Set getTvshowHasGenres() {
        return this.tvshowHasGenres;
    }
    
    public void setTvshowHasGenres(Set tvshowHasGenres) {
        this.tvshowHasGenres = tvshowHasGenres;
    }
    public Set getMovieHasGenres() {
        return this.movieHasGenres;
    }
    
    public void setMovieHasGenres(Set movieHasGenres) {
        this.movieHasGenres = movieHasGenres;
    }




}


