package com.feature.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "features_users")
public class FeatureUserEntity {
    @EmbeddedId
    private FeatureUser id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Data
    @Embeddable
    public static class FeatureUser implements Serializable{
        private String email;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "feature_id")
        private FeatureEntity feature;
    }
}
