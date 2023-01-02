package com.ex.project.entity;

import com.ex.project.dto.NoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="no_table")
public class NoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String noWriter;
    @Column(length = 30)
    private String noApplyWriter;
    @Column(length = 100)
    private String noContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    public static NoEntity toChangeEntity(NoDTO noDTO,MemberEntity memberEntity) {
        NoEntity noEntity = new NoEntity();
        noEntity.setNoContents(noDTO.getNoContents());
        noEntity.setNoWriter(noDTO.getNoWriter());
        noEntity.setNoApplyWriter(noDTO.getNoApplyWriter());
        noEntity.setMemberEntity(memberEntity);
        return noEntity;
    }
}
