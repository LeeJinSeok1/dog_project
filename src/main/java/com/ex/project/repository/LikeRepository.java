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

    @Modifying
    @Query(value="update LikeEntity l set l.likeCount = l.likeCount + 1 where l.id=:id")
    void LikeCount(@Param("id") Long id);


    Optional<LikeEntity> findByMemberEntityAndProductEntity(MemberEntity memberEntity,ProductEntity productEntity);


}
