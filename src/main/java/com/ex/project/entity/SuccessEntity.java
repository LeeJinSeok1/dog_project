package com.ex.project.entity;

import com.ex.project.dto.SuccessDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="success_table")
public class SuccessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long agreeId;
    @Column(length = 30)
    private String agreeWriter;
    @Column(length = 30)
    private String agreeApplyWriter;
    @Column(length = 50)
    private String agreeContents;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime successSaveTIme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;


    public static SuccessEntity toChangeEntity(SuccessDTO successDTO, MemberEntity memberEntity) {
        SuccessEntity successEntity = new SuccessEntity();
        successEntity.setAgreeWriter(successDTO.getAgreeWriter());
        successEntity.setAgreeApplyWriter(successDTO.getAgreeApplyWriter());
        successEntity.setAgreeContents(successDTO.getAgreeContents());
        successEntity.setAgreeId(successDTO.getAgreeId());
        successEntity.setMemberEntity(memberEntity);
        successEntity.setSuccessSaveTIme(successDTO.getSuccessSaveTime());
        return successEntity;
    }
}
