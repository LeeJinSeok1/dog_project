package com.ex.project.service;

import com.ex.project.dto.CommentDTO;
import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.CommentEntity;
import com.ex.project.repository.AdoptRepository;
import com.ex.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdoptRepository adoptRepository;
    public void commentSave(CommentDTO commentDTO) {
        AdoptEntity adoptEntity = adoptRepository.findById(commentDTO.getAdoptId()).get();
        CommentEntity commentEntity = CommentEntity.toChangeEntity(commentDTO,adoptEntity);
        commentRepository.save(commentEntity);
    }

    @Transactional
    public List<CommentDTO> findAll(Long adoptId) {
        AdoptEntity adoptEntity = adoptRepository.findById(adoptId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByAdoptEntityOrderByIdDesc(adoptEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
            CommentDTO commentDTO = CommentDTO.toChangeDTO(commentEntity);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;

    }
}
