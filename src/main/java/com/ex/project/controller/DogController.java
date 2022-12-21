package com.ex.project.controller;

import com.ex.project.dto.DogDTO;
import com.ex.project.service.DogSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DogController {
    private final DogSerivce dogSerivce;
    @GetMapping("/dogSave")
    public String dogSavePage() {
        return "/dog/dogSave";
    }
    //강아지 저장처리
    @PostMapping("/dogSave")
    public String dogSave(@ModelAttribute DogDTO dogDTO) {
        Long savedId = dogSerivce.dogSave(dogDTO);
        return "/dog/dogSaveSuccess";
    }
}
