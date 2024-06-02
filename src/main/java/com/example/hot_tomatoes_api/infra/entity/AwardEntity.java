package com.example.hot_tomatoes_api.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "award")
@IdClass(AwardKey.class)
public class AwardEntity {
    public AwardEntity() {}
    public AwardEntity(int awardYear, MovieEntity movieEntity) {
        this.awardYear = awardYear;
        this.movieEntity = movieEntity;
    }

    @Id
    @Column(length = 4)
    private int awardYear;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieId", nullable = false)
    private MovieEntity movieEntity;
}
