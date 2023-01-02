package com.ex.project.repository;

import com.ex.project.entity.AgreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreeRepository extends JpaRepository<AgreeEntity,Long> {
    List<AgreeEntity> findByAgreeApplyWriter(String memberEmail);
}
