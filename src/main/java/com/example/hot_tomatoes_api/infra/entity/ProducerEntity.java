package com.example.hot_tomatoes_api.infra.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "producer")
public class ProducerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;
    @ManyToMany(mappedBy = "producers", fetch = FetchType.LAZY)
    private List<MovieEntity> movies;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducerEntity that = (ProducerEntity) o;
        return name.equals(that.name);
    }
}
