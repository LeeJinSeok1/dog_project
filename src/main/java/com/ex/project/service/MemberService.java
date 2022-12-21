package com.ex.project.service;

import com.ex.project.dto.MemberDTO;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    회원저장처리
    public Long memberSave(MemberDTO memberDTO) {
        MemberEntity memberEntity= MemberEntity.changeEntity(memberDTO);
       return memberRepository.save(memberEntity).getId();
    }

    public MemberDTO findByMemberEmail(String memberEmail) {
       Optional<MemberEntity> optionalMemberEntity= memberRepository.findByMemberEmail(memberEmail);

       if(optionalMemberEntity.isPresent()) {
           return MemberDTO.changeMemberDTO(optionalMemberEntity.get());
       }else{
           return null;
       }
    }

    public MemberDTO memberLoginCk(MemberDTO memberDTO) {
       Optional<MemberEntity> optionalMemberEntity= memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
       if(optionalMemberEntity.isPresent()) {
           MemberEntity memberEntity= optionalMemberEntity.get();
           MemberDTO result = MemberDTO.changeMemberDTO(memberEntity);
           if(result.getMemberPass().equals(memberDTO.getMemberPass()) ){
               return result;
           }else{
               return null;
           }
       }else{
           return null;
       }
    }
}
