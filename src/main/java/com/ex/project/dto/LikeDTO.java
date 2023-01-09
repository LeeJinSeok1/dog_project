package com.ex.project.dto;

import com.ex.project.entity.LikeEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LikeDTO {
    private Long id;
    private Long productId;
    private String memberEmail;

    public static LikeDTO toChangeDTO(LikeEntity likeEntity) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(likeEntity.getId());
        likeDTO.setProductId(likeEntity.getProductEntity().getId());
        likeDTO.setMemberEmail(likeEntity.getMemberEntity().getMemberEmail());
        return likeDTO;
    }
}
