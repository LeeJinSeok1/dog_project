package com.ex.project.controller;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.dto.ApplyDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.service.AdoptService;
import com.ex.project.service.ApplyService;
import com.ex.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplyController {
    private final AdoptService adoptService;
    private final MemberService memberService;

    private final ApplyService applyService;
    @GetMapping("/adoptApplySave/{id}/{memberEmail}")
    public String adoptApplySavePage(@PathVariable Long id,@PathVariable String memberEmail,
                                     Model model){
        System.out.println("memberEmail="+memberEmail);
       AdoptDTO adoptDTO = adoptService.adoptDetail(id);
       MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
       model.addAttribute("adopt",adoptDTO);
       model.addAttribute("member",memberDTO);
       return "/apply/adoptApplySave";
    }

    @PostMapping("/applySave")
    public String applySave(@ModelAttribute ApplyDTO applyDTO){
        applyService.applySave(applyDTO);
        return "/apply/applySuccess";
    }
}
