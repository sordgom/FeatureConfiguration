package com.feature.service;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;

public interface FeatureService {
    FeatureAccessResponse getFeatureByEmailAndFeatureName(String email, String featureName);
    void createFeature(FeatureRequest request);
}
