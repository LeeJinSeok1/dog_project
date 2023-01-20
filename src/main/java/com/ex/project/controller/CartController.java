package com.ex.project.controller;

import com.ex.project.dto.CartDTO;
import com.ex.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/cartSave")
    public @ResponseBody List<CartDTO> cartSave(@ModelAttribute CartDTO cartDTO,
                                                Model model){
        Long savedId = cartService.cartSave(cartDTO);
        List<CartDTO> cartDTOList = cartService.findAllById(cartDTO);
        model.addAttribute("cartDTOList",cartDTOList);
        System.out.println(cartDTOList);
        return cartDTOList;
    }

    @PostMapping("/findTotal")
    public @ResponseBody CartDTO findTotal(@RequestParam("memberEmail") String memberEmail){
        CartDTO cartDTO = cartService.findTotal(memberEmail);
        return cartDTO;
    }
}
