package blackmediamanager.database.domain;
// Generated 03.06.2016 20:33:52 by Hibernate Tools 4.3.1



/**
 * TvshowHasSeason generated by hbm2java
 */
public class TvshowHasSeason  implements java.io.Serializable {


     private Integer id;
     private Season season;
     private Tvshow tvshow;

    public TvshowHasSeason() {
    }

    public TvshowHasSeason(Season season, Tvshow tvshow) {
       this.season = season;
       this.tvshow = tvshow;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Season getSeason() {
        return this.season;
    }
    
    public void setSeason(Season season) {
        this.season = season;
    }
    public Tvshow getTvshow() {
        return this.tvshow;
    }
    
    public void setTvshow(Tvshow tvshow) {
        this.tvshow = tvshow;
    }




}


