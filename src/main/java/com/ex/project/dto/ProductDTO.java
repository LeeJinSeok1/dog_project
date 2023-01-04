package com.ex.project.dto;

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
    private String productPrice;
    private String productSpecies;
    private int productHits;

    private MultipartFile productFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;
}
