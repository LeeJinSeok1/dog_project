package com.ex.project.repository;

import com.ex.project.entity.DogFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogFileRepository extends JpaRepository<DogFileEntity,Long> {
}
