package com.habsida.morago.model.entity;

import com.habsida.morago.enums.EPlatform;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_versions")
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppVersion {
    @Id
    @Enumerated(EnumType.STRING)
    private EPlatform platform;
    private String min;
    private String latest;
    @CreatedDate
    @Column(updatable = false)
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;
    @LastModifiedDate
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;
}
