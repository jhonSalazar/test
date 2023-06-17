package com.inclusion.cloud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class MaximumRequestDTO {
    @JsonProperty("x")
    @NotNull(message = "x can not be null")
    @Min(value = 2, message = "x parameter must be greater than or equal to 2")
    @Max(value = 1000000000, message = "x parameter must be less than or equal to 1000000000") // Math.pow(10, 9);
    private Long x;

    @JsonProperty("y")
    @NotNull(message = "y can not be null")
    @Min(value = 0, message = "y parameter must be greater than or equal to 0")
    private Long y;

    @JsonProperty("n")
    @NotNull(message = "n can not be null")

    private Long n;
}
