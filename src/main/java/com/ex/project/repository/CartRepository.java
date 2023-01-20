package com.ex.project.repository;

import com.ex.project.entity.CartEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
    List<CartEntity> findAllByMemberEntity(MemberEntity memberEntity);


    @Query(value = "select sum(c.price) from CartEntity c where c.memberEntity = :memberEntity")
    int totalPrice(@Param("memberEntity") MemberEntity memberEntity);


}
