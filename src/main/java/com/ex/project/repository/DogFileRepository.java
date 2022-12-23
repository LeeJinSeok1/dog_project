package com.ex.project.repository;

import com.ex.project.entity.DogEntity;
import com.ex.project.entity.DogFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogFileRepository extends JpaRepository<DogFileEntity,Long> {

    Optional<DogFileEntity> findByDogEntity(DogEntity dogEntity);
}
