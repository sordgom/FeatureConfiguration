package com.feature.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "featureName" , "email", "enable"})
public class FeatureRequest {
    private String featureName;
    private String email;
    private Boolean enable;
}
