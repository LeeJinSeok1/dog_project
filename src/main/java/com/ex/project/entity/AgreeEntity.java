package com.ex.project.entity;

import com.ex.project.dto.AgreeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="agree_table")
public class AgreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String agreeWriter;
    @Column(length = 30)
    private String agreeApplyWriter;
    @Column(length = 30)
    private String agreePhone;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;




    public static AgreeEntity toChangeEntity(AgreeDTO agreeDTO, MemberEntity memberEntity) {
        AgreeEntity agreeEntity = new AgreeEntity();
        agreeEntity.setAgreeWriter(agreeDTO.getAgreeWriter());
        agreeEntity.setAgreeApplyWriter(agreeDTO.getAgreeApplyWriter());
        agreeEntity.setAgreePhone(agreeDTO.getAgreePhone());
        agreeEntity.setMemberEntity(memberEntity);
        return agreeEntity;
    }
}
