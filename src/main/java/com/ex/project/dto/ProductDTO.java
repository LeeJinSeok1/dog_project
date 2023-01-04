package com.ex.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private Long id;
    private String productName;
    private String productContents;
    private String productPrice;
    private String productSpecies;
    private int productHits;
}
