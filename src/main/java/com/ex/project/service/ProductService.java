package com.ex.project.service;

import com.ex.project.dto.ProductDTO;
import com.ex.project.entity.ProductEntity;
import com.ex.project.entity.ProductFileEntity;
import com.ex.project.repository.ProductFileRepository;
import com.ex.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;
    public void productSave(ProductDTO productDTO)throws IOException {
        if(productDTO.getProductFile().isEmpty()) {
            ProductEntity productEntity = ProductEntity.toChangeEntity(productDTO);
            productRepository.save(productEntity);
        }else{
            MultipartFile productFile = productDTO.getProductFile();
            String originalFileName = productFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis()+"_"+originalFileName;
            String savePath = "D:\\dog_project\\" + storedFileName;
            productFile.transferTo(new File(savePath));

            ProductEntity productEntity =ProductEntity.toChangeFileEntity(productDTO);
            Long savedId = productRepository.save(productEntity).getId();

            ProductEntity entity = productRepository.findById(savedId).get();
            ProductFileEntity productFileEntity =
                    ProductFileEntity.toChangeFileEntity(entity,originalFileName,storedFileName);
            productFileRepository.save(productFileEntity);

        }
    }
}
