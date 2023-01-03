package com.ex.project.service;

import com.ex.project.dto.SuccessDTO;
import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.entity.SuccessEntity;
import com.ex.project.repository.AgreeRepository;
import com.ex.project.repository.MemberRepository;
import com.ex.project.repository.SuccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuccessService {
    private final MemberRepository memberRepository;
    private final SuccessRepository successRepository;
    public Long successSave(SuccessDTO successDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(successDTO.getAgreeApplyWriter()).get();
        SuccessEntity successEntity = SuccessEntity.toChangeEntity(successDTO,memberEntity);
        Long savedId = successRepository.save(successEntity).getId();

        return savedId;
    }
}
