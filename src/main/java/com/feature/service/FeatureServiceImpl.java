package com.feature.service;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;
import com.feature.domain.UserFeature;
import com.feature.entity.FeatureEntity;
import com.feature.entity.FeatureUserEntity;
import com.feature.repository.FeatureRepository;
import com.feature.repository.FeatureUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements  FeatureService{

    private final FeatureRepository featureRepository;
    private final FeatureUserRepository featureUserRepository;
    @Override
    public FeatureAccessResponse getFeatureByEmailAndFeatureName(String email, String featureName){
        List<UserFeature> entities = featureRepository.getFeaturesByEmailAndFeatureName(featureName, email);
        if(!CollectionUtils.isEmpty(entities)){
            return new FeatureAccessResponse(true);
        }
        return new FeatureAccessResponse(false);
    }

    @Override
    public void createFeature(FeatureRequest request){
        log.debug("Create a new feature whitelisted by user email");

        // Check if the feature already exists
        FeatureEntity feature = featureRepository.findByFeatureName(request.getFeatureName());
        if( Objects.nonNull(feature) ){
            log.debug("Feature already exists in the db");
            return;
        }
        // Save feature
        try{
            FeatureEntity entity = new FeatureEntity();
            entity.setFeatureName(request.getFeatureName());
            entity.setEnabled(request.getEnable());
            featureRepository.save(entity);

            // Save whitelisted user
            FeatureUserEntity.FeatureUser user = new FeatureUserEntity.FeatureUser();
            user.setEmail(request.getEmail());
            user.setFeature(entity);
            FeatureUserEntity userEntity = new FeatureUserEntity();
            userEntity.setId(user);
            featureUserRepository.save(userEntity);

        } catch (Error e) {
            log.error(String.valueOf(e));
        }
    }
}
