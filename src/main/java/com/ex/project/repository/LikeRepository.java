package com.ex.project.repository;

import com.ex.project.entity.LikeEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    Optional<LikeEntity> findByMemberEntityAndProductEntity(MemberEntity memberEntity, ProductEntity productEntity);
}
