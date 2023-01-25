package com.ex.project.repository;

import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    OrderEntity findByMemberEntity(MemberEntity memberEntity);
}
