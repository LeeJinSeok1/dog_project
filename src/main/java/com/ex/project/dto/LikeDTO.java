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
    private Long likeProductId;
    private int likeCount;
    private String memberEmail;

    public static LikeDTO toChangeDTO(LikeEntity likeEntity) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(likeEntity.getId());
        likeDTO.setLikeProductId(likeEntity.getProductEntity().getId());
        likeDTO.setMemberEmail(likeEntity.getMemberEntity().getMemberEmail());
        likeDTO.setLikeCount(likeEntity.getLikeCount());
        return likeDTO;
    }
}
