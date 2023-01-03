package com.ex.project.repository;

import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByAdoptEntityOrderByIdDesc(AdoptEntity
                                                                  adoptEntity);
}
