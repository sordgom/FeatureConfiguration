package com.feature.domain;

import lombok.Data;
@Data
public class UserFeature {
    private final String featureName;
    private final boolean isEnabled;
    private final boolean canAccess;
}
