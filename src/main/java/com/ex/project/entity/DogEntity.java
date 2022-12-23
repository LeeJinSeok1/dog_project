package com.ex.project.entity;

import com.ex.project.dto.DogDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column
    private int fileAttached;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "dogEntity", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<DogFileEntity> dogFileEntityList = new ArrayList<>();

    public static DogEntity toChangeEntity(DogDTO dogDTO, MemberEntity memberEntity) {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setDogWriter(dogDTO.getDogWriter());
        dogEntity.setDogName(dogDTO.getDogName());
        dogEntity.setDogSpecies(dogDTO.getDogSpecies());
        dogEntity.setDogAge(dogDTO.getDogAge());
        dogEntity.setDogWeight(dogDTO.getDogWeight());
        dogEntity.setMemberEntity(memberEntity);
        dogEntity.setFileAttached(0);
        return dogEntity;
    }


    public static DogEntity toChangeFileEntity(DogDTO dogDTO, MemberEntity memberEntity) {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setDogWriter(dogDTO.getDogWriter());
        dogEntity.setDogName(dogDTO.getDogName());
        dogEntity.setDogSpecies(dogDTO.getDogSpecies());
        dogEntity.setDogAge(dogDTO.getDogAge());
        dogEntity.setDogWeight(dogDTO.getDogWeight());
        dogEntity.setMemberEntity(memberEntity);
        dogEntity.setFileAttached(1);
        return dogEntity;
    }

    public static DogEntity toUpdateEntity(DogDTO dogDTO,MemberEntity memberEntity) {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setId(dogDTO.getId());
        dogEntity.setDogWriter(dogDTO.getDogWriter());
        dogEntity.setDogName(dogDTO.getDogName());
        dogEntity.setDogSpecies(dogDTO.getDogSpecies());
        dogEntity.setDogAge(dogDTO.getDogAge());
        dogEntity.setDogWeight(dogDTO.getDogWeight());
        dogEntity.setFileAttached(0);
        dogEntity.setMemberEntity(memberEntity);
        return dogEntity;
    }

    public static DogEntity toUpdateFileEntity(DogDTO dogDTO,MemberEntity memberEntity) {
        DogEntity dogEntity = new DogEntity();
        dogEntity.setId(dogDTO.getId());
        dogEntity.setDogWriter(dogDTO.getDogWriter());
        dogEntity.setDogName(dogDTO.getDogName());
        dogEntity.setDogSpecies(dogDTO.getDogSpecies());
        dogEntity.setDogAge(dogDTO.getDogAge());
        dogEntity.setDogWeight(dogDTO.getDogWeight());
        dogEntity.setFileAttached(1);
        dogEntity.setMemberEntity(memberEntity);
        return dogEntity;
    }
}
