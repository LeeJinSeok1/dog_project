package com.ex.project.service;

import com.ex.project.dto.AgreeDTO;
import com.ex.project.dto.NoDTO;
import com.ex.project.entity.AgreeEntity;
import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.NoEntity;
import com.ex.project.repository.ApplyRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.NoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoService {
    private final MemberRepository memberRepository;
    private final NoRepository noRepository;

    private final ApplyRepository applyRepository;

    @Transactional
    public void save(NoDTO noDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(noDTO.getNoApplyWriter()).get();
        NoEntity noEntity = NoEntity.toChangeEntity(noDTO,memberEntity);
        noRepository.save(noEntity);
    }

    public List<NoDTO> findNo(String memberEmail) {
        List<NoEntity> noEntityList = noRepository.findByNoApplyWriter(memberEmail);
        List<NoDTO> noDTOList = new ArrayList<>();
        for (NoEntity noEntity: noEntityList){
            NoDTO noDTO = NoDTO.toChangeDTO(noEntity);
            noDTOList.add(noDTO);
        }
        if(noDTOList.isEmpty()){
            return null;
        }else{
            return noDTOList;
        }
    }

    public void deleteNo(Long id) {
        noRepository.deleteById(id);
    }
}












