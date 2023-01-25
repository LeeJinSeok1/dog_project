package com.ex.project.service;

import com.ex.project.dto.CartDTO;
import com.ex.project.dto.OrderDTO;
import com.ex.project.entity.CartEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.OrderEntity;
import com.ex.project.repository.CartRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;
    @Transactional
    public List<CartDTO> orderSave(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        List<CartEntity> cartEntityList =  cartRepository.findAllByMemberEntity(memberEntity);
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (CartEntity cartEntity : cartEntityList){
            CartDTO cartDTO1 =new CartDTO();
            cartDTO1.setId(cartEntity.getId());
            cartDTO1.setPrice(cartEntity.getPrice());
            cartDTO1.setProductName(cartEntity.getProductName());
            cartDTOList.add(cartDTO1);
        }
        return  cartDTOList;
    }
@Transactional
    public Long orderSave2(String memberEmail, int totalPrice) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        if(totalPrice>memberEntity.getMemberPoint()){
            return null;
        }else{
            memberRepository.memberPointMinus(memberEmail,totalPrice);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderEmail(memberEmail);
            orderEntity.setOrderArea(memberEntity.getMemberArea());
            orderEntity.setOrderPrice(totalPrice);
            orderEntity.setOrderPhone(memberEntity.getMemberPhone());
            orderEntity.setMemberEntity(memberEntity);
            Long savedId = orderRepository.save(orderEntity).getId();
            return savedId;
        }

    }
@Transactional
    public OrderDTO findByOrder(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        OrderEntity orderEntity = orderRepository.findByMemberEntity(memberEntity);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderTime(orderEntity.getOrderTime());
        orderDTO.setOrderPrice(orderEntity.getOrderPrice());
        orderDTO.setOrderEmail(orderEntity.getOrderEmail());
        orderDTO.setOrderArea(orderEntity.getOrderArea());
        orderDTO.setOrderPhone(orderEntity.getOrderPhone());
        return orderDTO;
    }
}
