package com.ex.project.repository;

import com.ex.project.entity.SuccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuccessRepository extends JpaRepository<SuccessEntity,Long> {
    List<SuccessEntity> findTop3ByOrderBySuccessSaveTImeDesc();
}
