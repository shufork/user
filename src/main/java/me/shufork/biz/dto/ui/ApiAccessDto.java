package me.shufork.biz.dto.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiAccessDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String apiName;

    @JsonProperty("method")
    private String apiMethod;

    @JsonProperty("url")
    private String apiPath;
}
