package com.feature.repository;

import com.feature.entity.FeatureEntity;
import com.feature.entity.FeatureUserEntity;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeatureUserRepository extends JpaRepository<FeatureUserEntity, Long> {
    boolean existsById_EmailAndId_Feature_FeatureName(String email, String featureName);
}
