package com.ex.project.dto;

import com.ex.project.entity.SuccessEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDTO {
    private Long id;
    private Long agreeId;
    private String agreeWriter;
    private String agreeApplyWriter;
    private String agreeContents;
    private LocalDateTime successSaveTime;

    public static SuccessDTO toChangeDTO(SuccessEntity successEntity) {
        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setId(successEntity.getId());
        successDTO.setAgreeWriter(successEntity.getAgreeWriter());
        successDTO.setAgreeApplyWriter(successEntity.getAgreeApplyWriter());
        successDTO.setAgreeContents(successEntity.getAgreeContents());
        successDTO.setAgreeId(successEntity.getSuccessAgreeId());
        successDTO.setSuccessSaveTime(successEntity.getSuccessSaveTIme());
        return successDTO;
    }
}
