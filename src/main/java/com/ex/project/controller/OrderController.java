package com.ex.project.controller;

import com.ex.project.dto.CartDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.dto.OrderDTO;
import com.ex.project.service.CartService;
import com.ex.project.service.MemberService;
import com.ex.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    private final CartService cartService;
    @GetMapping("/orderSavePage/{memberEmail}")
    public String orderSavePage(@PathVariable String memberEmail,
                                          Model model){
       List<CartDTO> cartDTOList = orderService.orderSave(memberEmail);
       if(cartDTOList == null){
          model.addAttribute("cartList","null");
       }else{
           MemberDTO memberDTO =memberService.findByMemberEmail(memberEmail);
           CartDTO cartDTO =cartService.findTotal(memberEmail);
           model.addAttribute("cartList",cartDTOList);
           model.addAttribute("member",memberDTO);
           model.addAttribute("cart",cartDTO);
       }
        return "/order/orderDetail";
    }

    @GetMapping("/orderSave/{memberEmail}/{totalPrice}")
    public String orderSave(@PathVariable String memberEmail,
                            @PathVariable int totalPrice,
                            Model model){
        System.out.println("memberEmail"+memberEmail);
        System.out.println("total"+totalPrice);
        Long savedId = orderService.orderSave2(memberEmail,totalPrice);
        if(savedId != null){
            OrderDTO orderDTO = orderService.findByOrder(memberEmail);
            MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
            model.addAttribute("order",orderDTO);
            model.addAttribute("member",memberDTO);
            return "/order/orderSuccess";
        }else{
            return "/order/orderFail";
        }

    }




}
