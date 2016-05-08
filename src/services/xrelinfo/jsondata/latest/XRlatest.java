package services.xrelinfo.jsondata.latest;

import java.util.ArrayList;
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
    "total_count",
    "pagination",
    "list"
})
public class XRlatest {

    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonProperty("pagination")
    private Pagination pagination;
    @JsonProperty("list")
    private java.util.List<services.xrelinfo.jsondata.latest.List> list = new ArrayList<services.xrelinfo.jsondata.latest.List>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The totalCount
     */
    @JsonProperty("total_count")
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 
     * @param totalCount
     *     The total_count
     */
    @JsonProperty("total_count")
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 
     * @return
     *     The pagination
     */
    @JsonProperty("pagination")
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * @param pagination
     *     The pagination
     */
    @JsonProperty("pagination")
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * 
     * @return
     *     The list
     */
    @JsonProperty("list")
    public java.util.List<services.xrelinfo.jsondata.latest.List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    @JsonProperty("list")
    public void setList(java.util.List<services.xrelinfo.jsondata.latest.List> list) {
        this.list = list;
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
