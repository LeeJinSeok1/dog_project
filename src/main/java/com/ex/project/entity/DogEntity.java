package com.ex.project.entity;

import com.ex.project.dto.DogDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="dog_table")
public class DogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String dogSpecies;
    @Column(length = 30)
    private String dogName;
    @Column(length = 10)
    private String dogAge;
    @Column(length = 10)
    private String dogWeight;
    @Column(length = 30)
    private String dogWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    public static DogEntity toChangeEntity(DogDTO dogDTO, MemberEntity memberEntity) {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setDogWriter(dogDTO.getDogWriter());
        dogEntity.setDogName(dogDTO.getDogName());
        dogEntity.setDogSpecies(dogDTO.getDogSpecies());
        dogEntity.setDogAge(dogDTO.getDogAge());
        dogEntity.setDogWeight(dogDTO.getDogWeight());
        dogEntity.setMemberEntity(memberEntity);
        return dogEntity;
    }
}
