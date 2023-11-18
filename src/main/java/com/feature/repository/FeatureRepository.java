package com.feature.repository;

import com.feature.domain.UserFeature;
import com.feature.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

    /**
     * Return a list of UserFeatures based on feature name and email.
     *
     * @param feature Feature name
     * @param email   User's email
     * @return List of UserFeatures containing feature details and access information
     */
    @Query(value =
            "SELECT new com.feature.domain.UserFeature(" +
                    "f.featureName AS featureName, " +
                    "f.isEnabled AS isEnabled, " +
                    "(SELECT COUNT(*) > 0 " +
                    "FROM FeatureUserEntity fu " +
                    "WHERE fu.id.email = ?2 " +
                    "AND fu.id.feature.id = f.id) AS canAccess" +
                    ") " +
                    "FROM FeatureEntity f " +
                    "WHERE f.featureName = ?1")
    List<UserFeature> getFeaturesByEmailAndFeatureName(String feature, String email);

    /**
     * Find a FeatureEntity by its feature name.
     *
     * @param featureName Feature name
     * @return FeatureEntity or null if not found
     */
    FeatureEntity findByFeatureName(String featureName);
}
