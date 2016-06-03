package blackmediamanager.database.domain;
// Generated 03.06.2016 19:51:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Audioline generated by hbm2java
 */
public class Audioline  implements java.io.Serializable {


     private Integer id;
     private String audioLanguage;
     private Integer audioBitrate;
     private String audioFormat;
     private String audioChannels;
     private Boolean dtsMod;
     private Set seasonHasAudiolines = new HashSet(0);
     private Set movieHasAudiolines = new HashSet(0);

    public Audioline() {
    }

    public Audioline(String audioLanguage, Integer audioBitrate, String audioFormat, String audioChannels, Boolean dtsMod, Set seasonHasAudiolines, Set movieHasAudiolines) {
       this.audioLanguage = audioLanguage;
       this.audioBitrate = audioBitrate;
       this.audioFormat = audioFormat;
       this.audioChannels = audioChannels;
       this.dtsMod = dtsMod;
       this.seasonHasAudiolines = seasonHasAudiolines;
       this.movieHasAudiolines = movieHasAudiolines;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAudioLanguage() {
        return this.audioLanguage;
    }
    
    public void setAudioLanguage(String audioLanguage) {
        this.audioLanguage = audioLanguage;
    }
    public Integer getAudioBitrate() {
        return this.audioBitrate;
    }
    
    public void setAudioBitrate(Integer audioBitrate) {
        this.audioBitrate = audioBitrate;
    }
    public String getAudioFormat() {
        return this.audioFormat;
    }
    
    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }
    public String getAudioChannels() {
        return this.audioChannels;
    }
    
    public void setAudioChannels(String audioChannels) {
        this.audioChannels = audioChannels;
    }
    public Boolean getDtsMod() {
        return this.dtsMod;
    }
    
    public void setDtsMod(Boolean dtsMod) {
        this.dtsMod = dtsMod;
    }
    public Set getSeasonHasAudiolines() {
        return this.seasonHasAudiolines;
    }
    
    public void setSeasonHasAudiolines(Set seasonHasAudiolines) {
        this.seasonHasAudiolines = seasonHasAudiolines;
    }
    public Set getMovieHasAudiolines() {
        return this.movieHasAudiolines;
    }
    
    public void setMovieHasAudiolines(Set movieHasAudiolines) {
        this.movieHasAudiolines = movieHasAudiolines;
    }




}


