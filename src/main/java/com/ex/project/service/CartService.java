package com.ex.project.service;

import com.ex.project.dto.CartDTO;
import com.ex.project.entity.CartEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.ProductEntity;
import com.ex.project.repository.CartRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private final CartRepository cartRepository;
    @Transactional
    public Long cartSave(CartDTO cartDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(cartDTO.getMemberEmail()).get();
        ProductEntity productEntity = productRepository.findById(cartDTO.getProductId()).get();
        CartEntity cartEntity = new CartEntity();
        cartEntity.setPrice(productEntity.getProductPrice());
        cartEntity.setProductName(productEntity.getProductName());
        cartEntity.setMemberEntity(memberEntity);
        Long savedId = cartRepository.save(cartEntity).getId();
        return savedId;
    }
@Transactional
    public List<CartDTO> findAllById(CartDTO cartDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(cartDTO.getMemberEmail()).get();
        List<CartEntity> cartEntityList =  cartRepository.findAllByMemberEntity(memberEntity);
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (CartEntity cartEntity : cartEntityList){
            CartDTO cartDTO1 =new CartDTO();
            cartDTO1.setId(cartEntity.getId());
            cartDTO1.setPrice(cartEntity.getPrice());
            cartDTO1.setProductName(cartEntity.getProductName());
            cartDTOList.add(cartDTO1);

        }
    return cartDTOList;
    }
@Transactional
    public CartDTO findTotal(String memberEmail) {
    MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        int totalPrice = cartRepository.totalPrice(memberEntity);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalPrice(totalPrice);
        return cartDTO;
    }
}


