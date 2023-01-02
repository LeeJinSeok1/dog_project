package com.ex.project.repository;

import com.ex.project.entity.NoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoRepository extends JpaRepository<NoEntity,Long> {
    List<NoEntity> findByNoApplyWriter(String memberEmail);
}
