package com.ex.project.controller;

import com.ex.project.dto.SuccessDTO;
import com.ex.project.service.AgreeService;
import com.ex.project.service.SuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SuccessController {
    private final SuccessService successService;
    private final AgreeService agreeService;
    @PostMapping("/successSave")
    public @ResponseBody String successSave(@ModelAttribute SuccessDTO successDTO){
        Long savedId = successService.successSave(successDTO);
        agreeService.deleteById(successDTO.getAgreeId());
        if(savedId == null){
            return "no";
        }else{
            return "good";
        }
    }

    @GetMapping("successFind")
    public String successList(Model model){
        List<SuccessDTO> successDTOList = successService.findAll();
        model.addAttribute("successList",successDTOList);
        return "/success/successList";
    }
}
