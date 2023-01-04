package com.ex.project.repository;

import com.ex.project.entity.ProductFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFileEntity,Long> {
}
