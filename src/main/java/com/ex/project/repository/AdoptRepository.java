package com.ex.project.repository;

import com.ex.project.entity.AdoptEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptRepository extends JpaRepository<AdoptEntity,Long> {
    //종 검색
    List<AdoptEntity> findByAdoptSpeciesContainingOrderByIdDesc(String q);
   // 지역 검색
    List<AdoptEntity> findByAdoptAreaContainingOrderByIdDesc(String q);

    Page<AdoptEntity> findByAdoptSpeciesContainingOrderByIdDesc(String q, Pageable pageable);

    Page<AdoptEntity> findByAdoptAreaContainingOrderByIdDesc(String q, Pageable pageable);




}
