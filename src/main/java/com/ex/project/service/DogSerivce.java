package com.ex.project.service;

import com.ex.project.dto.DogDTO;
import com.ex.project.entity.DogEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.DogRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogSerivce {
    private final DogRepository dogRepository;
    private final MemberRepository memberRepository;
    public Long dogSave(DogDTO dogDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(dogDTO.getDogWriter()).get();
        DogEntity dogEntity = DogEntity.toChangeEntity(dogDTO,memberEntity);
        Long savedId = dogRepository.save(dogEntity).getId();
        return savedId;
    }
}
