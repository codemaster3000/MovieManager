
package blackmediamanager.scrapers.xrelinfo.jsondata.result;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "dirname",
    "link_href",
    "category",
    "main_lang",
    "pub_time",
    "post_time",
    "size_mb",
    "group",
    "num_ratings",
    "ext_info",
    "tv_season",
    "tv_episode",
    "comments"
})
public class P2pResult {

    @JsonProperty("id")
    private String id;
    @JsonProperty("dirname")
    private String dirname;
    @JsonProperty("link_href")
    private String linkHref;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("main_lang")
    private String mainLang;
    @JsonProperty("pub_time")
    private Integer pubTime;
    @JsonProperty("post_time")
    private Integer postTime;
    @JsonProperty("size_mb")
    private Integer sizeMb;
    @JsonProperty("group")
    private Group group;
    @JsonProperty("num_ratings")
    private Integer numRatings;
    @JsonProperty("ext_info")
    private ExtInfo_ extInfo;
    @JsonProperty("tv_season")
    private Integer tvSeason;
    @JsonProperty("tv_episode")
    private Integer tvEpisode;
    @JsonProperty("comments")
    private Integer comments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The dirname
     */
    @JsonProperty("dirname")
    public String getDirname() {
        return dirname;
    }

    /**
     * 
     * @param dirname
     *     The dirname
     */
    @JsonProperty("dirname")
    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    /**
     * 
     * @return
     *     The linkHref
     */
    @JsonProperty("link_href")
    public String getLinkHref() {
        return linkHref;
    }

    /**
     * 
     * @param linkHref
     *     The link_href
     */
    @JsonProperty("link_href")
    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    /**
     * 
     * @return
     *     The category
     */
    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The mainLang
     */
    @JsonProperty("main_lang")
    public String getMainLang() {
        return mainLang;
    }

    /**
     * 
     * @param mainLang
     *     The main_lang
     */
    @JsonProperty("main_lang")
    public void setMainLang(String mainLang) {
        this.mainLang = mainLang;
    }

    /**
     * 
     * @return
     *     The pubTime
     */
    @JsonProperty("pub_time")
    public Integer getPubTime() {
        return pubTime;
    }

    /**
     * 
     * @param pubTime
     *     The pub_time
     */
    @JsonProperty("pub_time")
    public void setPubTime(Integer pubTime) {
        this.pubTime = pubTime;
    }

    /**
     * 
     * @return
     *     The postTime
     */
    @JsonProperty("post_time")
    public Integer getPostTime() {
        return postTime;
    }

    /**
     * 
     * @param postTime
     *     The post_time
     */
    @JsonProperty("post_time")
    public void setPostTime(Integer postTime) {
        this.postTime = postTime;
    }

    /**
     * 
     * @return
     *     The sizeMb
     */
    @JsonProperty("size_mb")
    public Integer getSizeMb() {
        return sizeMb;
    }

    /**
     * 
     * @param sizeMb
     *     The size_mb
     */
    @JsonProperty("size_mb")
    public void setSizeMb(Integer sizeMb) {
        this.sizeMb = sizeMb;
    }

    /**
     * 
     * @return
     *     The group
     */
    @JsonProperty("group")
    public Group getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    @JsonProperty("group")
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * 
     * @return
     *     The numRatings
     */
    @JsonProperty("num_ratings")
    public Integer getNumRatings() {
        return numRatings;
    }

    /**
     * 
     * @param numRatings
     *     The num_ratings
     */
    @JsonProperty("num_ratings")
    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    /**
     * 
     * @return
     *     The extInfo
     */
    @JsonProperty("ext_info")
    public ExtInfo_ getExtInfo() {
        return extInfo;
    }

    /**
     * 
     * @param extInfo
     *     The ext_info
     */
    @JsonProperty("ext_info")
    public void setExtInfo(ExtInfo_ extInfo) {
        this.extInfo = extInfo;
    }

    /**
     * 
     * @return
     *     The tvSeason
     */
    @JsonProperty("tv_season")
    public Integer getTvSeason() {
        return tvSeason;
    }

    /**
     * 
     * @param tvSeason
     *     The tv_season
     */
    @JsonProperty("tv_season")
    public void setTvSeason(Integer tvSeason) {
        this.tvSeason = tvSeason;
    }

    /**
     * 
     * @return
     *     The tvEpisode
     */
    @JsonProperty("tv_episode")
    public Integer getTvEpisode() {
        return tvEpisode;
    }

    /**
     * 
     * @param tvEpisode
     *     The tv_episode
     */
    @JsonProperty("tv_episode")
    public void setTvEpisode(Integer tvEpisode) {
        this.tvEpisode = tvEpisode;
    }

    /**
     * 
     * @return
     *     The comments
     */
    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
