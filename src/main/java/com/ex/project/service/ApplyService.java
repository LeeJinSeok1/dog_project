package com.ex.project.service;

import com.ex.project.dto.ApplyDTO;
import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.AdoptRepository;
import com.ex.project.repository.ApplyRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final AdoptRepository adoptRepository;
    public void applySave(ApplyDTO applyDTO) {
        AdoptEntity adoptEntity = adoptRepository.findById(applyDTO.getAdoptApplyId()).get();
        MemberEntity memberEntity = memberRepository.findByMemberEmail(applyDTO.getApplyEmail()).get();
        ApplyEntity applyEntity = ApplyEntity.toChangeEntity(applyDTO,adoptEntity,memberEntity);
        applyRepository.save(applyEntity);
    }




    public List<ApplyDTO> findApply(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        List<ApplyEntity> applyEntityList = applyRepository.findAllByAdoptWriter(memberEntity.getMemberEmail());
        List<ApplyDTO> applyDTOList = new ArrayList<>();
        for (ApplyEntity applyEntity : applyEntityList){
            ApplyDTO applyDTO = ApplyDTO.toChangeDTO(applyEntity);
            applyDTOList.add(applyDTO);
        }
        if(applyDTOList.isEmpty()){
            return null;
        }else{
            return applyDTOList;
        }

    }

    public ApplyDTO applyDetail(Long id) {
        ApplyEntity applyEntity = applyRepository.findById(id).get();
        ApplyDTO applyDTO = ApplyDTO.toChangeDTO(applyEntity);
        return applyDTO;
    }



    public void applyDelete(Long applyId) {
        applyRepository.deleteById(applyId);
    }
}



















