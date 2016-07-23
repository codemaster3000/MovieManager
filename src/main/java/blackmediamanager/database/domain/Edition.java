package blackmediamanager.database.domain;
// Generated 03.06.2016 20:33:52 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Edition generated by hbm2java
 */
public class Edition  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set movieHasEditions = new HashSet(0);

    public Edition() {
    }

    public Edition(String name, Set movieHasEditions) {
       this.name = name;
       this.movieHasEditions = movieHasEditions;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getMovieHasEditions() {
        return this.movieHasEditions;
    }
    
    public void setMovieHasEditions(Set movieHasEditions) {
        this.movieHasEditions = movieHasEditions;
    }




}

