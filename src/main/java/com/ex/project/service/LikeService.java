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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    @Transactional
    public Long likeSave(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(likeDTO.getMemberEmail()).get();
        ProductEntity productEntity = productRepository.findById(likeDTO.getLikeProductId()).get();

        LikeEntity likeEntity =LikeEntity.toChangeEntity(memberEntity,productEntity);

        Long savedId = likeRepository.save(likeEntity).getId();



        return savedId;
    }

    @Transactional
    public Long checkFind(LikeDTO likeDTO) {
        MemberEntity memberEntity =memberRepository.findByMemberEmail(likeDTO.getMemberEmail()).get();
        ProductEntity productEntity = productRepository.findById(likeDTO.getLikeProductId()).get();
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndProductEntity(memberEntity,productEntity);
//        memberEntity.getLikeEntityList();
        if (optionalLikeEntity.isPresent()) {
            LikeEntity likeEntity = optionalLikeEntity.get();
            LikeDTO ckDTO = LikeDTO.toChangeDTO(likeEntity);
            Long likeId = ckDTO.getId();
            return likeId;
        }else{
            return null;
        }
    }

    public void likeDelete(Long likeId,Long likeProductId) {
        likeRepository.deleteById(likeId);
    }

    @Transactional
    public LikeDTO checkFind2(Long id,String memberEmail) {
        MemberEntity memberEntity =memberRepository.findByMemberEmail(memberEmail).get();
        ProductEntity productEntity = productRepository.findById(id).get();
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndProductEntity(memberEntity,productEntity);
//        memberEntity.getLikeEntityList();
        if (optionalLikeEntity.isPresent()) {
            LikeEntity likeEntity = optionalLikeEntity.get();
            LikeDTO ckDTO = LikeDTO.toChangeDTO(likeEntity);
            return ckDTO;
        }else{
            return null;
        }
    }
}
