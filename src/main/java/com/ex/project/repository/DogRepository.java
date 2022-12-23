package com.ex.project.repository;

import com.ex.project.dto.DogDTO;
import com.ex.project.entity.DogEntity;
import com.ex.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogRepository extends JpaRepository<DogEntity,Long> {

    Optional<DogEntity> findByDogWriter(String memberEmail);
}
