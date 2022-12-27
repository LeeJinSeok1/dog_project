package com.ex.project.repository;

import com.ex.project.entity.AdoptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptRepository extends JpaRepository<AdoptEntity,Long> {
}
