package com.ex.project.entity;

import com.ex.project.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column
    private int productPrice;
    @Column
    private int productHits;
    @Column
    private int fileAttached;


    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ProductFileEntity> productFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityLst = new ArrayList<>();
    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CartEntity> cartEntityList = new ArrayList<>();

    public static ProductEntity toChangeEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductHits(0);
        productEntity.setProductContents(productDTO.getProductContents());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductSpecies(productDTO.getProductSpecies());
        productEntity.setFileAttached(0);
        return productEntity;
    }

    public static ProductEntity toChangeFileEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductHits(0);
        productEntity.setProductContents(productDTO.getProductContents());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductSpecies(productDTO.getProductSpecies());
        productEntity.setFileAttached(1);
        return productEntity;
    }
}
