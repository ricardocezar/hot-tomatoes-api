package com.example.hot_tomatoes_api.infra.dataaccess;

import com.example.hot_tomatoes_api.infra.entity.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardDao extends JpaRepository<AwardEntity, Long> {
}
