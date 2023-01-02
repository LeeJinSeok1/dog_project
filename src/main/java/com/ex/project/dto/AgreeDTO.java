package com.ex.project.dto;

import com.ex.project.entity.AgreeEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AgreeDTO {
    private Long id;
    private Long applyId;
    private String agreeWriter;
    private String agreeApplyWriter;
    private String agreePhone;

    public static AgreeDTO toChangeDTO(AgreeEntity agreeEntity) {
        AgreeDTO agreeDTO = new AgreeDTO();
        agreeDTO.setAgreeWriter(agreeEntity.getAgreeWriter());
        agreeDTO.setAgreeApplyWriter(agreeEntity.getAgreeApplyWriter());
        agreeDTO.setAgreePhone(agreeEntity.getAgreePhone());
        agreeDTO.setId(agreeEntity.getId());
        return agreeDTO;
    }
}
