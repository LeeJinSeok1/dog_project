package com.ex.project.controller;

import com.ex.project.dto.LikeDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.dto.ProductDTO;
import com.ex.project.service.CartService;
import com.ex.project.service.LikeService;
import com.ex.project.service.MemberService;
import com.ex.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;
    private final LikeService likeService;

    private final CartService cartService;


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

    @GetMapping("/product")
    public String productPaging(@PageableDefault(page =1)Pageable pageable,
                                Model model){

        Page<ProductDTO> productDTOList = productService.paging(pageable);
        model.addAttribute("productList",productDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < productDTOList.getTotalPages()) ? startPage + blockLimit - 1 : productDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        //????????? ??? ?????????
        List<ProductDTO> productHitsList = productService.findByHits();
        model.addAttribute("productHitsList",productHitsList);

        return "/product/productPaging";

    }

    @GetMapping("/productDetail/{id}/{memberEmail}")
    public String productDetail(@PathVariable Long id,
                                @PathVariable String memberEmail,
                                Model model) {
        productService.productPlusHits(id);
        MemberDTO result = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member",result);
       ProductDTO productDTO = productService.findById(id);
       model.addAttribute("product",productDTO);
       LikeDTO likeDTO = likeService.checkFind2(id,memberEmail);

        System.out.println("like="+likeDTO);
        if(likeDTO !=null){
            model.addAttribute("like",likeDTO);
        }else{
            model.addAttribute("like",null);
        }

       return "/product/productDetail";
    }



}
