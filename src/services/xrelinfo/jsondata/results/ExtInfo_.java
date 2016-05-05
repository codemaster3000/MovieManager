
package services.xrelinfo.jsondata.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "type",
    "id",
    "title",
    "link_href",
    "rating",
    "num_ratings",
    "uris"
})
public class ExtInfo_ {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("link_href")
    private String linkHref;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("num_ratings")
    private Integer numRatings;
    @JsonProperty("uris")
    private List<String> uris = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

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
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
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
     *     The rating
     */
    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
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
     *     The uris
     */
    @JsonProperty("uris")
    public List<String> getUris() {
        return uris;
    }

    /**
     * 
     * @param uris
     *     The uris
     */
    @JsonProperty("uris")
    public void setUris(List<String> uris) {
        this.uris = uris;
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
