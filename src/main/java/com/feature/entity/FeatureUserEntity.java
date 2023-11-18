package com.feature.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "features_users")
public class FeatureUserEntity {
    @EmbeddedId
    private FeatureUser id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_modified")
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
