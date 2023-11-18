package com.feature.repository;

import com.feature.entity.FeatureEntity;
import com.feature.entity.FeatureUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureUserRepository extends JpaRepository<FeatureUserEntity, Long> {
}
