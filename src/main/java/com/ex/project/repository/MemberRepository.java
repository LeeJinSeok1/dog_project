package com.ex.project.repository;

import com.ex.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}
