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
}
