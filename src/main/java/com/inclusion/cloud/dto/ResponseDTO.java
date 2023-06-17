package com.inclusion.cloud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseDTO {
    @JsonProperty("status")
    boolean status;
    @JsonProperty("errors")
    List<String> errors;
    @JsonProperty("data")
    Object data;
}
