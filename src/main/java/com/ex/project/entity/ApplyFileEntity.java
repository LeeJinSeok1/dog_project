package com.ex.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="apply_file_table")
public class ApplyFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String originalFileName;
    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="apply_id")
    private ApplyEntity applyEntity;

    public static ApplyFileEntity toChangeFileEntity(ApplyEntity entity, String originalFileName, String storedFileName) {
        ApplyFileEntity applyFileEntity = new ApplyFileEntity();
        applyFileEntity.setOriginalFileName(originalFileName);
        applyFileEntity.setStoredFileName(storedFileName);
        applyFileEntity.setApplyEntity(entity);
        return applyFileEntity;
    }
}
