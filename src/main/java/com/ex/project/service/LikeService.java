package com.ex.project.service;

import com.ex.project.dto.LikeDTO;
import com.ex.project.entity.LikeEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.ProductEntity;
import com.ex.project.repository.LikeRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    public Long likeSave(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(likeDTO.getMemberEmail()).get();
        ProductEntity productEntity = productRepository.findById(likeDTO.getProductId()).get();
        LikeEntity likeEntity =LikeEntity.toChangeEntity(memberEntity,productEntity);
        Long savedId = likeRepository.save(likeEntity).getId();
        return savedId;
    }

    public LikeDTO findLike(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(likeDTO.getMemberEmail()).get();
        ProductEntity productEntity = productRepository.findById(likeDTO.getProductId()).get();
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndProductEntity(memberEntity,productEntity);
            LikeEntity likeEntity = optionalLikeEntity.get();
            LikeDTO likeDTO1 = LikeDTO.toChangeDTO(likeEntity);
            return likeDTO1;

    }

    public void likeDelete(Long id) {
        likeRepository.deleteById(id);
    }
}
