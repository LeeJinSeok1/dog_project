package com.ex.project.repository;

import com.ex.project.entity.LikeEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {




    Optional<LikeEntity> findByMemberEntityAndProductEntity(MemberEntity memberEntity,ProductEntity productEntity);


    List<LikeEntity> findTop3ByMemberEntityOrderByIdDesc(MemberEntity memberEntity);

//    List<ProductEntity> findTop3ByProductSpeciesOrderByProductHitsDesc(String dogSpecies);
}
