package com.ex.project.repository;

import com.ex.project.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Modifying
    @Query(value = "update ProductEntity  p set p.productHits = p.productHits + 1 where p.id = :id")
    void ProductHits(@Param("id") Long id);


    List<ProductEntity> findTop3ByOrderByProductHitsDesc();
}
