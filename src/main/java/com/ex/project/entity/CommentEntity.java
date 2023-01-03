package com.ex.project.entity;

import com.ex.project.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="commet_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,nullable = false)
    private String commentWriter;
    @Column(length = 200,nullable = false)
    private String commentContents;
    @CreationTimestamp
    @Column
    private LocalDateTime commentSaveTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adopt_id")
    private AdoptEntity adoptEntity;

    public static CommentEntity toChangeEntity(CommentDTO commentDTO, AdoptEntity adoptEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAdoptEntity(adoptEntity);
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        return commentEntity;
    }
}
