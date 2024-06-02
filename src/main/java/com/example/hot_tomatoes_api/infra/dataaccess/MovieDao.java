package com.example.hot_tomatoes_api.infra.dataaccess;

import com.example.hot_tomatoes_api.infra.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<MovieEntity, Long> {
}
