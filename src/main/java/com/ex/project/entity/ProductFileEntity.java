package com.ex.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="product_file_table")
public class ProductFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity productEntity;

    public static ProductFileEntity toChangeFileEntity(ProductEntity entity, String originalFileName, String storedFileName) {
        ProductFileEntity productFileEntity = new ProductFileEntity();
        productFileEntity.setOriginalFileName(originalFileName);
        productFileEntity.setStoredFileName(storedFileName);
        productFileEntity.setProductEntity(entity);
        return productFileEntity;
    }
}
