package com.ex.project.service;

import com.ex.project.dto.ProductDTO;
import com.ex.project.entity.ProductEntity;
import com.ex.project.entity.ProductFileEntity;
import com.ex.project.repository.ProductFileRepository;
import com.ex.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;
    @Transactional
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
    @Transactional
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity: productEntityList){
            ProductDTO productDTO = ProductDTO.toChangeDTO(productEntity);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
    @Transactional
    public Page<ProductDTO> paging(Pageable pageable) {
        int page =pageable.getPageNumber() -1;
        final int pageLimit= 6;
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of
                (page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<ProductDTO> productList = productEntities.map(
                product -> new ProductDTO(product.getId(),
                        product.getProductName(),
                        product.getProductContents(),
                        product.getProductPrice(),
                        product.getProductSpecies(),
                        product.getProductHits(),
                        product.getProductFileEntityList().get(0).getStoredFileName()
                )
        );
        return productList;

    }
    @Transactional
    public ProductDTO findById(Long id) {
       ProductEntity productEntity = productRepository.findById(id).get();
       ProductDTO productDTO = ProductDTO.toChangeDTO(productEntity);
       return productDTO;
    }
    @Transactional
    public void productPlusHits(Long id) {
        productRepository.ProductHits(id);
    }

    @Transactional
    public List<ProductDTO> findByHits(){
//        List<ProductEntity> productEntityList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "productHits"));
        List<ProductEntity> productEntityList = productRepository.findTop3ByOrderByProductHitsDesc();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList){
            ProductDTO productDTO = ProductDTO.toChangeDTO(productEntity);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }


}











