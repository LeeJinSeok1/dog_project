package com.ex.project.controller;

import com.ex.project.dto.AgreeDTO;
import com.ex.project.service.AgreeService;
import com.ex.project.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AgreeController {
    private final AgreeService agreeService;
    private final ApplyService applyService;
    @PostMapping("/applyAgree")
    public String applyAgree(@ModelAttribute AgreeDTO agreeDTO){
        applyService.applyDelete(agreeDTO.getApplyId());
        agreeService.agreeSave(agreeDTO);
        return "/apply/applyAgree";
    }

    @PostMapping("/findAgree")
    public @ResponseBody List<AgreeDTO> findAgree(@RequestParam("memberEmail") String memberEmail){
        List<AgreeDTO> agreeDTOList =agreeService.findAgree(memberEmail);
        return agreeDTOList;
    }

    @GetMapping("/agreeSuccess")
    public String agreeSuccess(@RequestParam("id") Long id){
        agreeService.deleteById(id);
        return "/apply/agreeSuccess";
    }
}
