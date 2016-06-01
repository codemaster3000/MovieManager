
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
    "meta_cat",
    "sub_cat",
    "id"
})
public class Category {

    @JsonProperty("meta_cat")
    private String metaCat;
    @JsonProperty("sub_cat")
    private String subCat;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The metaCat
     */
    @JsonProperty("meta_cat")
    public String getMetaCat() {
        return metaCat;
    }

    /**
     * 
     * @param metaCat
     *     The meta_cat
     */
    @JsonProperty("meta_cat")
    public void setMetaCat(String metaCat) {
        this.metaCat = metaCat;
    }

    /**
     * 
     * @return
     *     The subCat
     */
    @JsonProperty("sub_cat")
    public String getSubCat() {
        return subCat;
    }

    /**
     * 
     * @param subCat
     *     The sub_cat
     */
    @JsonProperty("sub_cat")
    public void setSubCat(String subCat) {
        this.subCat = subCat;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
