package com.ex.project.service;

import com.ex.project.dto.AgreeDTO;
import com.ex.project.entity.AgreeEntity;
import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.AgreeRepository;
import com.ex.project.repository.ApplyRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreeService {
    private final MemberRepository memberRepository;
    private final AgreeRepository agreeRepository;

    private final ApplyRepository applyRepository;
    public void agreeSave(AgreeDTO agreeDTO) {
        MemberEntity memberEntity =memberRepository.findByMemberEmail(agreeDTO.getAgreeApplyWriter()).get();
        AgreeEntity agreeEntity = AgreeEntity.toChangeEntity(agreeDTO,memberEntity);
        agreeRepository.save(agreeEntity);
    }

    public List<AgreeDTO> findAgree(String memberEmail) {
        List<AgreeEntity> agreeEntityList = agreeRepository.findByAgreeApplyWriter(memberEmail);
        List<AgreeDTO> agreeDTOList = new ArrayList<>();
        for (AgreeEntity agreeEntity: agreeEntityList){
            AgreeDTO agreeDTO = AgreeDTO.toChangeDTO(agreeEntity);
            agreeDTOList.add(agreeDTO);
        }
        if(agreeDTOList.isEmpty()){
            return null;
        }else{
            return agreeDTOList;
        }
    }


    public void deleteById(Long id) {
        agreeRepository.deleteById(id);
    }
}


















