package com.ex.project.controller;

import com.ex.project.dto.ProductDTO;
import com.ex.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/productMain")
    public String productMain(Model model){
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productList",productDTOList);
        return "/product/productMain";
    }

    @GetMapping("/productSave")
    public String productSavePage(){
        return "/product/productSave";
    }
    @PostMapping("/productSave")
    public String productSave(@ModelAttribute ProductDTO productDTO) throws IOException {
        productService.productSave(productDTO);
        return "redirect:productMain";
    }

}
