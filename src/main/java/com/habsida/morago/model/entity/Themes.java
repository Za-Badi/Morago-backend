package com.habsida.morago.model.entity;

import com.habsida.morago.enums.QuestionsCategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "themes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String korean_title;
    private BigDecimal price;
    private BigDecimal night_price;
    private String description;
    private  Boolean is_popular;
    private  Boolean is_active;
    private Long category_id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Files icon_id;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories categories;

    @Override
    public String toString() {
        return "Themes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", night_price=" + night_price + '\'' +
                ", korean_title=" + korean_title +
                ", description='" + description + '\'' +
                ", is_popular=" + is_popular +
                ", is_active=" + is_active +
                ", icon_id=" + icon_id +
                ", category_id=" +category_id+ '\''+
                ", createdAt=" + createdAt +
                ", updatedAt=" +updatedAt+
                '}';
    }
}
