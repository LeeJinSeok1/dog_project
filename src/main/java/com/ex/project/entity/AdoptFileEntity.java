package com.ex.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="adopt_file_table")
public class AdoptFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adopt_id")
    private AdoptEntity adoptEntity;



    public static AdoptFileEntity toSaveAdoptFileEntity(AdoptEntity adopt, String originalFileName, String storedFileName) {
        AdoptFileEntity adoptFileEntity = new AdoptFileEntity();
        adoptFileEntity.setAdoptEntity(adopt);
        adoptFileEntity.setOriginalFileName(originalFileName);
        adoptFileEntity.setStoredFileName(storedFileName);
        return adoptFileEntity;
    }
}
