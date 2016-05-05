
package services.xrelinfo.jsondata;

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
    "time",
    "group_name",
    "size",
    "video_type",
    "audio_type",
    "num_ratings",
    "ext_info",
    "comments",
    "flags",
    "proof_url",
    "tv_season",
    "tv_episode"
})
public class List {

    @JsonProperty("id")
    private String id;
    @JsonProperty("dirname")
    private String dirname;
    @JsonProperty("link_href")
    private String linkHref;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("size")
    private Size size;
    @JsonProperty("video_type")
    private String videoType;
    @JsonProperty("audio_type")
    private String audioType;
    @JsonProperty("num_ratings")
    private Integer numRatings;
    @JsonProperty("ext_info")
    private ExtInfo extInfo;
    @JsonProperty("comments")
    private Integer comments;
    @JsonProperty("flags")
    private Flags flags;
    @JsonProperty("proof_url")
    private String proofUrl;
    @JsonProperty("tv_season")
    private Integer tvSeason;
    @JsonProperty("tv_episode")
    private Integer tvEpisode;
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
     *     The time
     */
    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The groupName
     */
    @JsonProperty("group_name")
    public String getGroupName() {
        return groupName;
    }

    /**
     * 
     * @param groupName
     *     The group_name
     */
    @JsonProperty("group_name")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 
     * @return
     *     The size
     */
    @JsonProperty("size")
    public Size getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    @JsonProperty("size")
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The videoType
     */
    @JsonProperty("video_type")
    public String getVideoType() {
        return videoType;
    }

    /**
     * 
     * @param videoType
     *     The video_type
     */
    @JsonProperty("video_type")
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    /**
     * 
     * @return
     *     The audioType
     */
    @JsonProperty("audio_type")
    public String getAudioType() {
        return audioType;
    }

    /**
     * 
     * @param audioType
     *     The audio_type
     */
    @JsonProperty("audio_type")
    public void setAudioType(String audioType) {
        this.audioType = audioType;
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
    public ExtInfo getExtInfo() {
        return extInfo;
    }

    /**
     * 
     * @param extInfo
     *     The ext_info
     */
    @JsonProperty("ext_info")
    public void setExtInfo(ExtInfo extInfo) {
        this.extInfo = extInfo;
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

    /**
     * 
     * @return
     *     The flags
     */
    @JsonProperty("flags")
    public Flags getFlags() {
        return flags;
    }

    /**
     * 
     * @param flags
     *     The flags
     */
    @JsonProperty("flags")
    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    /**
     * 
     * @return
     *     The proofUrl
     */
    @JsonProperty("proof_url")
    public String getProofUrl() {
        return proofUrl;
    }

    /**
     * 
     * @param proofUrl
     *     The proof_url
     */
    @JsonProperty("proof_url")
    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
