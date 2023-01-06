package com.ex.project.controller;

import com.ex.project.dto.ProductDTO;
import com.ex.project.dto.SuccessDTO;
import com.ex.project.service.ProductService;
import com.ex.project.service.SuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    private final SuccessService successService;
    @GetMapping("/")
    public String home(Model model) {
        List<ProductDTO> productDTOList = productService.findByHits();
        model.addAttribute("productHitsList",productDTOList);
        List<SuccessDTO> successDTOList = successService.findList();
        model.addAttribute("successList",successDTOList);
        return "home";
    }

}
