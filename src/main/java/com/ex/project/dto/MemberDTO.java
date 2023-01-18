package com.ex.project.dto;

import com.ex.project.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private LocalDateTime memberSaveTime;
    private String memberEmail;
    private String memberPass;
    private String memberName;
    private String memberGender;
    private String memberArea;
    private int memberAge;
    private String memberPhone;
    private String memberPurpose;


    public static MemberDTO changeMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPass(memberEntity.getMemberPass());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberArea(memberEntity.getMemberArea());
        memberDTO.setMemberAge(memberEntity.getMemberAge());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberPurpose(memberEntity.getMemberPurpose());
        memberDTO.setMemberSaveTime(memberEntity.getMemberSaveTime());
//        memberDTO.setMemberPoint(memberEntity.getMemberPoint());
        return memberDTO;
    }
}
