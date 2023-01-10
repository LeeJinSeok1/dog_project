package com.ex.project.controller;

import com.ex.project.dto.LikeDTO;
import com.ex.project.entity.LikeEntity;
import com.ex.project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/likeSave")
    public @ResponseBody String likeSave(@ModelAttribute LikeDTO likeDTO) {

        Long savedId = likeService.likeSave(likeDTO);
        System.out.println("savedId=" + savedId);
        if (savedId != null) {
            return "good";
        } else {
            return "no";
        }
    }

    @PostMapping("/likeDelete")
    public @ResponseBody String likeDelete(@ModelAttribute LikeDTO likeDTO){
        Long likeId = likeService.checkFind(likeDTO);
        System.out.println("likeId="+likeId);
        if(likeId != null){
            likeService.likeDelete(likeId,likeDTO.getLikeProductId());
            return "good";
        }else{
            return "no";
        }
    }


    }


