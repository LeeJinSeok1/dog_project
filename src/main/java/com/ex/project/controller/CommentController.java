package com.ex.project.controller;

import com.ex.project.dto.CommentDTO;
import com.ex.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/commentSave")
    public @ResponseBody List<CommentDTO> commentSave(@ModelAttribute CommentDTO commentDTO){
        commentService.commentSave(commentDTO);
        List<CommentDTO> commentDTOList =commentService.findAll(commentDTO.getAdoptId());
        return commentDTOList;
    }
 }
