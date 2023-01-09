package com.ex.project.dto;

import com.ex.project.entity.ProductEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private Long id;
    private String productName;
    private String productContents;
    private int productPrice;
    private String productSpecies;
    private int productHits;

    private MultipartFile productFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public ProductDTO(Long id, String productName, String productContents, int productPrice, String productSpecies, int productHits,
                      String storedFileName) {
        this.id=id;
        this.productName=productName;
        this.productContents=productContents;
        this.productPrice=productPrice;
        this.productSpecies=productSpecies;
        this.productHits=productHits;
        this.storedFileName=storedFileName;
    }

    public static ProductDTO toChangeDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductContents(productEntity.getProductContents());
        productDTO.setProductHits(productEntity.getProductHits());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setProductSpecies(productEntity.getProductSpecies());
        if(productEntity.getFileAttached() ==1){
            productDTO.setFileAttached(productEntity.getFileAttached());

            productDTO.setOriginalFileName(productEntity.getProductFileEntityList().get(0).getOriginalFileName());
            productDTO.setStoredFileName(productEntity.getProductFileEntityList().get(0).getStoredFileName());
        }else{
            productDTO.setFileAttached(productEntity.getFileAttached());
        }
        return productDTO;
    }
}
