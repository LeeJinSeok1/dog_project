package com.ex.project.controller;

import com.ex.project.dto.DogDTO;
import com.ex.project.service.DogSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

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
    public String dogSave(@ModelAttribute DogDTO dogDTO) throws IOException {
        Long savedId = dogSerivce.dogSave(dogDTO);
        return "/dog/dogSaveSuccess";
    }
    //강아지 수정
    @GetMapping("/dogUpdate/{dogWriter}")
    public String  dogUpdateForm(@PathVariable String dogWriter,
                                 Model model){
        DogDTO dogDTO = dogSerivce.findDog(dogWriter);
        model.addAttribute("dog",dogDTO);
        return "/dog/dogUpdate";
    }
    //강아지 수정처리
    @PostMapping("/dogUpdate")
    public String dogUpdate(@ModelAttribute DogDTO dogDTO)throws IOException {
        dogSerivce.dogUpdate(dogDTO);
        return "home";
    }
}
