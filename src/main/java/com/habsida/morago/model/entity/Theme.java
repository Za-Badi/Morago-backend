package com.habsida.morago.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "themes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(name = "themes.field", attributeNodes = {@NamedAttributeNode("category"),
        @NamedAttributeNode("icon")})
@NamedEntityGraph(name = "themes.image",
        attributeNodes = {@NamedAttributeNode("icon")}
)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String koreanTitle;
    private BigDecimal price;
    private BigDecimal nightPrice;
    private String description;
    private Boolean isPopular;
    private Boolean isActive;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "icon_id")
    private File icon;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;

    @ManyToMany(mappedBy = "themes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TranslatorProfile> translatorProfiles;

    @Override
    public String toString() {
        return "Theme {" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", night_price=" + nightPrice + '\'' + ", korean_title=" + koreanTitle + ", description='" + description + '\'' + ", is_popular=" + isPopular + ", is_active=" + isActive + ", icon_id=" + icon + ", category_id=" + category.getId() + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
