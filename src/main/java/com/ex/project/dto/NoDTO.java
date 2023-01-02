package com.ex.project.dto;

import com.ex.project.entity.NoEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoDTO {
    private Long id;
    private Long applyId;
    private String noWriter;
    private String noApplyWriter;
    private String noContents;

    public static NoDTO toChangeDTO(NoEntity noEntity) {
        NoDTO noDTO = new NoDTO();
        noDTO.setId(noEntity.getId());
        noDTO.setNoApplyWriter(noEntity.getNoApplyWriter());
        noDTO.setNoWriter(noEntity.getNoWriter());
        noDTO.setNoContents(noEntity.getNoContents());
        return noDTO;
    }
}
