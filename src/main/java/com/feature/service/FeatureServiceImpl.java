package com.feature.service;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;
import com.feature.entity.FeatureEntity;
import com.feature.entity.FeatureUserEntity;
import com.feature.repository.FeatureRepository;
import com.feature.repository.FeatureUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final FeatureUserRepository featureUserRepository;

    /**
     * Get feature access status by user email and feature name.
     *
     * @param email       User's email
     * @param featureName Feature name
     * @return FeatureAccessResponse indicating whether the user has access to the feature
     */
    @Override
    public FeatureAccessResponse getFeatureByEmailAndFeatureName(String email, String featureName) {
        if (Objects.isNull(email) || Objects.isNull(featureName)) {
            log.debug("Email or featureName is empty");
            return new FeatureAccessResponse(false);
        }

        // Check if the feature exists and is enabled
        FeatureEntity entity = featureRepository.findByFeatureName(featureName);
        if (entity == null || !entity.isEnabled()) {
            log.debug("Feature not enabled");
            return new FeatureAccessResponse(false);
        }

        // Check if the user has access to the feature
        boolean canAccess = featureUserRepository.existsById_EmailAndId_Feature_FeatureName(email, featureName);
        return new FeatureAccessResponse(canAccess);
    }

    /**
     * Create a new feature and whitelist user by email.
     *
     * @param request Feature creation request
     * @return ResponseEntity indicating the result of the feature creation process
     */
    @Transactional
    @Override
    public ResponseEntity<String> createFeature(FeatureRequest request) {
        log.debug("Creating a new feature whitelisted by user email");
        try {
            // Sanitization checks
            if (StringUtils.isEmpty(request.getFeatureName()) || StringUtils.isEmpty(request.getEmail())) {
                log.debug("Invalid request: FeatureName or Email is empty");
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Data not modified");
            }

            FeatureEntity featureEntity = featureRepository.findByFeatureName(request.getFeatureName());
            if (featureEntity != null) {
                // Feature exists, update isEnabled
                featureEntity.setEnabled(request.getEnable());
            } else {
                // Feature doesn't exist, create a new one
                featureEntity = new FeatureEntity();
                featureEntity.setFeatureName(request.getFeatureName());
                featureEntity.setEnabled(request.getEnable());
            }

            // Save or update the feature
            featureRepository.save(featureEntity);
            log.debug("Feature created successfully");

            // Save whitelisted user
            FeatureUserEntity featureUserEntity = new FeatureUserEntity();
            FeatureUserEntity.FeatureUser user = new FeatureUserEntity.FeatureUser();
            user.setEmail(request.getEmail());
            user.setFeature(featureEntity);
            featureUserEntity.setId(user);

            featureUserRepository.save(featureUserEntity);

            return ResponseEntity.ok("Feature created successfully");
        } catch (Exception e) {
            log.error("Error creating feature", e);
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Data not modified");
        }
    }
}
