package com.feature.service;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements  FeatureService{
    @Override
    public FeatureAccessResponse getFeatureByEmailAndFeatureName(String email, String featureName){
        return new FeatureAccessResponse(true);
    }

    @Override
    public void createFeature(FeatureRequest request){

    }


}
