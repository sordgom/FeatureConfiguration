package com.feature.controller;

import com.feature.domain.FeatureAccessResponse;
import com.feature.domain.FeatureRequest;
import com.feature.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService service;

    @GetMapping("/feature")
    public FeatureAccessResponse GetFeature(
            @RequestParam(value="email") String email,
            @RequestParam(value="featureName") String featureName){
        return service.getFeatureByEmailAndFeatureName(email, featureName);
    }

    @PostMapping("/feature")
    public ResponseEntity<String> createFeature(@RequestBody FeatureRequest request){
        return service.createFeature(request);
    }
}
