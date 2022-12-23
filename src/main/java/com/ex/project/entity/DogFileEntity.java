package com.ex.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="dog_file_table")
public class DogFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dog_id")
    private DogEntity dogEntity;

    public static DogFileEntity toSaveDogFileEntity(DogEntity entity, String originalFileName, String storedFileName) {
        DogFileEntity dogFileEntity = new DogFileEntity();
        dogFileEntity.setOriginalFileName(originalFileName);
        dogFileEntity.setStoredFileName(storedFileName);
        dogFileEntity.setDogEntity(entity);
        return dogFileEntity;
    }

    public static DogFileEntity toUpdateDogFileEntity(DogEntity entity, String originalFileName,
                                                      String storedFileName,
                                                      Long fileId
                                                     ) {
        DogFileEntity dogFileEntity = new DogFileEntity();
        dogFileEntity.setId(fileId);
        dogFileEntity.setOriginalFileName(originalFileName);
        dogFileEntity.setStoredFileName(storedFileName);
        dogFileEntity.setDogEntity(entity);
        return dogFileEntity;
    }
}
