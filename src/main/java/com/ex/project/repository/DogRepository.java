package com.ex.project.repository;

import com.ex.project.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<DogEntity,Long> {
}
