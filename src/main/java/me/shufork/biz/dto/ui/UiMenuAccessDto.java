package me.shufork.biz.dto.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UiMenuAccessDto {
    @JsonProperty("ref")
    private String ref;

    @JsonProperty("name")
    private String name;

    @JsonProperty("route")
    private String route;

    @JsonProperty("id")
    private String id;

    @JsonProperty("parent_id")
    private String parentId;
}
