package com.ex.project.entity;

import com.ex.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,unique = true,nullable = false)
    private String memberEmail;
    @Column(length = 30,nullable = false)
    private String memberPass;
    @Column(length = 15)
    private String memberName;
    @Column(length = 3)
    private String memberGender;
    @Column(length = 100,nullable = false)
    private String memberArea;
    @Column
    private int memberAge;
    @Column(length = 30)
    private String memberPhone;
    @Column(length = 20,nullable = false)
    private String memberPurpose;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime memberSaveTime;
    @Column
    private int memberPoint;

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<DogEntity> dogEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<AdoptEntity> adoptEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ApplyEntity> applyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<AgreeEntity> agreeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<NoEntity> noEntityList = new ArrayList<>();


    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<SuccessEntity> successEntityList = new ArrayList<>();


    // dto를 entity로 바꾸는 메서드
    public static MemberEntity changeEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberPoint(0);
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberArea(memberDTO.getMemberArea());
        memberEntity.setMemberAge(memberDTO.getMemberAge());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberPurpose(memberDTO.getMemberPurpose());
        memberEntity.setMemberSaveTime(memberDTO.getMemberSaveTime());
        return memberEntity;
    }

    public static MemberEntity toUpdateChangeEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberArea(memberDTO.getMemberArea());
        memberEntity.setMemberAge(memberDTO.getMemberAge());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberPurpose(memberDTO.getMemberPurpose());
        return memberEntity;
    }
}
