package com.ex.project.dto;

import com.ex.project.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentSaveTime;
    private Long adoptId;

    public static CommentDTO toChangeDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentSaveTime(commentEntity.getCommentSaveTime());
        commentDTO.setAdoptId(commentEntity.getAdoptEntity().getId());
        return commentDTO;
    }
}
