package com.ex.project.entity;

import com.ex.project.dto.AdoptDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="adopt_table")
public class AdoptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String adoptWriter;
    @Column(length = 15,nullable = false)
    private String adoptName;
    @Column(length =50,nullable = false )
    private String adoptSpecies;
    @Column(length = 10,nullable = false)
    private String adoptAge;
    @Column(length = 50,nullable = false)
    private String adoptTitle;
    @Column(length = 500,nullable = false)
    private String adoptContents;
    @Column(length = 50,nullable = false)
    private String adoptArea;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime adoptSaveTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    public static AdoptEntity toChangeEntity(AdoptDTO adoptDTO,MemberEntity memberEntity) {
        AdoptEntity adoptEntity = new AdoptEntity();
        adoptEntity.setAdoptWriter(adoptDTO.getAdoptWriter());
        adoptEntity.setAdoptName(adoptDTO.getAdoptName());
        adoptEntity.setAdoptAge(adoptDTO.getAdoptAge());
        adoptEntity.setAdoptTitle(adoptDTO.getAdoptTitle());
        adoptEntity.setAdoptContents(adoptDTO.getAdoptContents());
        adoptEntity.setAdoptSpecies(adoptDTO.getAdoptSpecies());
        adoptEntity.setAdoptArea(adoptDTO.getAdoptArea());
        adoptEntity.setMemberEntity(memberEntity);
        return adoptEntity;
    }
}