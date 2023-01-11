package com.ex.project.service;

import com.ex.project.dto.SuccessDTO;
import com.ex.project.entity.*;
import com.ex.project.repository.AgreeRepository;
import com.ex.project.repository.ApplyRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.SuccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuccessService {
    private final MemberRepository memberRepository;
    private final SuccessRepository successRepository;

    private final AgreeRepository agreeRepository;

    private final ApplyRepository applyRepository;
    public Long successSave(SuccessDTO successDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(successDTO.getAgreeWriter()).get();
        SuccessEntity successEntity = SuccessEntity.toChangeEntity(successDTO,memberEntity);
        Long savedId = successRepository.save(successEntity).getId();

        return savedId;
    }

    public List<SuccessDTO> findAll() {
       List<SuccessEntity> successEntityList = successRepository.findAll();
       List<SuccessDTO> successDTOList = new ArrayList<>();
       for (SuccessEntity successEntity : successEntityList){
           SuccessDTO successDTO =SuccessDTO.toChangeDTO(successEntity);
           successDTOList.add(successDTO);
       }
       return successDTOList;
    }

    public List<SuccessDTO> findList() {
        List<SuccessEntity> successEntityList = successRepository.findTop3ByOrderBySuccessSaveTImeDesc();
        List<SuccessDTO> successDTOList = new ArrayList<>();
        for (SuccessEntity successEntity : successEntityList){
           SuccessDTO successDTO = SuccessDTO.toChangeDTO(successEntity);
           successDTOList.add(successDTO);
        }
        return successDTOList;

    }
}
