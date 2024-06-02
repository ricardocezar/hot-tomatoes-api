package com.example.hot_tomatoes_api.infra.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(columnNames = {"releaseYear", "title"}))
public class MovieEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 4)
    private int releaseYear;
    private String title;
    @ManyToMany
    @JoinTable(name = "movie_producer",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "producerId"))
    private Set<ProducerEntity> producers;
    @OneToOne(mappedBy = "movieEntity", fetch = FetchType.LAZY)
    private AwardEntity award;
}
