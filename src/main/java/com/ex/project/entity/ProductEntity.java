package com.ex.project.entity;

import com.ex.project.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="product_table")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String productName;
    @Column(length = 100,nullable = false)
    private String productContents;
    @Column(length = 100)
    private String productSpecies;
    @Column(length = 100,nullable = false)
    private String productPrice;
    @Column
    private int productHits;

    public static ProductEntity toChangeEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductHits(0);
        productEntity.setProductContents(productDTO.getProductContents());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductSpecies(productDTO.getProductSpecies());
        return productEntity;
    }
}
