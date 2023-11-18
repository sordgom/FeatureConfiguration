package com.feature.service;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;
import org.springframework.http.ResponseEntity;

public interface FeatureService {
    FeatureAccessResponse getFeatureByEmailAndFeatureName(String email, String featureName);
    ResponseEntity<String> createFeature(FeatureRequest request);
}
