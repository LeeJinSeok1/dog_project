package com.ex.project.controller;

import com.ex.project.dto.NoDTO;
import com.ex.project.service.ApplyService;
import com.ex.project.service.NoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoController {
    private final ApplyService applyService;
    private final NoService noService;
    @PostMapping("/applyNo")
    public String applyNo(@ModelAttribute NoDTO noDTO){
        applyService.applyDelete(noDTO.getApplyId());
        noService.save(noDTO);
        return "/apply/applyNo";
    }
    @PostMapping("findNo")
    public @ResponseBody List<NoDTO> findNo(@RequestParam("memberEmail") String memberEmail){
        List<NoDTO> noDTOList = noService.findNo(memberEmail);
        return noDTOList;
    }
    @GetMapping("/noDelete")
    public String noDelete(@RequestParam("id") Long id){
        noService.deleteNo(id);
        return "/apply/applyNo2";
    }
}
