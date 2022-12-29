package com.ex.project.entity;

import com.ex.project.dto.ApplyDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="apply_table")
public class ApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String applyEmail;
    @Column(length = 10)
    private String applyGender;
    @Column(length = 10)
    private String applyAge;
    @Column(length = 10,nullable = false)
    private Long adoptApplyId;
    @Column(length = 10,nullable = false)
    private String applyHaveDog;
    @Column(length = 50,nullable = false)
    private String applyTitle;
    @Column(length = 500,nullable = false)
    private String applyContents;
    @Column(length = 30,nullable = false)
    private String adoptWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adopt_id")
    private AdoptEntity adoptEntity;

    public static ApplyEntity toChangeEntity(ApplyDTO applyDTO,AdoptEntity adoptEntity,MemberEntity memberEntity) {
        ApplyEntity applyEntity = new ApplyEntity();
        applyEntity.setApplyTitle(applyDTO.getApplyTitle());
        applyEntity.setApplyContents(applyDTO.getApplyContents());
        applyEntity.setApplyEmail(applyDTO.getApplyEmail());
        applyEntity.setApplyAge(applyDTO.getApplyAge());
        applyEntity.setApplyGender(applyDTO.getApplyGender());
        applyEntity.setApplyHaveDog(applyDTO.getApplyHaveDog());
        applyEntity.setAdoptApplyId(applyDTO.getAdoptApplyId());
        applyEntity.setAdoptEntity(adoptEntity);
        applyEntity.setMemberEntity(memberEntity);
        applyEntity.setAdoptWriter(applyDTO.getAdoptWriter());
        return applyEntity;
    }
}
