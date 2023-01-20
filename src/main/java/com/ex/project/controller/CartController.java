package com.ex.project.controller;

import com.ex.project.dto.CartDTO;
import com.ex.project.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/deleteCart")
    public @ResponseBody List<CartDTO> deleteCart(@ModelAttribute CartDTO cartDTO,
                                          @RequestParam("cartId") Long cartId,
                                          Model model){
        System.out.println(cartId);
        System.out.println("ddtdtd="+cartDTO);
        cartService.deleteById(cartId);
        List<CartDTO> cartDTOList = cartService.findAllById(cartDTO);
        model.addAttribute("cartDTOList",cartDTOList);
        return cartDTOList;
    }
}
