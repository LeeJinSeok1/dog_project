package com.ex.project.repository;

import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<ApplyEntity,Long> {
    List<ApplyEntity> findAllByAdoptWriter(String memberEmail);

    ApplyEntity findByAdoptWriter(String noWriter);

    List<Object> findByApplyEmail(String agreeApplyWriter);
}
